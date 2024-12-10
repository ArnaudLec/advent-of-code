package day09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.TestUtils;

class Day09Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day09-input.txt");

	private static final String EXAMPLE = "2333133121414131402";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(1928, Day09.getChecksumAfterCompacting(EXAMPLE));
		}

		@Test
		void inputFile() throws Exception {
			String file = Files.readString(INPUT_FILE_PATH);

			System.out.println(Day09.getChecksumAfterCompacting(file));
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
