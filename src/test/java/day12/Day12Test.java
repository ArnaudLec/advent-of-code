package day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.TestUtils;

class Day12Test {

	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day12-input.txt");

	private static final String EXAMPLE = """
			???.### 1,1,3
			.??..??...?##. 1,1,3
			?#?#?#?#?#?#?#? 1,3,1,6
			????.#...#... 4,1,1
			????.######..#####. 1,6,5
			?###???????? 3,2,1
						""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(21, Day12.sumPossibleArrangements(EXAMPLE));
		}

		@Test
		void inputFile() throws Exception {
			System.out.println(Day12.sumPossibleArrangements(Files.readString(INPUT_FILE_PATH)));
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
