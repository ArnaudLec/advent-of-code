package day07;

import java.util.Arrays;
import java.util.Set;

import utils.Utils;

class Day07 {
	public static long calcCalibrationStatus(String input, Set<Operation> ops) {
		String[] lines = Utils.splitLines(input);

		long result = 0;
		for (String line : lines) {
			int indexOfResultEnd = line.indexOf(':');
			long expectedResult = Long.parseLong(line.substring(0, indexOfResultEnd));
			long[] numbers = Arrays.stream(line.substring(indexOfResultEnd + 2).split(" ")).mapToLong(Long::parseLong)
					.toArray();
			if (calc(ops, expectedResult, numbers, 0, 0)) {
				result += expectedResult;
			}
		}

		return result;
	}

	private static boolean calc(Set<Operation> operations, long expectedResult, long[] numbers, long previousCalcResult,
			int i) {
		if (i == numbers.length && expectedResult == previousCalcResult) {
			return true;
		}
		if (previousCalcResult > expectedResult || i == numbers.length) {
			return false;
		}

		for (Operation op : operations) {
			if (calc(operations, expectedResult, numbers, op.apply(previousCalcResult, numbers[i]), i + 1)) {
				return true;
			}
		}
		return false;
	}

	public enum Operation {
		ADD((a, b) -> a + b), MULTIPLY((a, b) -> a * b), CONCAT((a, b) -> Long.parseLong(a + "" + b));

		final OperationInterface operation;

		Operation(OperationInterface operation) {
			this.operation = operation;
		}

		long apply(long a, long b) {
			return operation.calc(a, b);
		}
	}

	@FunctionalInterface
	private interface OperationInterface {
		long calc(long a, long b);
	}
}
