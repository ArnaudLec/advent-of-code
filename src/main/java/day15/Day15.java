package day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Day15 {

	private static final boolean DEBUG = false;

	public static long calcPart1(final String input) {
		return Arrays.stream(input.trim().split(",")).mapToInt(Day15::hashEntry).sum();
	}

	private static int hashEntry(final String input) {
		int currentValue = 0;
		for (int i = 0; i < input.length(); i++) {
			// determine ASCII code for current char
			int curChar = input.codePointAt(i);
			// increase current value by char code
			currentValue += curChar;
			// multiply by 17
			currentValue *= 17;
			// set currentValue to the remainder of dividing by 256
			currentValue %= 256;
		}
		return currentValue;
	}

	public static int calcPart2(final String input) {
		List<Box> boxes = IntStream.range(0, 256).mapToObj(i -> new Box(i, new ArrayList<>())).toList();

		String[] splittedInput = input.trim().split(",");
		for (String inputPart : splittedInput) {
			if (inputPart.endsWith("-")) {
				String label = inputPart.substring(0, inputPart.length() - 1);
				int boxIndex = hashEntry(label);
				boxes.get(boxIndex).sequence.removeIf(part -> label.equals(part.label));
			}
			int indexOfEqual = inputPart.indexOf('=');
			if (indexOfEqual != -1) {
				String label = inputPart.substring(0, indexOfEqual);
				int focalLength = Integer.parseInt(inputPart.substring(indexOfEqual + 1));
				int boxIndex = hashEntry(label);
				boolean added = false;
				List<SequencePart> sequence = boxes.get(boxIndex).sequence;
				for (int i = 0; i < sequence.size() && !added; i++) {
					SequencePart part = sequence.get(i);
					if (part.label.equals(label)) {
						sequence.remove(i);
						sequence.add(i, new SequencePart(label, focalLength));
						added = true;
					}
				}
				if (!added) {
					sequence.add(new SequencePart(label, focalLength));
				}
			}

			if (DEBUG) {
				System.out.println("After \"" + inputPart + "\":");
				boxes.stream().filter(box -> !box.sequence.isEmpty()).forEach(System.out::println);
				System.out.println();
			}
		}

		return boxes.parallelStream().mapToInt(Box::getFocusingPower).sum();
	}

	private record Box(int number, List<SequencePart> sequence) {

		public int getFocusingPower() {
			int focusingPower = 0;
			for (int i = 0; i < sequence.size(); i++) {
				SequencePart seqPart = sequence.get(i);
				focusingPower += (1 + number) * (i + 1) * seqPart.focalLength;
			}
			return focusingPower;
		}

		@Override
		public String toString() {
			return "Box " + number + ": "
					+ sequence.stream()
							.map(seq -> "[" + seq.label + " " + seq.focalLength + "]")
							.collect(Collectors.joining(" "));
		}
	}

	private record SequencePart(String label, int focalLength) {
	}
}
