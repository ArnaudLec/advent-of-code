package day06;

import java.util.HashSet;
import java.util.Set;

import utils.Utils;

class Day06 {

	public static int calcDistinctPositions(String input) {
		char[][] map = Utils.to2dArray(input);

		Set<Position> walkedByPosition = new HashSet<>();
		Position startingPosition = getStartingPosition(map);
		walkedByPosition.add(startingPosition);
		Direction dir = Direction.NORTH;

		int x = startingPosition.x;
		int y = startingPosition.y;

		do {
			x += dir.verticalShift;
			y += dir.horizontalShift;
			if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) {
				// guard went out of the map
				break;
			}
			if (map[x][y] != '#') {
				walkedByPosition.add(new Position(x, y));
			} else {
				x -= dir.verticalShift;
				y -= dir.horizontalShift;
				dir = dir.getNext();
			}
		} while (x < map.length && y < map[0].length);

		return walkedByPosition.size();
	}

	private static Position getStartingPosition(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == '^') {
					return new Position(i, j);
				}
			}
		}
		throw new IllegalArgumentException("Starting position '^' not found");
	}

	private record Position(int x, int y) {

	}

	private enum Direction {
		NORTH(-1, 0), EAST(0, +1), SOUTH(+1, 0), WEST(0, -1);

		final int horizontalShift;
		final int verticalShift;

		Direction(int verticalShift, int horizontalShift) {
			this.verticalShift = verticalShift;
			this.horizontalShift = horizontalShift;
		}

		Direction getNext() {
			if (ordinal() == 3) {
				return Direction.values()[0];
			}
			return Direction.values()[ordinal() + 1];
		}
	}
}
