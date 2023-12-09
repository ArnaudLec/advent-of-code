package utils;

import java.util.Arrays;
import java.util.function.Predicate;

public class Utils {

	public static int toInt(final char c) {
		// subtracting 0 char code to have integer value of c
		int intVal = c - '0';
		if (intVal < 0 || intVal >= 10) {
			throw new IllegalArgumentException("Character [" + c + "] is not a digit");
		}
		return intVal;
	}

	public static char[][] to2dArray(final String str) {
		return Arrays.stream(splitLines(str)).map(String::toCharArray).toArray(char[][]::new);
	}

	public static String[] splitLines(final String str) {
		return str.split("\r?\n");
	}

	public static <T> Predicate<T> not(final Predicate<T> predicate) {
		return predicate.negate();
	}

	public static int arrayContains(final int[] array, final int value) {
		return arrayContains(array, value, 0);
	}

	public static int arrayContains(final int[] array, final int value, final int fromIndex) {
		return arrayContains(array, value, fromIndex, array.length);
	}

	public static int arrayContains(final int[] array, final int value, final int fromIndex, final int endIndex) {
		for (int i = fromIndex; i < endIndex && i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
}
