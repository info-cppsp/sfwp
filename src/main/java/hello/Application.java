package hello;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.StoredProcedureQuery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import hello.helper.StringHelper;
import hello.model.report.ListingReportSummary;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class Application {

	private static final String[] API_CLASS_NAMES = {"Marketplace", "Location", "ListingStatus", "Listing"};
	private static int successCounter = 0;

	private static ApplicationContext context;

	public static void main(String args[]) {
		context = SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> {

			Object object = new Object();

			for (String string : API_CLASS_NAMES) {

				Class<?> clazz = Class.forName("hello.model." + string);
			    Flux<?> result = MyRestPublisherBuilder.getFluxForApiClass(clazz);
				result.subscribe(entity -> {

					String beanName = StringHelper.firstCharToLower(entity.getClass().getSimpleName() + "Repository");
					Object beanObject = context.getBean(beanName);

					try {
						Method saveMethod = beanObject.getClass().getMethod("save", object.getClass());
						saveMethod.invoke(beanObject, entity);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}, Application::errorHandler, Application::completeHandler);
			}

		};
	}

	public static void errorHandler(Throwable throwable) {
		throwable.printStackTrace();
	}

	public static void completeHandler() {

		successCounter++;
		if (successCounter == API_CLASS_NAMES.length) {
			log.info("Save to database complete.");

			EntityManager em = ((EntityManagerFactory) context.getBean("entityManagerFactory")).createEntityManager();
			StoredProcedureQuery query = em.createStoredProcedureQuery("generate_report");
			query.execute();

			ListingReportSummary reportSummary = ListingReportSummary.generateFromObjectArrays((ArrayList<Object[]>) query.getResultList());

			try {
				saveReportToFile(reportSummary);
				MyFtpClient.uploadFile(new File("report.json"));
				log.info("Successfully uploaded report.json to FTP...");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void saveReportToFile(ListingReportSummary reportSummary) throws IOException {

		try (FileWriter file = new FileWriter("report.json")) {
			file.write(reportSummary.toJSONString());
			log.info("Successfully wrote reportSummary JSON to File...");
		}
	}
}