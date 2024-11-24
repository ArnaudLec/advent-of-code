package day03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import utils.Utils;

public class Day03 {

	private record Symbol(char c, int row, int col) {
	}

	private record Part(int number, int length, int row, int col) {

		public Part(final String number, final int row, final int col) {
			this(Integer.parseInt(number), number.length(), row, col);
		}

		public boolean isNearbyAny(final Collection<Symbol> symbols) {
			return symbols.parallelStream().anyMatch(this::isNearby);
		}

		public boolean isNearby(final Symbol symbol) {
			if (Math.abs(this.row - symbol.row) > 1) {
				return false;
			}

			// symbol.col +/- 1 from an int
			int minCol = symbol.col - 1;
			int maxCol = symbol.col + 1;

			for (int i = 0; i < length; i++) {
				int checkedIntIndex = this.col + i;
				if (checkedIntIndex >= minCol && checkedIntIndex <= maxCol) {
					return true;
				}
			}

			return false;
		}

		@Override
		public String toString() {
			return String.format("Part %4s  @  %3s , %s", number, row, col);
		}
	}

	private record Gear(Symbol symbol, Part part1, Part part2) {
		public int ratio() {
			return part1.number * part2.number;
		}
	}

	public static int part1(final String str) {
		String[] lines = Utils.splitLines(str);
		Collection<Symbol> symbols = getSpecialChars(lines);
		Collection<Part> parts = getParts(lines);

		return parts.parallelStream().filter(part -> part.isNearbyAny(symbols)).mapToInt(Part::number).sum();
	}

	public static int part2(final String str) {
		String[] lines = Utils.splitLines(str);
		Collection<Symbol> symbols = getSpecialChars(lines);
		Collection<Part> parts = getParts(lines);

		return symbols.parallelStream()
				.filter(symbol -> symbol.c == '*')
				.map(maybeAGear -> toGears(maybeAGear, parts))
				.filter(Objects::nonNull)
				.mapToInt(Gear::ratio)
				.sum();
	}

	private static Gear toGears(final Symbol maybeAGear, final Collection<Part> parts) {
		Part[] gearParts = parts.parallelStream().filter(part -> part.isNearby(maybeAGear)).toArray(Part[]::new);
		if (gearParts.length != 2) {
			return null;
		}
		return new Gear(maybeAGear, gearParts[0], gearParts[1]);
	}

	private static Collection<Part> getParts(final String[] lines) {
		List<Part> piecesToSum = new ArrayList<>();

		for (int row = 0; row < lines.length; row++) {
			String line = lines[row];

			for (int col = 0, numberLen = 0; col < line.length(); col += 1 + numberLen, numberLen = 0) {
				char c = line.charAt(col);
				if (Character.isDigit(c)) {
					StringBuilder digitsAggr = new StringBuilder();
					digitsAggr.append(c);

					while ((col + numberLen + 1) < line.length()
							&& Character.isDigit(line.charAt(col + (++numberLen)))) {

						digitsAggr.append(line.charAt(col + numberLen));
					}
					piecesToSum.add(new Part(digitsAggr.toString(), row, col));
				}
			}
		}
		return piecesToSum;
	}

	private static List<Symbol> getSpecialChars(final String[] lines) {
		List<Symbol> specialCharsCoords = new ArrayList<>();

		for (int row = 0; row < lines.length; row++) {
			String line = lines[row];
			for (int col = 0; col < line.length(); col++) {
				char c = line.charAt(col);
				if (isSpecialChar(c)) {
					specialCharsCoords.add(new Symbol(c, row, col));
				}
			}
		}
		return specialCharsCoords;
	}

	private static boolean isSpecialChar(final char c) {
		if (c == '.' || Character.isDigit(c)) {
			return false;
		}
		return true;
	}

}
