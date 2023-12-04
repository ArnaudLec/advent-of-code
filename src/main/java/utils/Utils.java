package utils;

import java.util.Arrays;

public class Utils {

	public static int toInt(final char c) {
		// subtracting 0 char code to have integer value of c
		return c - '0';
	}

	public static char[][] to2dArray(final String str) {
		return Arrays.stream(splitLines(str)).map(String::toCharArray).toArray(char[][]::new);
	}

	public static String[] splitLines(final String str) {
		return str.split("\r?\n");
	}
}
