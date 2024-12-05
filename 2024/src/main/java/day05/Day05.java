package day05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import utils.Part;
import utils.Utils;

class Day05 {

	public static int sumSortedMiddlePageNumber(String input, Part part) {
		String[] inputParts = input.split("\n\n");

		Map<Integer, Set<Integer>> pageOrderingRules = parsePageOrderingRules(inputParts[0]);
		Comparator<Integer> pageOrderComparator = orderingRulesComparator(pageOrderingRules);

		int sum = 0;
		for (String str : Utils.splitLines(inputParts[1])) {
			List<Integer> pagesOrder = Arrays.stream(str.split(",")).map(Integer::valueOf).toList();
			List<Integer> pagesOrderWithRule = pagesOrder.stream().sorted(pageOrderComparator).toList();

			boolean isRightOrder = pagesOrder.equals(pagesOrderWithRule);
			switch (part) {
			case PART_1:
				if (isRightOrder) {
					sum += pagesOrder.get(pagesOrder.size() / 2);
				}
				break;
			case PART_2:
				if (!isRightOrder) {
					sum += pagesOrderWithRule.get(pagesOrder.size() / 2);
				}
				break;
			}
		}

		return sum;
	}

	private static Comparator<Integer> orderingRulesComparator(Map<Integer, Set<Integer>> orderingRules) {
		return (o1, o2) -> {
			Set<Integer> set = orderingRules.get(o1);
			if (set == null) {
				return 0;
			}
			if (set.contains(o2)) {
				return -1;
			}
			return 1;
		};
	}

	private static Map<Integer, Set<Integer>> parsePageOrderingRules(String string) {
		Map<Integer, Set<Integer>> orderingRules = new TreeMap<>();
		for (String line : Utils.splitLines(string)) {
			int pageIndex = line.indexOf('|');
			int first = Integer.parseInt(line.substring(0, pageIndex));
			int second = Integer.parseInt(line.substring(pageIndex + 1));
			orderingRules.computeIfAbsent(first, HashSet::new).add(second);
		}
		return orderingRules;
	}

}
