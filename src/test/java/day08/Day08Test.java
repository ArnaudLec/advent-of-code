package day08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.TestUtils;

public class Day08Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day08-input.txt");

	private static final String EXAMPLE = """
			RL

			AAA = (BBB, CCC)
			BBB = (DDD, EEE)
			CCC = (ZZZ, GGG)
			DDD = (DDD, DDD)
			EEE = (EEE, EEE)
			GGG = (GGG, GGG)
			ZZZ = (ZZZ, ZZZ)
			 """;

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			int steps = Day08.countSteps(EXAMPLE);
			assertEquals(2, steps);
		}

		@Test
		void example2() throws Exception {
			String example2 = """
					LLR

					AAA = (BBB, BBB)
					BBB = (AAA, ZZZ)
					ZZZ = (ZZZ, ZZZ)
					""";

			int steps = Day08.countSteps(example2);
			assertEquals(6, steps);
		}

		@Test
		void inputFile() throws Exception {
			int steps = Day08.countSteps(Files.readString(INPUT_FILE_PATH));
			System.out.println(steps);
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
