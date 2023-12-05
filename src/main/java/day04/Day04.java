package day04;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.Utils;

class Day04 {

	record Card(int id, Set<Integer> winningNumbers, Set<Integer> playerNumbers) {

		public int getWorthPoints() {
			int matchingNumbers = getMatchingNumbers();
			if (matchingNumbers == 0) {
				return 0;
			}
			int worthPoints = 1;
			for (int i = 1; i < matchingNumbers; i++) {
				worthPoints *= 2;
			}
			return worthPoints;
		}

		private int getMatchingNumbers() {
			return (int) playerNumbers.stream().filter(winningNumbers::contains).count();
		}

		public Collection<Integer> getRewardedScracthcards() {
			return IntStream.range(id + 1, id + 1 + getMatchingNumbers()).boxed().toList();
		}

		public static Card fromString(final String cardStr) {
			String[] split = cardStr.split("[:\\|]");
			int id = Integer.parseInt(split[0].substring(5).trim());

			return new Card(id, parseNumbers(split[1]), parseNumbers(split[2]));
		}
	}

	private static Set<Integer> parseNumbers(final String split) {
		return Arrays.stream(split.split("\s"))
				.filter(Utils.not(String::isBlank))
				.map(String::trim)
				.map(Integer::valueOf)
				.collect(Collectors.toUnmodifiableSet());
	}
}
