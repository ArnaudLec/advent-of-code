package day08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	public record WastelandMap(String route, Map<String, Directions> map) {

		public static WastelandMap parse(final String input) {
			String[] lines = Utils.splitLines(input);
			String route = lines[0];

			Map<String, Directions> map = Arrays.stream(lines)
					.skip(2)
					.map(DIRECTIONS_PATTERN::matcher)
					.peek(Matcher::find)
					.collect(Collectors.toMap(matcher -> matcher.group("place"),
							matcher -> new Directions(matcher.group("left"), matcher.group("right"))));

			return new WastelandMap(route, map);
		}

		public int countStepsPart1() {
			String currentPlace = "AAA";
			int steps = 0;
			while (!"ZZZ".equals(currentPlace)) {
				char direction = route.charAt(steps % route.length());
				currentPlace = getNextNode(currentPlace, direction);
				steps++;
			}

			return steps;
		}

		private String getNextNode(final String currentPlace, final char direction) {
			Directions dirs = map.get(currentPlace);
			return switch (direction) {
			case 'L' -> dirs.left;
			case 'R' -> dirs.right;
			default -> throw new IllegalStateException("Direction [" + direction + "] not expected");
			};
		}

		private char getNextDirection(final long steps) {
			long dirIndex = steps % route.length();
			return route.charAt(Math.toIntExact(dirIndex));
		}

		public long countStepsPart2() {
			List<String> nodes = map.keySet().stream().filter(node -> node.endsWith("A")).toList();

			int[] steps = nodes.stream().mapToInt(this::countStepsTowardsEnd).toArray();

			List<List<Integer>> stepsDivisors = Arrays.stream(steps).mapToObj(Day08::getDivisors).toList();

			List<Integer> commonDivisors = getCommonDivisors(stepsDivisors);

			long result = stepsDivisors.stream()
					.flatMap(List::stream)
					.filter(Utils.not(commonDivisors::contains))
					.mapToLong(a -> a)
					.reduce((a, b) -> a * b)
					.getAsLong();

			return result * commonDivisors.stream().mapToInt(Integer::intValue).reduce((a, b) -> a * b).orElse(1);
		}

		private List<Integer> getCommonDivisors(final List<List<Integer>> stepsDivisors) {
			if (stepsDivisors.size() == 1) {
				return stepsDivisors.get(0);
			}

			List<Integer> step1Divisors = stepsDivisors.get(0);

			return step1Divisors.stream()
					.filter(divisor -> stepsDivisors.stream().allMatch(list -> list.contains(divisor)))
					.toList();
		}

		private int countStepsTowardsEnd(final String node) {
			String currentNode = node;
			int steps = 0;
			while (!currentNode.endsWith("Z")) {
				char direction = getNextDirection(steps);
				currentNode = getNextNode(currentNode, direction);
				steps++;
			}

			return steps;
		}

	}

	private static List<Integer> getDivisors(final int n) {
		List<Integer> divisors = new ArrayList<>();
		for (int i = 1; i <= n / 2; i++) {
			if (n % i == 0) {
				divisors.add(i);
			}
		}
		divisors.add(n);
		return divisors;
	}

}
