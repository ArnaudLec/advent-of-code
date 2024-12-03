package day03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.Part;

class Day03 {

	private static final Pattern MULTIPLY_PATTERN = Pattern
			.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)");

	public static int calc(String input, Part part) {
		Matcher m = MULTIPLY_PATTERN.matcher(input);
		int result = 0;
		boolean ignore = false;
		while (m.find()) {
			if ("do()".equals(m.group())) {
				ignore = false;
			} else if ("don't()".equals(m.group())) {
				ignore = true;
			} else if (part == Part.PART_1 || !ignore) {
				int left = Integer.parseInt(m.group(1));
				int right = Integer.parseInt(m.group(2));
				result += left * right;
			}
		}

		return result;
	}

}
