package day04;

import utils.Utils;

class Day04 {

	public static int countXmases(String input) {
		char[] followingChars = { 'M', 'A', 'S' };
		char[][] chars = Utils.to2dArray(input);
		int counter = 0;

		for (int line = 0; line < chars.length; line++) {
			for (int column = 0; column < chars[line].length; column++) {
				if (chars[line][column] == 'X') {
					for (Direction dir : Direction.values()) {
						for (int shift = 1; shift <= followingChars.length; shift++) {
							char c = dir.getChar(chars, line, column, shift);
							if (c != followingChars[shift - 1]) {
								break;
							}
							if (shift == followingChars.length) {
								// found
								counter++;
							}
						}
					}

				}
			}
		}

		return counter;
	}

	public static int countMases(String input) {
		char[][] chars = Utils.to2dArray(input);
		int counter = 0;

		for (int line = 0; line < chars.length; line++) {
			for (int column = 0; column < chars[line].length; column++) {
				if (chars[line][column] == 'A') {
					char nec = Direction.NORTH_EAST.getChar(chars, line, column, 1);
					char swc = Direction.SOUTH_WEST.getChar(chars, line, column, 1);
					if ((nec == 'M' || nec == 'S') && (swc == 'M' || swc == 'S') && nec != swc) {
						char nwc = Direction.NORTH_WEST.getChar(chars, line, column, 1);
						char sec = Direction.SOUTH_EAST.getChar(chars, line, column, 1);
						if ((nwc == 'M' || nwc == 'S') && (sec == 'M' || sec == 'S') && nwc != sec) {
							counter++;
						}
					}
				}
			}
		}

		return counter;
	}

	private enum Direction {
		NORTH(-1, 0), NORTH_EAST(-1, +1), EAST(0, +1), SOUTH_EAST(+1, +1), SOUTH(+1, 0), SOUTH_WEST(+1, -1),
		WEST(0, -1), NORTH_WEST(-1, -1);

		final int verticalShift;
		final int horizontalShift;

		Direction(int verticalShift, final int horizontalShift) {
			this.verticalShift = verticalShift;
			this.horizontalShift = horizontalShift;
		}

		char getChar(char[][] chars, int line, int column, int shift) {
			int lineWithShift = line + verticalShift * shift;
			if (lineWithShift >= chars.length || lineWithShift < 0) {
				return '\0';
			}
			int columnWithShift = column + horizontalShift * shift;
			if (columnWithShift >= chars[lineWithShift].length || columnWithShift < 0) {
				return '\0';
			}
			return chars[lineWithShift][columnWithShift];
		}
	}

}
