package day11;

import java.util.ArrayList;
import java.util.List;

import utils.Utils;

class Day11 {

	public record Galaxy(int number, int row, int col) {

	}

	public record Universe(List<Galaxy> galaxies, int[] expandedRows, int[] expandedCols) {

		public long sumShortestPathsBetweenGalaxies(final int expansionSize) {
			long sumOfPathsLength = 0;

			for (int i = 0; i < galaxies.size(); i++) {
				Galaxy firstGalaxy = galaxies.get(i);
				for (int j = i + 1; j < galaxies.size(); j++) {
					Galaxy secondGalaxy = galaxies.get(j);
					long pathLength = Math.abs(secondGalaxy.row - firstGalaxy.row)
							+ Math.abs(secondGalaxy.col - firstGalaxy.col);

					long rowAddSpace = getAdditionalExpandedSpaceBetween(firstGalaxy.row, secondGalaxy.row,
							expandedRows, expansionSize);
					long colAddSpace = getAdditionalExpandedSpaceBetween(firstGalaxy.col, secondGalaxy.col,
							expandedCols, expansionSize);

					long expandedAdditionalSpace = rowAddSpace + colAddSpace;

					pathLength += expandedAdditionalSpace;

					System.out.println(String.format("Between galaxy %s and galaxy %s:  %s", firstGalaxy.number,
							secondGalaxy.number, pathLength));
					sumOfPathsLength += pathLength;
				}
			}

			return sumOfPathsLength;
		}

		private static long getAdditionalExpandedSpaceBetween(final int val1, final int val2, final int[] expanded,
				final int expansionSize) {
			long expandedAdditionalSpace = 0;
			int minRow = Math.min(val1, val2);
			int maxRow = Math.max(val1, val2);
			for (int rowBetween = minRow + 1; rowBetween < maxRow; rowBetween++) {
				if (Utils.arrayContains(expanded, rowBetween) != -1) {
					expandedAdditionalSpace += (expansionSize - 1);
				}
			}
			return expandedAdditionalSpace;
		}

		@Override
		public String toString() {
			return galaxies.toString();
		}
	}

	public static Universe parse(final String input) {
		char[][] universe = Utils.to2dArray(input);

		List<Integer> rowsToExpand = new ArrayList<>();
		List<Integer> colsToExpand = new ArrayList<>();

		List<Galaxy> notExpandedGalaxies = new ArrayList<>();
		int count = 0;

		for (int row = 0; row < universe.length; row++) {
			boolean emptyRow = true;
			for (int col = 0; col < universe[row].length; col++) {
				if (universe[row][col] == '#') {
					count++;
					notExpandedGalaxies.add(new Galaxy(count, row, col));
					emptyRow = false;
				} else if (emptyRow && col == universe[row].length - 1) {
					rowsToExpand.add(row);
				}
			}
		}

		for (int col = 0; col < universe[0].length; col++) {
			for (int row = 0; row < universe.length; row++) {
				if (universe[row][col] == '#') {
					break;
				}
				if (row == universe[row].length - 1) {
					colsToExpand.add(col);
				}
			}
		}

		return new Universe(notExpandedGalaxies, Utils.toIntArray(rowsToExpand), Utils.toIntArray(colsToExpand));
	}

}
