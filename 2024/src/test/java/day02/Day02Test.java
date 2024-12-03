package day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.Part;
import utils.TestUtils;

class Day02Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day02-input.txt");

	private static final String EXAMPLE = """
			7 6 4 2 1
			1 2 7 8 9
			9 7 6 2 1
			1 3 2 4 5
			8 6 4 4 1
			1 3 6 7 9
			""";

	private static String inputFile;

	@BeforeAll
	static void readInputFile() throws Exception {
		inputFile = Files.readString(INPUT_FILE_PATH);
	}

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(2, getSafeReports(EXAMPLE, Part.PART_1));
		}

		@Test
		void inputFile() throws Exception {
			System.out.println(getSafeReports(inputFile, Part.PART_1));
		}

	}

	@Nested
	class Part2 {

		@Test
		void example() throws Exception {
			assertEquals(4, getSafeReports(EXAMPLE, Part.PART_2));
		}

		@Test
		void inputFile() throws Exception {
			long safeReports = getSafeReports(inputFile, Part.PART_2);
			System.out.println(safeReports);
		}
	}

	long getSafeReports(String input, Part part) {
		return Arrays.stream(Day02.parse(input)).filter(report -> report.isSafe(part)).count();
	}
}
