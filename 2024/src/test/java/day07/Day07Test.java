package day07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import day07.Day07.Operation;
import utils.TestUtils;

class Day07Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day07-input.txt");

	private static final String EXAMPLE = """
			190: 10 19
			3267: 81 40 27
			83: 17 5
			156: 15 6
			7290: 6 8 6 15
			161011: 16 10 13
			192: 17 8 14
			21037: 9 7 18 13
			292: 11 6 16 20
			""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(3749, Day07.calcCalibrationStatus(EXAMPLE, Set.of(Operation.ADD, Operation.MULTIPLY)));
		}

		@Test
		void inputFile() throws Exception {
			String file = Files.readString(INPUT_FILE_PATH);

			System.out.println(Day07.calcCalibrationStatus(file, Set.of(Operation.ADD, Operation.MULTIPLY)));
		}
	}

	@Nested
	class Part2 {

		@Test
		void example() throws Exception {
			assertEquals(11387, Day07.calcCalibrationStatus(EXAMPLE, Set.of(Operation.values())));
		}

		@Test
		void inputFile() throws Exception {
			String file = Files.readString(INPUT_FILE_PATH);

			System.out.println(Day07.calcCalibrationStatus(file, Set.of(Operation.values())));
		}
	}

}
