package day08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utils.Part;
import utils.Utils;

class Day08 {

	public static AntennasMap antennasMap(String input) {
		char[][] map = Utils.to2dArray(input);

		Map<Character, List<Position>> positions = new TreeMap<>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				char c = map[i][j];
				if (c != '.') {
					positions.computeIfAbsent(c, _ -> new ArrayList<>()).add(new Position(i, j));
				}
			}
		}

		return new AntennasMap(map.length, map[0].length, positions);
	}

	public record Position(int row, int col) implements Comparable<Position> {
		@Override
		public final String toString() {
			return "[" + row + "," + col + "]";
		}

		@Override
		public int compareTo(Position other) {
			int rowDiff = row - other.row;
			if (rowDiff != 0) {
				return rowDiff;
			}
			return col - other.col;
		}

		public boolean isInBounds(int rows, int cols) {
			return row >= 0 && row < rows && col >= 0 && col < cols;
		}
	}

	public record AntennasMap(int rows, int cols, Map<Character, List<Position>> positions) {
		@Override
		public String toString() {
			return Utils.mapOfCollectionsToString(positions);
		}

		public AntinodesMap getAntinodesMap(Part part) {
			Map<Character, Set<Position>> antinodesMap = new TreeMap<>();

			for (Entry<Character, List<Position>> entry : positions.entrySet()) {
				Character character = entry.getKey();
				List<Position> antennas = entry.getValue();

				Set<Position> antinodes = antinodesMap.computeIfAbsent(character, _ -> new TreeSet<>());
				for (int i = 0; i < antennas.size() - 1; i++) {
					Position a = antennas.get(i);
					for (int j = i + 1; j < antennas.size(); j++) {
						Position b = antennas.get(j);

						int rowDiff = a.row - b.row;
						int colDiff = a.col - b.col;

						int factor = part == Part.PART_1 ? 1 : 0;
						boolean stop = false;
						do {
							List<Position> antinodePositions = Stream
									.of(new Position(a.row + factor * rowDiff, a.col + factor * colDiff),
											new Position(b.row - factor * rowDiff, b.col - factor * colDiff))
									.filter(pos -> pos.isInBounds(rows, cols)).toList();

							antinodes.addAll(antinodePositions);
							stop = part == Part.PART_1 || antinodePositions.isEmpty();
							factor++;
						} while (!stop);
					}
				}
			}
			return new AntinodesMap(antinodesMap);
		}
	}

	public record AntinodesMap(Map<Character, Set<Position>> positions) {
		@Override
		public String toString() {
			return Utils.mapOfCollectionsToString(positions);
		}

		public Set<Position> getUniquePositions() {
			return positions.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
		}
	}
}
