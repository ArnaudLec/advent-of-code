package day04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.TestUtils;

class Day04Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day04-input.txt");

	private static final String EXAMPLE = """
			MMMSXXMASM
			MSAMXMSMSA
			AMXSXMAAMM
			MSAMASMSMX
			XMASAMXAMM
			XXAMMXXAMA
			SMSMSASXSS
			SAXAMASAAA
			MAMMMXMMMM
			MXMXAXMASX
			""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(18, Day04.countXmases(EXAMPLE));
		}

		@Test
		void inputFile() throws Exception {
			String file = Files.readString(INPUT_FILE_PATH);

			System.out.println(Day04.countXmases(file));
		}
	}

	@Nested
	class Part2 {

		@Test
		void example() throws Exception {
			assertEquals(9, Day04.countMases(EXAMPLE));
		}

		@Test
		void inputFile() throws Exception {
			String file = Files.readString(INPUT_FILE_PATH);

			System.out.println(Day04.countMases(file));
		}
	}

}
