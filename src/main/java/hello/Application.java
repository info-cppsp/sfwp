package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> {
			log.info("hi");

		    Flux<ListingStatus> result = MyRestPublisherBuilder.getFluxForApiClass(new ParameterizedTypeReference<ListingStatus>() {});
			result.subscribe(Application::hadleResponse);
//			log.info(result.block().toString());
		};
	}

	private static void hadleResponse(ListingStatus ls) {
		log.info(ls.toString());
	}
}