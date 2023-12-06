package day05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import day05.Day05.Almanac;
import day05.Day05.Result;
import utils.TestUtils;

class Day05Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day05-input.txt");

	private static final String EXAMPLE = """
			seeds: 79 14 55 13

			seed-to-soil map:
			50 98 2
			52 50 48

			soil-to-fertilizer map:
			0 15 37
			37 52 2
			39 0 15

			fertilizer-to-water map:
			49 53 8
			0 11 42
			42 0 7
			57 7 4

			water-to-light map:
			88 18 7
			18 25 70

			light-to-temperature map:
			45 77 23
			81 45 19
			68 64 13

			temperature-to-humidity map:
			0 69 1
			1 0 69

			humidity-to-location map:
			60 56 37
			56 93 4
						""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			Almanac almanac = Day05.parse(EXAMPLE);

			List<Result> results = almanac.streamResults().toList();

			assertResult(79, 81, 81, 81, 74, 78, 78, 82, results);
			assertResult(14, 14, 53, 49, 42, 42, 43, 43, results);
			assertResult(55, 57, 57, 53, 46, 82, 82, 86, results);
			assertResult(13, 13, 52, 41, 34, 34, 35, 35, results);

			Result lowestLocationResult = getLowestLocation(results.stream());
			assertEquals(35L, lowestLocationResult.getLocation());
		}

		private void assertResult(final int expectedSeed, final int expectedSoil, final int expectedFertilizer,
				final int expectedWater, final int expectedLight, final int expectedTemperature,
				final int expectedHumidity, final int expectedLocation, final List<Result> results) {

			Result result = results.stream()
					.filter(r -> r.getSeed() == expectedSeed)
					.findFirst()
					.orElseThrow(() -> new IllegalStateException("No result for seed " + expectedSeed));

			assertEquals(expectedSoil, result.getSoil(), "soil");
			assertEquals(expectedFertilizer, result.getFertilizer(), "fertilizer");
			assertEquals(expectedWater, result.getWater(), "water");
			assertEquals(expectedLight, result.getLight(), "light");
			assertEquals(expectedTemperature, result.getTemperature(), "temperature");
			assertEquals(expectedHumidity, result.getHumidity(), "humidity");
			assertEquals(expectedLocation, result.getLocation(), "location");
		}

		@Test
		void inputFile() throws Exception {
			Almanac almanac = Day05.parse(Files.readString(INPUT_FILE_PATH));
			Result lowestLocationResult = getLowestLocation(almanac.streamResults());
			System.out.println(lowestLocationResult);
		}

		private Result getLowestLocation(final Stream<Result> streamResults) {
			return streamResults.sorted(Comparator.comparingLong(Result::getLocation)).findFirst().orElseThrow();
		}

	}

	@Nested
	class Part2 {

		@Test
		void example() throws Exception {

		}

		@Test
		void inputFile() throws Exception {

		}
	}

}
