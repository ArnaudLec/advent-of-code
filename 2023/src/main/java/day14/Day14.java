package day14;

import java.util.HashMap;
import java.util.Map;

import utils.Utils;

class Day14 {

	public static class Platform {

		private char[][] array;

		public Platform(String platform) {
			this.array = Utils.to2dArray(platform);
		}

		public void tiltNorth() {
			for (int row = 1; row < array.length; row++) {
				for (int col = 0; col < array[row].length; col++) {
					if (array[row][col] == 'O') {
						int shift = 0;
						while (row - shift > 0 && array[row - shift - 1][col] == '.') {
							shift++;
						}

						if (shift > 0 && array[row - shift][col] == '.') {
							array[row - shift][col] = 'O';
							array[row][col] = '.';
						}
					}
				}
			}
		}

		public void tiltCycle() {
			for (int i = 0; i < 4; i++) {
				tiltNorth();
				array = Utils.rotateRight(array);
			}
		}

		public void tilteCycles(long times) {
			Map<String, Long> cache = new HashMap<>();
			for (long i = 0; i < times; i++) {
				tiltCycle();

				String currentState = this.toString();
				Long currentDelta = cache.get(currentState);
				if (currentDelta != null) {
					long delta = i - currentDelta;
					i += delta * ((times - i) / delta);
				}
				cache.put(currentState, i);
			}
		}

		@Override
		public String toString() {
			return Utils.toString(array);
		}

		public long getLoad() {
			final int rows = array.length;

			long result = 0l;
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < array[row].length; col++) {
					if (array[row][col] == 'O') {
						result += rows - row;
					}
				}
			}
			return result;
		}

	}
}
