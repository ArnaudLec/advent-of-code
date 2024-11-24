package day13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.TestUtils;

class Day13Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day13-input.txt");

	private static final String EXAMPLE = """
			#.##..##.
			..#.##.#.
			##......#
			##......#
			..#.##.#.
			..##..##.
			#.#.##.#.

			#...##..#
			#....#..#
			..##..###
			#####.##.
			#####.##.
			..##..###
			#....#..#
						""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(405, Day13.sumReflections(EXAMPLE));
		}

		@Test
		void inputFile() throws Exception {
			System.out.println(Day13.sumReflections(Files.readString(INPUT_FILE_PATH)));
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
