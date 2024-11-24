package day01;

import java.util.Arrays;
import java.util.List;

import utils.Utils;

public class Day01 {

	private static final int IGNORE = -1;

	private static final List<String> REPLACEMENTS = List.of("zero", "one", "two", "three", "four", "five", "six",
			"seven", "eight", "nine");

	public static int part1Compute(final String string) {
		return part1Compute(getInts(string));
	}

	private static int part1Compute(final int[] ints) {
		int first = 0, second = 0;

		for (int val : ints) {
			if (val != IGNORE) {
				first = val;
				break;
			}
		}
		for (int i = ints.length - 1; i >= 0; i--) {
			int val = ints[i];
			if (val != IGNORE) {
				second = val;
				break;
			}
		}

		return first * 10 + second;
	}

	private static int[] getInts(final String str) {
		int[] ints = new int[str.length()];
		Arrays.fill(ints, IGNORE);
		for (int i = 0; i < ints.length; i++) {
			char c = str.charAt(i);
			if (Character.isDigit(c)) {
				ints[i] = Utils.toInt(c);
			}
		}
		return ints;
	}

	public static int part2Compute(final String string) {
		int[] ints = getInts(string);
		replaceWordDigits(string, ints);
		return part1Compute(ints);
	}

	private static void replaceWordDigits(final String string, final int[] ints) {
		for (int i = 0; i < REPLACEMENTS.size(); i++) {
			String search = REPLACEMENTS.get(i);
			int indexOfSearch = string.indexOf(search);
			while (indexOfSearch != -1) {
				ints[indexOfSearch] = i;
				indexOfSearch = string.indexOf(search, indexOfSearch + 1);
			}
		}
	}

}
