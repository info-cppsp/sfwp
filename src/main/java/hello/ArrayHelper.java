package hello;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public final class ArrayHelper {

	public static <T> T[] skipFirstElement(T[] array) {

		return Arrays.copyOfRange(array, 1, array.length);
	}

	public static <T> T[] skipSecondElement(T[] array) {

		T[] newArray = skipFirstElement(array);
		newArray[0] = array[0];
		return newArray;
	}
}
