package day09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import day09.Day09.History;
import utils.TestUtils;

public class Day09Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day09-input.txt");

	private static final String EXAMPLE = """
			0 3 6 9 12 15
			1 3 6 10 15 21
			10 13 16 21 30 45
					""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			List<History> histories = Day09.parse(EXAMPLE);
			long result = sumNextValues(histories);
			assertEquals(114, result);
		}

		@Test
		void inputFile() throws Exception {
			List<History> histories = Day09.parse(Files.readString(INPUT_FILE_PATH));
			long result = sumNextValues(histories);
			System.out.println(result);
		}

		private long sumNextValues(final List<History> histories) {
			return histories.stream().mapToLong(History::findNextValue).sum();
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
