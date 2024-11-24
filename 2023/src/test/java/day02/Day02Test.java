package day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import utils.TestUtils;

class Day02Test {

	private static final String[] EXAMPLES = """
			Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
			Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
			Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
			Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
			Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
						""".split("\n");

	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day02-input.txt");

	@Nested
	class Part1 {

		@ParameterizedTest
		@CsvSource(textBlock = """
				Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green                     IS EXPECTING true
				Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue           IS EXPECTING true
				Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red   IS EXPECTING false
				Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red   IS EXPECTING false
				Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green                     IS EXPECTING true
					""", delimiterString = "IS EXPECTING")
		void examples(final String gameStr, final boolean expectedPossible) throws Exception {
			Game game = new Game(gameStr);
			assertEquals(expectedPossible, isPossibleGame(game));
		}

		@Test
		void sumExamples() throws Exception {
			int sum = sumPossibleGames(Arrays.stream(EXAMPLES));

			assertEquals(8, sum);
		}

		@Test
		void sumInputFile() throws Exception {
			int sum = sumPossibleGames(Files.lines(INPUT_FILE_PATH));

			System.out.println(sum);
		}

		private int sumPossibleGames(final Stream<String> stream) {
			return stream.map(Game::new).filter(this::isPossibleGame).mapToInt(Game::getId).sum();
		}

		private boolean isPossibleGame(final Game game) {
			return game.isPossible(12, 13, 14);
		}
	}

	@Nested
	class Part2 {
		@ParameterizedTest
		@CsvSource(textBlock = """
				Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green                     IS EXPECTING 48
				Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue           IS EXPECTING 12
				Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red   IS EXPECTING 1560
				Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red   IS EXPECTING 630
				Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green                     IS EXPECTING 36
					""", delimiterString = "IS EXPECTING")
		void examples(final String gameStr, final int expectedPower) throws Exception {
			Game game = new Game(gameStr);
			assertEquals(expectedPower, game.powerCubes());
		}

		@Test
		void sumExample() throws Exception {
			int sum = sumPowers(Arrays.stream(EXAMPLES));

			assertEquals(2286, sum);
		}

		@Test
		void sumInputFile() throws Exception {
			int sum = sumPowers(Files.lines(INPUT_FILE_PATH));

			System.out.println(sum);
		}

		private int sumPowers(final Stream<String> stream) {
			return stream.map(Game::new).mapToInt(Game::powerCubes).sum();
		}
	}

}
