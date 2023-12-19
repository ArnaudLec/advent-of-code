package day11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.TestUtils;

class Day11Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day11-input.txt");

	private static final String EXAMPLE = """
			...#......
			.......#..
			#.........
			..........
			......#...
			.#........
			.........#
			..........
			.......#..
			#...#.....
						""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(374, Day11.parse(EXAMPLE).sumShortestPathsBetweenGalaxies(2));
		}

		@Test
		void inputFile() throws Exception {
			System.out.println(Day11.parse(Files.readString(INPUT_FILE_PATH)).sumShortestPathsBetweenGalaxies(2));
		}
	}

	@Nested
	class Part2 {

		@Test
		void example1() throws Exception {
			assertEquals(1030, Day11.parse(EXAMPLE).sumShortestPathsBetweenGalaxies(10));
		}

		@Test
		void example2() throws Exception {
			assertEquals(8410, Day11.parse(EXAMPLE).sumShortestPathsBetweenGalaxies(100));
		}

		@Test
		void inputFile() throws Exception {
			System.out
					.println(Day11.parse(Files.readString(INPUT_FILE_PATH)).sumShortestPathsBetweenGalaxies(1_000_000));
		}
	}

}
