package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public WebClient webClient(WebClient.Builder webClientBuilder) {

		return webClientBuilder.baseUrl("https://my.api.mockaroo.com")
				.defaultHeader("X-API-Key", "63304c70")
				.build();
	}

	@Bean
	public CommandLineRunner run(WebClient webClient) throws Exception {
		return args -> {
			log.info("hi");

		    Flux<ListingStatus> result = webClient.get()
		    		.uri("/listingStatus")
		            .accept(MediaType.APPLICATION_JSON)
		            .retrieve()
		            .bodyToFlux(ListingStatus.class);
			result.subscribe(Application::hadleResponse);
//			log.info(result.block().toString());
		};
	}

	private static void hadleResponse(ListingStatus ls) {
		log.info(ls.toString());
	}
}