package day08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.Part;
import utils.TestUtils;

class Day08Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day08-input.txt");

	private static final String EXAMPLE = """
			............
			........0...
			.....0......
			.......0....
			....0.......
			......A.....
			............
			............
			........A...
			.........A..
			............
			............
			""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(14, calc(EXAMPLE));
		}

		@Test
		void inputFile() throws Exception {
			String file = Files.readString(INPUT_FILE_PATH);
			System.out.println(calc(file));
		}

		private static int calc(String input) {
			return Day08.antennasMap(input).getAntinodesMap(Part.PART_1).getUniquePositions().size();
		}
	}

	@Nested
	class Part2 {

		@Test
		void example() throws Exception {
			assertEquals(34, calc(EXAMPLE));
		}

		@Test
		void inputFile() throws Exception {
			String file = Files.readString(INPUT_FILE_PATH);
			System.out.println(calc(file));
		}

		private static int calc(String input) {
			return Day08.antennasMap(input).getAntinodesMap(Part.PART_2).getUniquePositions().size();
		}
	}

}
