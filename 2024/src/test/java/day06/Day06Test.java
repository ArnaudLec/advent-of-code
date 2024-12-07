package day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.TestUtils;

class Day06Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day06-input.txt");

	private static final String EXAMPLE = """
			....#.....
			.........#
			..........
			..#.......
			.......#..
			..........
			.#..^.....
			........#.
			#.........
			......#...
			""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(41, Day06.calcDistinctPositions(EXAMPLE));
		}

		@Test
		void inputFile() throws Exception {
			String file = Files.readString(INPUT_FILE_PATH);

			System.out.println(Day06.calcDistinctPositions(file));
		}
	}

	@Nested
	class Part2 {

		@Test
		void example() throws Exception {
			assertEquals(0, EXAMPLE);
		}

		@Test
		void inputFile() throws Exception {
			String file = Files.readString(INPUT_FILE_PATH);

			System.out.println();
		}
	}

}
