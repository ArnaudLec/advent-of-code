package day03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Day03 {

	private static final Pattern MULTIPLY_PATTERN = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

	public static int calc(String input) {
		Matcher m = MULTIPLY_PATTERN.matcher(input);
		int result = 0;

		while (m.find()) {
			int left = Integer.parseInt(m.group(1));
			int right = Integer.parseInt(m.group(2));
			result += left * right;
		}

		return result;
	}
}
