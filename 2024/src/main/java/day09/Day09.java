package day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.Part;
import utils.Utils;

class Day09 {

	private static final int FREE = -1;

	public static long getChecksumAfterCompacting(String input, Part part) {
		FileSystem fileSystem = convertInput(input);
		if (part == Part.PART_1) {
			fileSystem.compactPart1();
		} else {
			fileSystem.compactPart2();
		}
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

		public void compactPart1() {
			int i = 0;
			int j = fileSystem.length - 1;

			while (i < j) {
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

		public void compactPart2() {
			List<File> files = getFiles();

			for (File file : files.reversed()) {
				for (int i = 0, freeSpace = 0; i <= file.startIndex; i++) {
					if (fileSystem[i] == FREE) {
						freeSpace++;
					} else if (freeSpace > 0) {
						freeSpace = 0;
					}
					if (freeSpace > 0 && file.length <= freeSpace) {
						Arrays.fill(fileSystem, i - freeSpace + 1, i - freeSpace + 1 + file.length, file.id);
						Arrays.fill(fileSystem, file.startIndex, file.startIndex + file.length, FREE);
						break;
					}
				}
			}

		}

		private List<File> getFiles() {
			List<File> files = new ArrayList<>();
			int fileLength = 0;
			int fileId = FREE;
			int startIndex = 0;
			for (int i = 0; i < fileSystem.length; i++) {

				if (fileSystem[i] == FREE && fileId != FREE && fileLength > 0) {
					files.add(new File(fileId, startIndex, fileLength));
					fileLength = 0;
					fileId = FREE;
				} else if (fileId != FREE && i == fileSystem.length - 1) {
					if (fileLength > 0) {
						files.add(new File(fileId, startIndex, fileLength + 1));
					}
				} else if (fileSystem[i] != FREE) {
					if (fileId == FREE) {
						startIndex = i;
					} else if (fileId != fileSystem[i]) {
						files.add(new File(fileId, startIndex, fileLength));
						startIndex = i;
						fileLength = 0;
					}
					fileId = fileSystem[i];
					fileLength++;
				}
			}
			return files;
		}

		@Override
		public final String toString() {
			return Arrays.stream(fileSystem).mapToObj(fileId -> fileId == FREE ? "." : String.valueOf(fileId))
					.collect(Collectors.joining(""));
		}
	}

	private static record File(int id, int startIndex, int length) {

	}
}
