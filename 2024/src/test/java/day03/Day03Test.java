package day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.TestUtils;

class Day03Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day03-input.txt");

	private static final String EXAMPLE = """
			xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
			""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(161, Day03.calc(EXAMPLE));
		}

		@Test
		void inputFile() throws Exception {
			String file = Files.readString(INPUT_FILE_PATH);
			System.out.println(Day03.calc(file));
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
