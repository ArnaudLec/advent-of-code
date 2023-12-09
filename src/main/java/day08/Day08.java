package day08;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import utils.Utils;

public class Day08 {

	private static final Pattern DIRECTIONS_PATTERN = Pattern
			.compile("(?<place>\\w{3})\\s+=\\s+\\((?<left>\\w{3}),\\s+(?<right>\\w{3})\\)");

	private record Directions(String left, String right) {
	}

	public static int countSteps(final String input) {
		String[] lines = Utils.splitLines(input);
		String route = lines[0];

		Map<String, Directions> map = Arrays.stream(lines)
				.skip(2)
				.map(DIRECTIONS_PATTERN::matcher)
				.peek(Matcher::find)
				.collect(Collectors.toMap(matcher -> matcher.group("place"),
						matcher -> new Directions(matcher.group("left"), matcher.group("right"))));

		String currentPlace = "AAA";
		int steps = 0;
		while (!"ZZZ".equals(currentPlace)) {
			Directions dirs = map.get(currentPlace);
			char direction = route.charAt(steps % route.length());

			currentPlace = switch (direction) {
			case 'L' -> dirs.left;
			case 'R' -> dirs.right;
			default -> throw new IllegalStateException("Direction [" + direction + "] not expected");
			};

			steps++;
		}

		return steps;
	}

}
