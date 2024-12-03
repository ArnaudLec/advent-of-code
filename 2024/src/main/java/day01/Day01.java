package day01;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.Utils;

class Day01 {

	public static OrderedLists parse(String input) {
		String[] lines = Utils.splitLines(input);

		Pattern elementsExtractor = Pattern.compile("(?<left>\\d+)[ ]+(?<right>\\d+)");

		int[] leftList = new int[lines.length];
		int[] rightList = new int[lines.length];

		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			Matcher matcher = elementsExtractor.matcher(line);
			if (matcher.find()) {
				String left = matcher.group("left");
				String right = matcher.group("right");

				leftList[i] = Integer.parseInt(left);
				rightList[i] = Integer.parseInt(right);
			}
		}

		return new OrderedLists(leftList, rightList);
	}

	public static record OrderedLists(int[] leftList, int[] rightList) {

		public OrderedLists(int[] leftList, int[] rightList) {
			if (leftList.length != rightList.length) {
				throw new IllegalArgumentException("Both lists don't have the same size (left:%d,right:%d)"
						.formatted(leftList.length, rightList.length));
			}

			Arrays.sort(leftList);
			Arrays.sort(rightList);

			this.leftList = leftList;
			this.rightList = rightList;
		}

		public int getDistance() {
			int distance = 0;
			for (int i = 0; i < leftList.length; i++) {
				int leftVal = leftList[i];
				int rightVal = rightList[i];

				int valDistance = leftVal - rightVal;
				if (valDistance < 0) {
					valDistance = -valDistance;
				}
				distance += valDistance;
			}

			return distance;
		}

		public long getSimilarityScore() {
			long similarityScore = 0L;
			int rightStartIndex = 0;
			for (int leftVal : leftList) {
				int foundOccurences = 0;
				// both list are sorted, we can start from last rightIndex
				for (int j = rightStartIndex; j < rightList.length; j++) {
					int rightVal = rightList[j];
					if (leftVal == rightVal) {
						if (foundOccurences == 0) {
							rightStartIndex = j;
						}
						foundOccurences++;
					} else if (foundOccurences > 0) {
						// we won't find any other equal value from now on
						break;
					}
				}
				similarityScore += leftVal * foundOccurences;
			}
			return similarityScore;
		}
	}
}
