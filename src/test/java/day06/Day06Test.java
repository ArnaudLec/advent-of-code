package day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import day06.Day06.Race;
import utils.Utils;

public class Day06Test {
	private static final String[] EXAMPLE = Utils.splitLines("""
			Time:      7  15   30
			Distance:  9  40  200
						""");

	private static final String[] INPUT = Utils.splitLines("""
			Time:        49     87     78     95
			Distance:   356   1378   1502   1882
						""");

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			List<Race> races = Day06.parseRacesPart1(EXAMPLE);

			Race race1 = races.get(0);
			assertEquals(0, race1.pressButton(0));
			assertEquals(6, race1.pressButton(1));
			assertEquals(10, race1.pressButton(2));
			assertEquals(12, race1.pressButton(3));
			assertEquals(12, race1.pressButton(4));
			assertEquals(10, race1.pressButton(5));
			assertEquals(6, race1.pressButton(6));
			assertEquals(0, race1.pressButton(7));

			long race1NumbersOfWaysToWin = race1.numbersOfWaysToWin();
			assertEquals(4, race1NumbersOfWaysToWin);

			Race race2 = races.get(1);
			long race2NumbersOfWaysToWin = race2.numbersOfWaysToWin();
			assertEquals(8, race2NumbersOfWaysToWin);

			Race race3 = races.get(1);
			long race3NumbersOfWaysToWin = race3.numbersOfWaysToWin();
			assertEquals(8, race3NumbersOfWaysToWin);

			long result = calcAnswer(races);
			assertEquals(288L, result);
		}

		@Test
		void inputFile() throws Exception {
			List<Race> races = Day06.parseRacesPart1(INPUT);
			System.out.println(calcAnswer(races));
		}

		private long calcAnswer(final List<Race> races) {
			return races.stream().mapToLong(Race::numbersOfWaysToWin).reduce(Math::multiplyExact).getAsLong();
		}

	}

	@Nested
	class Part2 {

		@Test
		void example() throws Exception {
			Race race = Day06.parseRacePart2(EXAMPLE);
			assertEquals(71503, race.numbersOfWaysToWin());
		}

		@Test
		void inputFile() throws Exception {
			Race race = Day06.parseRacePart2(INPUT);
			System.out.println(race.numbersOfWaysToWin());
		}
	}

}
