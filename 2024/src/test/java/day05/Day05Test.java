package day05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.TestUtils;

class Day05Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day05-input.txt");

	private static final String EXAMPLE = """
			47|53
			97|13
			97|61
			97|47
			75|29
			61|13
			75|53
			29|13
			97|29
			53|29
			61|53
			97|53
			61|29
			47|13
			75|47
			97|75
			47|61
			75|61
			47|29
			75|13
			53|13

			75,47,61,53,29
			97,61,53,29,13
			75,29,13
			75,97,47,61,53
			61,13,29
			97,13,75,29,47
			""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(143, Day05.sumSortedMiddlePageNumber(EXAMPLE));
		}

		@Test
		void inputFile() throws Exception {
			String file = Files.readString(INPUT_FILE_PATH);

			System.out.println(Day05.sumSortedMiddlePageNumber(file));
		}
	}

	@Nested
	class Part2 {

		@Test
		void example() throws Exception {
			assertEquals(0, EXAMPLE);
		}

		@Test
		void inputFile() throws Exception {
			String file = Files.readString(INPUT_FILE_PATH);

			System.out.println();
		}
	}

}
