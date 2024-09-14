package day14;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.ValueSource;

import day14.Day14.Platform;
import utils.TestUtils;

class Day14Test {

	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day14-input.txt");

	private static final String EXAMPLE = """
			O....#....
			O.OO#....#
			.....##...
			OO.#O....O
			.O.....O#.
			O.#..O.#.#
			..O..#O..O
			.......O..
			#....###..
			#OO..#....""";

	private static final String TILTED_EXPECTED_EXAMPLE = """
			OOOO.#.O..
			OO..#....#
			OO..O##..O
			O..#.OO...
			........#.
			..#....#.#
			..O..#.O.O
			..O.......
			#....###..
			#....#....""";

	private static final String EXPECTED_1_CYCLE_TILT_EXAMPLE = """
			.....#....
			....#...O#
			...OO##...
			.OO#......
			.....OOO#.
			.O#...O#.#
			....O#....
			......OOOO
			#...O###..
			#..OO#....""";
	private static final String EXPECTED_2_CYCLES_TILT_EXAMPLE = """
			.....#....
			....#...O#
			.....##...
			..O#......
			.....OOO#.
			.O#...O#.#
			....O#...O
			.......OOO
			#..OO###..
			#.OOO#...O""";
	private static final String EXPECTED_3_CYCLES_TILT_EXAMPLE = """
			.....#....
			....#...O#
			.....##...
			..O#......
			.....OOO#.
			.O#...O#.#
			....O#...O
			.......OOO
			#...O###.O
			#.OOO#...O""";

	@Nested
	class Part1 {

		@Test
		void platformTilt() {
			Platform platform = new Platform(EXAMPLE);
			platform.tiltNorth();

			assertThat(platform.toString()).isEqualToNormalizingNewlines(TILTED_EXPECTED_EXAMPLE);
		}

		@Test
		void example() {
			Platform platform = new Platform(EXAMPLE);
			platform.tiltNorth();

			assertEquals(136, platform.getLoad());
		}

		@Test
		void inputFile() throws Exception {
			Platform platform = new Platform(Files.readString(INPUT_FILE_PATH));
			platform.tiltNorth();

			System.out.println(platform.getLoad());
		}
	}

	@Nested
	class Part2 {

		@ParameterizedTest(name = "{index} cycle(s)")
		@ValueSource(strings = { EXPECTED_1_CYCLE_TILT_EXAMPLE, EXPECTED_2_CYCLES_TILT_EXAMPLE,
				EXPECTED_3_CYCLES_TILT_EXAMPLE })
		void exampleCycles(String expectedResult, ArgumentsAccessor argsAccessor) {
			Platform platform = new Platform(EXAMPLE);
			platform.tilteCycles(argsAccessor.getInvocationIndex());

			assertThat(platform.toString()).isEqualToNormalizingNewlines(expectedResult);
		}

		@Test
		void example() {
			Platform platform = new Platform(EXAMPLE);
			platform.tilteCycles(1_000_000_000L);

			assertEquals(64, platform.getLoad());
		}

		@Test
		void inputFile() throws Exception {
			Platform platform = new Platform(Files.readString(INPUT_FILE_PATH));
			platform.tilteCycles(1_000_000_000L);

			System.out.println(platform.getLoad());
		}
	}

}
