package day15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.TestUtils;

class Day15Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day15-input.txt");

	private static final String EXAMPLE = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7";

	@Nested
	class Part1 {

		@Test
		void example0() throws Exception {
			assertEquals(52, Day15.calc("HASH"));
		}

		@Test
		void example() throws Exception {
			assertEquals(1320, Day15.calc(EXAMPLE));
		}

		@Test
		void inputFile() throws Exception {
			System.out.println(Day15.calc(Files.readString(INPUT_FILE_PATH)));
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
