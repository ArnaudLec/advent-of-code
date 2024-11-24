package day07;

import static utils.Utils.arrayContains;
import static utils.Utils.splitLines;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day07 {

	public enum Day07Part {
		PART_1("AKQJT98765432"), PART_2("AKQT98765432J");

		public final String strengthOrder;

		Day07Part(final String strengthOrder) {
			this.strengthOrder = strengthOrder;
		}
	}

	public static class Hand {
		public final char[] hand;
		public final int bid;
		public final HandType handType;

		public Hand(final String line, final Day07Part part) {
			String[] parts = line.split("\\s+");
			hand = parts[0].toCharArray();
			bid = Integer.parseInt(parts[1]);
			handType = HandType.fromHand(hand, part);
		}

		public HandType getHandType() {
			return handType;
		}

		@Override
		public String toString() {
			return String.format("%15s %s %4s", handType, new String(hand), bid);
		}
	}

	public enum HandType {
		FIVE_OF_A_KIND {
			@Override
			public boolean isMatching(final int[] countOfCards, final int countOfJokers) {
				return arrayContains(countOfCards, 5 - countOfJokers) != -1;
			}
		},
		FOUR_OF_A_KIND {
			@Override
			public boolean isMatching(final int[] countOfCards, final int countOfJokers) {
				// part2 -> ignore pair of jokers
				int endIndex = countOfJokers > 0 ? countOfCards.length - 1 : countOfCards.length;
				return arrayContains(countOfCards, 4 - countOfJokers, 0, endIndex) != -1;
			}
		},
		FULL_HOUSE {
			@Override
			public boolean isMatching(final int[] countOfCards, final int countOfJokers) {
				// part2 -> ignore jokers
				int endIndex = countOfJokers > 0 ? countOfCards.length - 1 : countOfCards.length;
				int firstContain = arrayContains(countOfCards, 3 - countOfJokers, 0, endIndex);
				int secondContain = arrayContains(countOfCards, 2, 0, endIndex);
				if (secondContain == firstContain) {
					secondContain = arrayContains(countOfCards, 2, firstContain + 1);
				}
				return firstContain != -1 && secondContain != -1 && secondContain != firstContain;
			}
		},
		THREE_OF_A_KIND {
			@Override
			public boolean isMatching(final int[] countOfCards, final int countOfJokers) {
				return arrayContains(countOfCards, 3 - countOfJokers) != -1;
			}
		},
		TWO_PAIR {
			@Override
			public boolean isMatching(final int[] countOfCards, final int countOfJokers) {
				int firstContain = arrayContains(countOfCards, 2 - countOfJokers);
				int secondContain = arrayContains(countOfCards, 2, firstContain + 1);
				return secondContain != -1;
			}
		},
		ONE_PAIR {
			@Override
			public boolean isMatching(final int[] countOfCards, final int countOfJokers) {
				return arrayContains(countOfCards, 2 - countOfJokers) != -1;
			}
		},
		HIGH_CARD {
			@Override
			public boolean isMatching(final int[] countOfCards, final int countOfJokers) {
				if (countOfJokers > 0) {
					throw new IllegalStateException(this + " is not possible with jokers in hand");
				}
				return true;
			}
		};

		abstract boolean isMatching(int[] countOfCards, int countOfJokers);

		public static HandType fromHand(final char[] hand, final Day07Part part) {
			int[] matches = getCountOfCards(hand, part);
			int countOfJokers = 0;
			if (part == Day07Part.PART_2) {
				countOfJokers = matches[matches.length - 1];
			}
			for (HandType handType : values()) {
				if (handType.isMatching(matches, countOfJokers)) {
					return handType;
				}
			}
			throw new IllegalStateException("Could not find HandType for " + Arrays.toString(hand));
		}
	}

	private static int[] getCountOfCards(final char[] cards, final Day07Part part) {
		int[] matches = new int[part.strengthOrder.length()];
		for (char card : cards) {
			int cardIndex = part.strengthOrder.indexOf(card);
			if (cardIndex != -1) {
				matches[cardIndex] = matches[cardIndex] + 1;
			}
		}
		return matches;
	}

	public static List<Hand> parseHands(final String input, final Day07Part part) {
		return Arrays.stream(splitLines(input)).map(line -> new Hand(line, part)).collect(Collectors.toList());
	}

	public static void sortHandsByStrength(final List<Hand> hands, final Day07Part part) {
		hands.sort(Comparator.comparing(Hand::getHandType)
				.thenComparing((a, b) -> highestCardComparator(a, b, part))
				.reversed());
	}

	private static int highestCardComparator(final Hand a, final Hand b, final Day07Part part) {
		char[] handA = a.hand;
		char[] handB = b.hand;

		int i = 0;
		while (i < handA.length && i < handB.length && handA[i] == handB[i]) {
			i++;
		}

		return part.strengthOrder.indexOf(handA[i]) - part.strengthOrder.indexOf(handB[i]);
	}
}
