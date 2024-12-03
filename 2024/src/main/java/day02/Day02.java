package day02;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import utils.Part;
import utils.Utils;

class Day02 {

	private static final Pattern REPORT_SPLITTER = Pattern.compile("\s+");

	public static Report[] parse(String input) {
		return Arrays.stream(Utils.splitLines(input)).map(Day02::splitReport).toArray(Report[]::new);
	}

	private static Report splitReport(String report) {
		return new Report(Arrays.stream(REPORT_SPLITTER.split(report)).mapToInt(Integer::parseInt).toArray());
	}

	public static record Report(int[] levels) {

		public boolean isSafe(Part part) {
			Direction reportDirection = null;
			int lastGoodLevel = levels[0];

			for (int i = 1; i < levels.length; i++) {
				int level = levels[i];

				int diff = lastGoodLevel - level;

				if (reportDirection == null) {
					reportDirection = diff > 0 ? Direction.DECREASING : Direction.INCREASING;
				}
				if (isBadLevel(reportDirection, diff)) {
					if (part == Part.PART_2) {
						for (int indexToIgnore = 0; indexToIgnore < levels.length; indexToIgnore++) {
							boolean isSafeReportWithIgnoredIndex = isSafeReportIgnoringIndex(indexToIgnore);
							if (isSafeReportWithIgnoredIndex) {
								return true;
							}
						}
					}

					// not a safe report
					return false;
				}

				lastGoodLevel = level;
			}
			return true;
		}

		private boolean isSafeReportIgnoringIndex(int indexToIgnore) {
			Direction reportDirection = null;
			int lastGoodLevel = indexToIgnore == 0 ? levels[1] : levels[0];

			for (int i = indexToIgnore == 0 ? 2 : 1; i < levels.length; i++) {
				if (i == indexToIgnore) {
					continue;
				}
				int level = levels[i];

				int diff = lastGoodLevel - level;

				if (reportDirection == null) {
					reportDirection = diff > 0 ? Direction.DECREASING : Direction.INCREASING;
				}
				if (isBadLevel(reportDirection, diff)) {
					return false;
				}

				lastGoodLevel = level;
			}
			return true;
		}

		private static boolean isBadLevel(Direction direction, int diff) {
			if ((diff == 0) || diff < -3 || diff > 3) {
				return true;
			}
			return diff > 0 ? direction == Direction.INCREASING : direction == Direction.DECREASING;
		}

		@Override
		public final String toString() {
			return Arrays.stream(levels).mapToObj(String::valueOf).collect(Collectors.joining(" "));
		}
	}

	private enum Direction {
		INCREASING, DECREASING
	}
}
