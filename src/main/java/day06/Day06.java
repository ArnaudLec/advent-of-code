package day06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class Day06 {

	public record Race(long timeMs, long bestDistanceMillimeters) {

		public long pressButton(final long timeButtonIsPressedMs) {
			if (this.timeMs <= timeButtonIsPressedMs) {
				return 0;
			}
			// 1ms button press = +1 millimeter/s
			long speed = timeButtonIsPressedMs;
			long remainingDuration = this.timeMs - timeButtonIsPressedMs;

			return remainingDuration * speed;
		}

		public long numbersOfWaysToWin() {
			return LongStream.range(1, this.timeMs)
					.map(this::pressButton)
					.filter(distance -> distance > bestDistanceMillimeters)
					.count();
		}
	}

	public static List<Race> parseRacesPart1(final String[] input) {
		String[] times = removePrefix(input[0]).split("\\s+");
		String[] distances = removePrefix(input[1]).split("\\s+");

		List<Race> races = new ArrayList<>();
		for (int i = 0; i < times.length; i++) {
			String time = times[i];
			String distance = distances[i];
			if (time.isBlank() || distance.isBlank()) {
				continue;
			}
			races.add(new Race(Long.parseLong(time), Long.parseLong(distance)));
		}

		return races;
	}

	public static Race parseRacePart2(final String[] input) {
		String duration = input[0].replaceAll("\\s", "");
		String distance = input[1].replaceAll("\\s", "");
		return new Race(Long.parseLong(removePrefix(duration)), Long.parseLong(removePrefix(distance)));
	}

	private static String removePrefix(final String line) {
		int index = line.indexOf(':');
		return line.substring(index + 1);
	}
}
