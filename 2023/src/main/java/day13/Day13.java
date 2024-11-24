package day13;

import java.util.Arrays;

import utils.Utils;

class Day13 {

	public static long sumReflections(final String input) {
		String[] patterns = input.split("\n\n");

		return Arrays.stream(patterns).mapToLong(Day13::getReflectionValue).sum();
	}

	private static long getReflectionValue(final String pattern) {
		int palindromeColIndex = getColsReflectionValue(pattern);
		if (palindromeColIndex != -1) {
			return palindromeColIndex;
		}

		char[][] pattern2dArr = Utils.to2dArray(pattern);
		StringBuilder reversedPattern = new StringBuilder();

		for (int i = 0; i < pattern2dArr[0].length; i++) {
			for (int j = 0; j < pattern2dArr.length; j++) {
				reversedPattern.append(pattern2dArr[j][i]);
			}
			reversedPattern.append("\n");
		}

		int palindromeRowIndex = getColsReflectionValue(reversedPattern.toString());
		if (palindromeRowIndex == -1) {
			throw new IllegalStateException("Should have found something\n\nInitial pattern:\n" + pattern
					+ "\n\nReversed:\n" + reversedPattern);
		}
		return palindromeRowIndex * 100;
	}

	private static int getColsReflectionValue(final String pattern) {
		String[] rows = pattern.split("\n");

		int[] remainingColsPalindromes = new int[rows[0].length()];
		Arrays.fill(remainingColsPalindromes, 1);
		remainingColsPalindromes[0] = 0;
		for (int i = 0; i < rows.length; i++) {
			String row = rows[i];
			for (int j = 1; j < row.length(); j++) {
				if (remainingColsPalindromes[j] == 0) {
					continue;
				}
				int remainingRowLength = row.length() - j;
				String part = row.substring(0, j);
				String reversed = new StringBuilder(part).reverse()
						.substring(0, Math.min(remainingRowLength, part.length()))
						.toString();
				int indexOfReversed = row.indexOf(reversed, j);
				if (indexOfReversed != j) {
					remainingColsPalindromes[j] = 0;
				}
			}
			System.out.println("After line " + i + ": " + Arrays.toString(remainingColsPalindromes));
		}

		return Utils.arrayContains(remainingColsPalindromes, 1);
	}

}
