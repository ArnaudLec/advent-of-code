package day02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import day02.Day02.Report;
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
			assertEquals(442, getSafeReports(inputFile, Part.PART_1));
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
		void reportStartsWithBadLevel() throws Exception {
			Report report = new Day02.Report(new int[] { 12, 10, 11, 14, 17, 18, 20, 21 });
			assertTrue(report.isSafe(Part.PART_2));
		}

		@Test
		void inputFile() throws Exception {
			long safeReports = getSafeReports(inputFile, Part.PART_2);
			System.out.println(safeReports);
			assertThat(safeReports).isGreaterThan(484);
		}
	}

	long getSafeReports(String input, Part part) {
		return Arrays.stream(Day02.parse(input)).filter(report -> report.isSafe(part)).count();
	}
}
