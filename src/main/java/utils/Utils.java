package utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utils {

	public static int toInt(final char c) {
		// subtracting 0 char code to have integer value of c
		int intVal = c - '0';
		if (intVal < 0 || intVal >= 10) {
			throw new IllegalArgumentException("Character [" + c + "] is not a digit");
		}
		return intVal;
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

	public static int[] toIntArray(final Collection<Integer> collection) {
		return collection.stream().mapToInt(Integer::intValue).toArray();
	}

	public static long[] toLongArray(final Collection<Long> collection) {
		return collection.stream().mapToLong(Long::longValue).toArray();
	}

	public static char[][] to2dArray(final String str) {
		return Arrays.stream(splitLines(str)).map(String::toCharArray).toArray(char[][]::new);
	}

	public static String toString(final char[][] twoDimensionArray) {
		return Arrays.stream(twoDimensionArray)
				.map(String::valueOf)
				.collect(Collectors.joining(System.lineSeparator()));
	}

	public static char[][] rotateRight(char[][] twoDimensionArray) {
		final int rows = twoDimensionArray.length;
		final int cols = twoDimensionArray[0].length;
		char[][] rotated = new char[cols][rows];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				rotated[col][rows - row - 1] = twoDimensionArray[row][col];
			}
		}
		return rotated;
	}
}
