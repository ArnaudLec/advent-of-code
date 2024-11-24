package day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.Utils;

class Day09 {

	public record History(long[] dataset) {

		private static History parse(final String line) {
			return new History(Arrays.stream(line.split("\\s+")).mapToLong(Long::parseLong).toArray());
		}

		public long findNextValue() {
			List<long[]> datasetList = new ArrayList<>();

			long[] firstLineWithEmptySlot = Arrays.copyOf(dataset, dataset.length + 1);
			datasetList.add(firstLineWithEmptySlot);

			// descending
			while (!lastLineIsOnlyZeroes(datasetList)) {
				long[] lastLine = datasetList.get(datasetList.size() - 1);
				long[] diffDataset = new long[lastLine.length - 1];
				for (int i = 0; i < diffDataset.length - 1; i++) {
					diffDataset[i] = lastLine[i + 1] - lastLine[i];

				}
				datasetList.add(diffDataset);
			}

			// ascending
			for (int i = datasetList.size() - 2; i >= 0; i--) {
				long[] previousLine = datasetList.get(i + 1);
				long[] currentLine = datasetList.get(i);
				currentLine[currentLine.length - 1] = previousLine[previousLine.length - 1]
						+ currentLine[currentLine.length - 2];
			}
			print(datasetList);
			return firstLineWithEmptySlot[firstLineWithEmptySlot.length - 1];
		}

		public long findPreviousValue() {
			List<long[]> datasetList = new ArrayList<>();

			long[] firstLineWithEmptySlot = new long[dataset.length + 1];
			for (int i = 0; i < dataset.length; i++) {
				firstLineWithEmptySlot[i + 1] = dataset[i];
			}
			datasetList.add(firstLineWithEmptySlot);

			// descending
			while (!lastLineIsOnlyZeroes(datasetList)) {
				long[] lastLine = datasetList.get(datasetList.size() - 1);
				long[] diffDataset = new long[lastLine.length - 1];
				for (int i = 1; i < diffDataset.length; i++) {
					diffDataset[i] = lastLine[i + 1] - lastLine[i];

				}
				datasetList.add(diffDataset);
			}

			// ascending
			for (int i = datasetList.size() - 2; i >= 0; i--) {
				long[] previousLine = datasetList.get(i + 1);
				long[] currentLine = datasetList.get(i);
				currentLine[0] = currentLine[1] - previousLine[0];
			}
			print(datasetList);
			return firstLineWithEmptySlot[0];
		}

		private boolean lastLineIsOnlyZeroes(final List<long[]> datasetList) {
			return Arrays.stream(datasetList.get(datasetList.size() - 1)).allMatch(i -> i == 0);
		}

	}

	public static List<History> parse(final String input) {
		return Arrays.stream(Utils.splitLines(input)).map(History::parse).toList();
	}

	private static void print(final List<long[]> dataset) {
		long[] firstLine = dataset.get(0);
		int maxSize = Arrays.stream(firstLine).mapToObj(String::valueOf).mapToInt(String::length).max().orElse(0);
		String maxSizeFormat = "%" + maxSize + "s";
		String blank = String.format(maxSizeFormat, "");

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dataset.size(); i++) {
			sb.append(blank.repeat(i));
			long[] line = dataset.get(i);
			for (int j = 0; j < line.length; j++) {
				if (j != 0) {
					sb.append(blank);
				}
				sb.append(String.format(maxSizeFormat, line[j]));
			}
			sb.append(System.lineSeparator());
		}
		System.out.println("=".repeat(maxSize * 2 * firstLine.length - maxSize));
		System.out.println(sb);
	}
}
