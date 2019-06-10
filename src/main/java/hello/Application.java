package hello;

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

	private static final String[] API_CLASS_NAMES = {"Marketplace", "Location", "ListingStatus"};
//	private static final String[] API_CLASS_NAMES = {"Marketplace", "Location", "ListingStatus", "Listing"};

	@Autowired
	private ApplicationContext context;

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> {

			for (String string : API_CLASS_NAMES) {

				Class<?> clazz = Class.forName("hello." + string);
			    Flux<?> result = MyRestPublisherBuilder.getFluxForApiClass(clazz);
				result.subscribe(entity -> {

					String beanName = StringHelper.firstCharToLower(entity.getClass().getSimpleName() + "Repository");

					MarketplaceRepository marketplaceRepository = null;
					LocationRepository locationRepository = null;
					ListingStatusRepository listingStatusRepository = null;
					ListingRepository listingRepository = null;

					switch (string) {

						case "Marketplace":
							marketplaceRepository = (MarketplaceRepository) context.getBean(beanName);
							break;
						case "Location":
							locationRepository = (LocationRepository) context.getBean(beanName);
							break;
						case "ListingStatus":
							listingStatusRepository = (ListingStatusRepository) context.getBean(beanName);
							break;
						case "Listing":
							listingRepository = (ListingRepository) context.getBean(beanName);
							break;

						default:
							break;
					}

					if (null != marketplaceRepository) {
						marketplaceRepository.save((Marketplace) entity);
					} else if (null != locationRepository) {
						locationRepository.save((Location) entity);
					} else if (null != listingStatusRepository) {
						listingStatusRepository.save((ListingStatus) entity);
					} else if (null != listingRepository) {
						listingRepository.save((Listing) entity);
					}

				});

			}


			for (Marketplace marketplace : ((MarketplaceRepository) context.getBean("marketplaceRepository")).findAll()) {
				log.info(marketplace.toString());
			}

			for (Location location : ((LocationRepository) context.getBean("locationRepository")).findAll()) {
				log.info(location.toString());
			}

			for (ListingStatus listingStatus : ((ListingStatusRepository) context.getBean("listingStatusRepository")).findAll()) {
				log.info(listingStatus.toString());
			}

			for (Listing listing : ((ListingRepository) context.getBean("listingRepository")).findAll()) {
				log.info(listing.toString());
			}

//			for (String string : context.getBeanDefinitionNames()) {
//				log.info(string);
//			}
		};
	}
}