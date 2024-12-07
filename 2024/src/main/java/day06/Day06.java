package day06;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

import utils.Part;
import utils.Utils;

class Day06 {

	public static int calcDistinctPositions(String input) {
		char[][] map = Utils.to2dArray(input);
		return getGuardWalkingPositions(map, (pos, _) -> pos, Part.PART_1).size();
	}

	public static int getPossibleObstructionPositions(String input) {
		char[][] map = Utils.to2dArray(input);
		Set<Position> obstructionPositions = new HashSet<>();
		Position startingPosition = getStartingPosition(map);

		Set<Position> walkingPositions = getGuardWalkingPositions(map, (pos, _) -> pos, Part.PART_1);
		walkingPositions.remove(startingPosition);

		for (Position p : walkingPositions) {
			map[p.x][p.y] = '#';
			Set<PositionWithDirection> guardPositionWithDirections = getGuardWalkingPositions(map,
					PositionWithDirection::new, Part.PART_2);
			if (guardPositionWithDirections.isEmpty()) {
				obstructionPositions.add(p);
			}
			map[p.x][p.y] = '.';
		}

		return obstructionPositions.size();
	}

	private static <X> Set<X> getGuardWalkingPositions(char[][] map,
			BiFunction<Position, Direction, X> accumulatorFunction, Part part) {
		Set<X> walkedByPosition = new HashSet<>();
		Position startingPosition = getStartingPosition(map);
		Direction dir = Direction.NORTH;
		walkedByPosition.add(accumulatorFunction.apply(startingPosition, dir));

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
				Position position = new Position(x, y);
				X mappedPosition = accumulatorFunction.apply(position, dir);
				boolean alreadyPresent = !walkedByPosition.add(mappedPosition);
				if (alreadyPresent && part == Part.PART_2) {
					return Set.of();
				}
			} else {
				x -= dir.verticalShift;
				y -= dir.horizontalShift;
				dir = dir.getNext();
			}
		} while (x < map.length && y < map[0].length);

		return walkedByPosition;
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

	private record PositionWithDirection(Position pos, Direction dir) {

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
