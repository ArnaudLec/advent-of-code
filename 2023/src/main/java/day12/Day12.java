package day12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import utils.Utils;

class Day12 {

	private record CacheKey(int curPos, int groupPos, int currentGroupSize) {
	}

	public record Springs(String springStatuses, int[] groups) {

		public long countPossibleArrangements() {
			long countPossibleArrangements = countPossibleArrangements(0, 0, 0, new HashMap<>());
			System.out.println(this + " => " + countPossibleArrangements);
			return countPossibleArrangements;
		}

		private long countPossibleArrangements(final int pos, final int groupPos, final int currentGroupSize,
				final Map<CacheKey, Long> cache) {

			CacheKey key = new CacheKey(pos, groupPos, currentGroupSize);
			Long cachedArrangementsCount = cache.get(key);
			if (cachedArrangementsCount != null) {
				return cachedArrangementsCount.longValue();
			}

			if (pos == springStatuses.length()) {
				// already matched all groups and ending with a '.'
				// OR matched all group except last and ending with a '#'
				if ((groupPos == groups.length && currentGroupSize == 0)
						|| (groupPos == groups.length - 1 && groups[groupPos] == currentGroupSize)) {
					return 1;
				}
				return 0;
			}

			long arrangementsCount = 0;
			char currentChar = springStatuses.charAt(pos);

			for (char c : new char[] { '.', '#' }) {
				if (currentChar == c || currentChar == '?') {
					if (c == '.' && currentGroupSize == 0) {
						// not in a group
						arrangementsCount += countPossibleArrangements(pos + 1, groupPos, 0, cache);
					} else if (c == '.' && currentGroupSize > 0 && groupPos < groups.length
							&& groups[groupPos] == currentGroupSize) {
						// group ending
						arrangementsCount += countPossibleArrangements(pos + 1, groupPos + 1, 0, cache);
					} else if (c == '#') {
						// group continuing
						arrangementsCount += countPossibleArrangements(pos + 1, groupPos, currentGroupSize + 1, cache);
					}
				}
			}
			cache.put(key, arrangementsCount);
			return arrangementsCount;
		}

		@Override
		public String toString() {
			return String.format("%-110s %-60s", springStatuses,
					Arrays.stream(groups).mapToObj(String::valueOf).collect(Collectors.joining(",")));
		}

		public static Springs fromRowPart1(final String row) {
			String[] parts = row.split(" ");
			return new Springs(parts[0], Arrays.stream(parts[1].split(",")).mapToInt(Integer::parseInt).toArray());
		}

		public static Springs fromRowPart2(final String row) {
			String[] parts = row.split(" ");
			int[] groups = Arrays.stream(parts[1].split(",")).mapToInt(Integer::parseInt).toArray();

			int[] expandedGroups = new int[groups.length * 5];
			StringBuilder expandedSpringStatuses = new StringBuilder();
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < groups.length; j++) {
					expandedGroups[i * groups.length + j] = groups[j];
				}
				if (!expandedSpringStatuses.isEmpty()) {
					expandedSpringStatuses.append('?');
				}
				expandedSpringStatuses.append(parts[0]);
			}

			return new Springs(expandedSpringStatuses.toString(), expandedGroups);
		}
	}

	public static long sumPossibleArrangements(final String input, final Function<String, Springs> convertor) {
		List<Springs> springs = Arrays.stream(Utils.splitLines(input)).map(convertor).toList();
		return springs.stream().mapToLong(Springs::countPossibleArrangements).sum();
	}
}
