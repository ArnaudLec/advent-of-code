package day07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import day07.Day07.Day07Part;
import day07.Day07.Hand;
import utils.TestUtils;

public class Day07Test {
	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day07-input.txt");

	private static final String EXAMPLE = """
			32T3K 765
			T55J5 684
			KK677 28
			KTJJT 220
			QQQJA 483
						""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			List<Hand> hands = Day07.parseHands(EXAMPLE, Day07Part.PART_1);
			Day07.sortHandsByStrength(hands, Day07Part.PART_1);
			assertEquals(6440, calcTotalWinnings(hands));
		}

		@Test
		void inputFile() throws Exception {
			List<Hand> hands = Day07.parseHands(Files.readString(INPUT_FILE_PATH), Day07Part.PART_1);
			Day07.sortHandsByStrength(hands, Day07Part.PART_1);
			System.out.println(calcTotalWinnings(hands));
		}
	}

	@Nested
	class Part2 {

		@Test
		void example() throws Exception {
			List<Hand> hands = Day07.parseHands(EXAMPLE, Day07Part.PART_2);
			Day07.sortHandsByStrength(hands, Day07Part.PART_2);
			assertEquals(5905, calcTotalWinnings(hands));
		}

		@Test
		void inputFile() throws Exception {
			List<Hand> hands = Day07.parseHands(Files.readString(INPUT_FILE_PATH), Day07Part.PART_2);
			Day07.sortHandsByStrength(hands, Day07Part.PART_2);
			System.out.println(calcTotalWinnings(hands));
		}

	}

	private long calcTotalWinnings(final List<Hand> hands) {
		AtomicLong pos = new AtomicLong(1);
		return hands.stream().mapToLong(hand -> hand.bid * pos.getAndIncrement()).sum();
	}
}
