package hello;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class Application {

	private static final String[] API_CLASS_NAMES = {"Marketplace", "Location", "ListingStatus", "Listing"};
	private static int successCounter = 0;

	@Autowired
	private ApplicationContext context;

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
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

//			for (String string : context.getBeanDefinitionNames()) {
//				log.info(string);
//			}
		};
	}

	public static void errorHandler(Throwable throwable) {
		throwable.printStackTrace();
	}

	public static void completeHandler() {

		successCounter++;
		if (successCounter == API_CLASS_NAMES.length) {
			log.info("Save to database complete.");
		}
	}
}