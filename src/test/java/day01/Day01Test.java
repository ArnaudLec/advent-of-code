package day01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import utils.TestUtils;

class Day01Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day01-input.txt");

	@Nested
	class Part1 {

		@ParameterizedTest
		@CsvSource(textBlock = """
				1abc2         ,	12
				pqr3stu8vwx   ,	38
				a1b2c3d4e5f   ,	15
				treb7uchet    ,	77
					""")
		void exemples(final String str, final int expectedResult) throws Exception {
			assertEquals(expectedResult, Day01.part1Compute(str));
		}

		@Test
		void inputFile() throws Exception {
			int sum = Files.lines(INPUT_FILE_PATH).mapToInt(Day01::part1Compute).sum();
			System.out.println(sum);
		}
	}

	@Nested
	class Part2 {

		@ParameterizedTest
		@CsvSource(textBlock = """
				two1nine            , 29
				eightwothree        , 83
				abcone2threexyz     , 13
				xtwone3four         , 24
				4nineeightseven2    , 42
				zoneight234         , 14
				7pqrstsixteen       , 76
						""")
		void exemples(final String str, final int expectedResult) throws Exception {
			assertEquals(expectedResult, Day01.part2Compute(str));
		}

		@Test
		void inputFile() throws Exception {
			int sum = Files.lines(INPUT_FILE_PATH).mapToInt(Day01::part2Compute).sum();
			System.out.println(sum);
		}
	}
}
