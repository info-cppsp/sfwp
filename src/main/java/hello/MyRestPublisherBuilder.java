package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
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

	public static <T> Flux<T> getFluxForApiClass(ParameterizedTypeReference<T> typeReference) {

	    return createWebClient().get()
	    		.uri("/" + getApiURIFromTypeReference(typeReference))
	            .accept(MediaType.APPLICATION_JSON)
	            .retrieve()
	            .bodyToFlux(typeReference);
	}

	public static <T> String getApiURIFromTypeReference(ParameterizedTypeReference<T> typeReference) {

		String ret = "";
		String typeReferenceString = typeReference.getType().getTypeName();
		int index = typeReferenceString.lastIndexOf(".");

		if (index != -1) {
			ret = typeReferenceString.substring(index + 1);
			ret = ret.substring(0, 1).toLowerCase() + ret.substring(1);
		}

		return ret;
	}
}
