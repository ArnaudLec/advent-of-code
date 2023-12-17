package day12;

import java.util.Arrays;
import java.util.List;

import utils.Utils;

class Day12 {
	private record Springs(String springStatuses, int[] groups) {

		public int countPossibleArrangements() {
			return countPossibleArrangements(springStatuses, 0);
		}

		private int countPossibleArrangements(final String arrangement, final int pos) {
			if (pos == arrangement.length()) {
				return isPossibleArrangement(arrangement) ? 1 : 0;
			}
			int nextPos = pos + 1;
			if (arrangement.charAt(pos) == '?') {
				String beforeCurPos = arrangement.substring(0, pos);
				String afterCurPos = arrangement.substring(nextPos);

				return countPossibleArrangements(beforeCurPos + "#" + afterCurPos, nextPos)
						+ countPossibleArrangements(beforeCurPos + "." + afterCurPos, nextPos);
			}
			return countPossibleArrangements(arrangement, nextPos);
		}

		private boolean isPossibleArrangement(final String arrangement) {
			int curGroupSize = 0;
			int currentGroup = 0;
			for (char c : arrangement.toCharArray()) {
				if (c == '.') {
					if (curGroupSize > 0) {
						if (currentGroup >= groups.length || groups[currentGroup] != curGroupSize) {
							return false;
						}
						currentGroup++;
					}
					curGroupSize = 0;
				} else if (c == '#') {
					curGroupSize++;
				}
			}

			// already matched all groups and ending with a '.'
			return (currentGroup == groups.length && curGroupSize == 0)
					// matched all group except last and ending with a '#'
					|| (currentGroup == groups.length - 1 && groups[currentGroup] == curGroupSize);
		}

		@Override
		public String toString() {
			return springStatuses + " " + Arrays.toString(groups);
		}

		public static Springs fromRow(final String row) {
			String[] parts = row.split(" ");
			return new Springs(parts[0], Arrays.stream(parts[1].split(",")).mapToInt(Integer::parseInt).toArray());
		}
	}

	public static int sumPossibleArrangements(final String input) {
		List<Springs> springs = Arrays.stream(Utils.splitLines(input)).map(Springs::fromRow).toList();
		return springs.stream().mapToInt(Springs::countPossibleArrangements).sum();
	}

}
