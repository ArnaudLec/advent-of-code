package day09;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.Utils;

class Day09 {

	private static final int FREE = -1;

	public static long getChecksumAfterCompacting(String input) {
		FileSystem fileSystem = convertInput(input);
		fileSystem.compact();
		return fileSystem.calculateChecksum();
	}

	private static FileSystem convertInput(String input) {
		String trimmedInput = input.trim();
		int fileSystemSize = IntStream.range(0, trimmedInput.length()).map(i -> Utils.toInt(trimmedInput.charAt(i)))
				.sum();
		int[] fileSystem = new int[fileSystemSize];

		int cursor = 0;
		for (int i = 0; i < trimmedInput.length(); i += 2) {
			int fileId = i / 2;
			int fileLength = Utils.toInt(trimmedInput.charAt(i));
			Arrays.fill(fileSystem, cursor, cursor += fileLength, fileId);

			if (i + 1 < trimmedInput.length()) {
				int freeSpaceLength = Utils.toInt(trimmedInput.charAt(i + 1));
				Arrays.fill(fileSystem, cursor, cursor += freeSpaceLength, FREE);
			}
		}

		return new FileSystem(fileSystem);
	}

	private record FileSystem(int[] fileSystem) {

		public long calculateChecksum() {
			long checksum = 0;
			for (int i = 0; i < fileSystem.length; i++) {
				if (fileSystem[i] != FREE) {
					checksum += i * fileSystem[i];
				}
			}
			return checksum;
		}

		public void compact() {
			int i = 0;
			int j = fileSystem.length - 1;

			while (i <= j) {
				if (fileSystem[i] == FREE) {
					while (fileSystem[j] == FREE) {
						j--;
					}
					if (i < j) {
						fileSystem[i] = fileSystem[j];
						fileSystem[j] = FREE;
						j--;
					}
				} else {
					i++;
				}
			}
		}

		@Override
		public final String toString() {
			return Arrays.stream(fileSystem).mapToObj(fileId -> fileId == FREE ? "." : String.valueOf(fileId))
					.collect(Collectors.joining(""));
		}
	}

}
