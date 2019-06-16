package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import hello.helper.StringHelper;
import reactor.core.publisher.Flux;

@Component
public final class MyRestPublisherBuilder {

	@Bean
	public static WebClient createWebClient() {

		return WebClient.builder().baseUrl("https://my.api.mockaroo.com")
				.defaultHeader("X-API-Key", "63304c70")
				.build();
	}

	public static <T> Flux<T> getFluxForApiClass(Class<T> clazz) {

	    return createWebClient().get()
	    		.uri("/" + StringHelper.firstCharToLower(clazz.getSimpleName()))
	            .accept(MediaType.APPLICATION_JSON)
	            .retrieve()
	            .bodyToFlux(clazz);
	}
}
