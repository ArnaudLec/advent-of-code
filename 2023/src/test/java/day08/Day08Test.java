package day08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import day08.Day08.WastelandMap;
import utils.TestUtils;

public class Day08Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day08-input.txt");

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			WastelandMap wastelandMap = WastelandMap.parse("""
					RL

					AAA = (BBB, CCC)
					BBB = (DDD, EEE)
					CCC = (ZZZ, GGG)
					DDD = (DDD, DDD)
					EEE = (EEE, EEE)
					GGG = (GGG, GGG)
					ZZZ = (ZZZ, ZZZ)
					 """);
			assertEquals(2, wastelandMap.countStepsPart1());
		}

		@Test
		void example2() throws Exception {
			String example2 = """
					LLR

					AAA = (BBB, BBB)
					BBB = (AAA, ZZZ)
					ZZZ = (ZZZ, ZZZ)
					""";

			WastelandMap wastelandMap = WastelandMap.parse(example2);
			assertEquals(6, wastelandMap.countStepsPart1());
		}

		@Test
		void inputFile() throws Exception {
			WastelandMap wastelandMap = WastelandMap.parse(Files.readString(INPUT_FILE_PATH));
			System.out.println(wastelandMap.countStepsPart1());
		}
	}

	@Nested
	class Part2 {

		@Test
		void example() throws Exception {
			WastelandMap wastelandMap = WastelandMap.parse("""
					LR

					11A = (11B, XXX)
					11B = (XXX, 11Z)
					11Z = (11B, XXX)
					22A = (22B, XXX)
					22B = (22C, 22C)
					22C = (22Z, 22Z)
					22Z = (22B, 22B)
					XXX = (XXX, XXX)
					""");
			assertEquals(6, wastelandMap.countStepsPart2());
		}

		@Test
		void inputFile() throws Exception {
			WastelandMap wastelandMap = WastelandMap.parse(Files.readString(INPUT_FILE_PATH));
			System.out.println(wastelandMap.countStepsPart2());
		}

	}

}
