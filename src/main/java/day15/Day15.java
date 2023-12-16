package day15;

import java.util.Arrays;

class Day15 {

	public static long calc(final String input) {
		return Arrays.stream(input.trim().split(",")).mapToInt(Day15::hashEntry).sum();
	}

	private static int hashEntry(final String input) {
		int currentValue = 0;
		for (int i = 0; i < input.length(); i++) {
			// determine ASCII code for current char
			int curChar = input.codePointAt(i);
			// increase current value by char code
			currentValue += curChar;
			// multiply by 17
			currentValue *= 17;
			// set currentValue to the remained of dividing by 256
			currentValue %= 256;
		}
		return currentValue;
	}
}
