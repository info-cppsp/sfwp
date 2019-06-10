package hello;

import org.springframework.stereotype.Component;

@Component
public final class StringHelper {

	public static String firstCharToLower(String string) {

		return Character.toLowerCase(string.charAt(0)) + string.substring(1);
	}

	public static String firstCharToUpper(String string) {

		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}
}
