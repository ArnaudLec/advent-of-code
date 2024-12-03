package day01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.TestUtils;

class Day01Test {

	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day01-input.txt");

	private static final String EXAMPLE = """
			3   4
			4   3
			2   5
			1   3
			3   9
			3   3
			""";

	private static String INPUT_FILE;

	@BeforeAll
	static void readInputFile() throws Exception {
		INPUT_FILE = Files.readString(INPUT_FILE_PATH);
	}

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(11, Day01.parse(EXAMPLE).getDistance());
		}

		@Test
		void inputFile() throws Exception {
			System.out.println(Day01.parse(INPUT_FILE).getDistance());
		}
	}

	@Nested
	class Part2 {

		@Test
		void example() throws Exception {
			assertEquals(31, Day01.parse(EXAMPLE).getSimilarityScore());
		}

		@Test
		void inputFile() throws Exception {
			System.out.println(Day01.parse(INPUT_FILE).getSimilarityScore());
		}
	}

}
