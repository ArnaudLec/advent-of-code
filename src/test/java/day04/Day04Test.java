package day04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import day04.Day04.Card;
import utils.IntegerArrayConverter;
import utils.TestUtils;
import utils.Utils;

class Day04Test {

	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day04-input.txt");

	private static final String EXAMPLE = """
			Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
			Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
			Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
			Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
			Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
			Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
						""";

	@Nested
	class Part1 {

		@ParameterizedTest
		@CsvSource(textBlock = """
				Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53   IS EXPECTING 8
				Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19   IS EXPECTING 2
				Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1   IS EXPECTING 2
				Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83   IS EXPECTING 1
				Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36   IS EXPECTING 0
				Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11   IS EXPECTING 0
							""", delimiterString = "IS EXPECTING")
		void example(final String cardStr, final int expectedWorthPoints) throws Exception {
			assertEquals(expectedWorthPoints, Card.fromString(cardStr).getWorthPoints());
		}

		@Test
		void exampleSum() throws Exception {
			int sum = Arrays.stream(Utils.splitLines(EXAMPLE))
					.map(Card::fromString)
					.mapToInt(Day04.Card::getWorthPoints)
					.sum();
			assertEquals(13, sum);
		}

		@Test
		void inputFile() throws Exception {
			int sum = Files.lines(INPUT_FILE_PATH).map(Card::fromString).mapToInt(Day04.Card::getWorthPoints).sum();
			System.out.println("Sum of worth points: " + sum);
		}
	}

	@Nested
	class Part2 {

		@ParameterizedTest
		@CsvSource(textBlock = """
				Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53   IS EXPECTING [2,3,4,5]
				Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19   IS EXPECTING [3,4]
				Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1   IS EXPECTING [4,5]
				Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83   IS EXPECTING [5]
				Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36   IS EXPECTING []
				Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11   IS EXPECTING []
							""", delimiterString = "IS EXPECTING")
		void exampleExpecatedMatchingCards(final String cardStr,
				@ConvertWith(IntegerArrayConverter.class) final Integer[] expectedCards) throws Exception {
			assertEquals(List.of(expectedCards), Card.fromString(cardStr).getRewardedScracthcards());
		}

		@Test
		void example() throws Exception {
			Map<Integer, Card> cards = Arrays.stream(Utils.splitLines(EXAMPLE))
					.map(Card::fromString)
					.collect(Collectors.toMap(Card::id, Function.identity()));

			int count = countTotalScratchedCards(cards);
			assertEquals(30, count);
		}

		@Test
		void inputFile() throws Exception {
			Map<Integer, Card> cards = Files.lines(INPUT_FILE_PATH)
					.map(Card::fromString)
					.collect(Collectors.toMap(Card::id, Function.identity()));

			int count = countTotalScratchedCards(cards);
			System.out.println("Total scratchcards: " + count);
		}

		private static int countTotalScratchedCards(final Map<Integer, Card> cards) {
			TreeMap<Integer, AtomicInteger> cardsToScractch = cards.keySet()
					.stream()
					.collect(Collectors.toMap(Function.identity(), key -> new AtomicInteger(1), (a, b) -> a,
							TreeMap::new));

			for (int cardId = 0; cardId < cardsToScractch.size(); cardId++) {
				AtomicInteger countOfCardsToScratch = cardsToScractch.get(cardId);
				cards.getOrDefault(cardId, new Card(Integer.MIN_VALUE, Set.of(), Set.of()))
						.getRewardedScracthcards()
						.stream()
						.forEach(cardIdToAdd -> addCardToScractch(cardIdToAdd, cardsToScractch, countOfCardsToScratch));
			}

			return cardsToScractch.values().stream().mapToInt(AtomicInteger::get).sum();
		}

		private static void addCardToScractch(final int cardIdToAdd, final TreeMap<Integer, AtomicInteger> cardsToScractch,
				final AtomicInteger countOfCardsToScratch) {
			AtomicInteger alreadyPresentCount = cardsToScractch.get(cardIdToAdd);
			if (alreadyPresentCount == null) {
				cardsToScractch.put(cardIdToAdd, countOfCardsToScratch);
			} else {
				alreadyPresentCount.addAndGet(countOfCardsToScratch.get());
			}
		}
	}
}
