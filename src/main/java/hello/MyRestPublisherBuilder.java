package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

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
	    		.uri("/" + getApiURIFromClass(clazz))
	            .accept(MediaType.APPLICATION_JSON)
	            .retrieve()
	            .bodyToFlux(clazz);
	}

	public static <T> String getApiURIFromClass(Class<T> clazz) {

		String ret = "";
		String className = clazz.getTypeName();
		int index = className.lastIndexOf(".");

		if (index != -1) {
			ret = className.substring(index + 1);
			ret = StringHelper.firstCharToLower(ret);
		}

		return ret;
	}
}
