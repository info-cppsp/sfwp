package hello;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class Application {

	@Autowired
	private ApplicationContext context;

//	@Autowired
//	LocalContainerEntityManagerFactoryBean fb;
//	EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();
//	@PersistenceContext
//	EntityManager em;

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner run(MarketplaceRepository repository) throws Exception {
		return args -> {
			log.info("hi");
			Marketplace mp = new Marketplace(3, "Banggood");
//			em.persist(mp);
//			em.flush();

			EntityManager em = ((EntityManagerFactory) context.getBean("entityManagerFactory")).createEntityManager();
			em.getTransaction().begin();
			em.persist(mp);
			em.getTransaction().commit();
			em.close();

			repository.save(new Marketplace(1, "Amazon"));
			repository.save(new Marketplace(2, "Ebay"));

			for (Marketplace marketplace : repository.findAll()) {
				log.info(marketplace.toString());
			}

//			for (String string : context.getBeanDefinitionNames()) {
//				log.info(string);
//			}

		    Flux<ListingStatus> result = MyRestPublisherBuilder.getFluxForApiClass(new ParameterizedTypeReference<ListingStatus>() {});
			result.subscribe(Application::hadleResponse);
//			log.info(result.block().toString());
		};
	}

	private static void hadleResponse(ListingStatus ls) {
		log.info(ls.toString());
	}
}