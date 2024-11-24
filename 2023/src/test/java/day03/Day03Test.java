package day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import utils.TestUtils;

class Day03Test {

	private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day03-input.txt");

	private static final String EXAMPLE = """
			467..114..
			...*......
			..35..633.
			......#...
			617*......
			.....+.58.
			..592.....
			......755.
			...$.*....
			.664.598..
				""";

	@Nested
	class Part1 {

		@Test
		void example() throws Exception {
			assertEquals(4361, Day03.part1(EXAMPLE));
		}

		@ParameterizedTest
		@MethodSource("testSchemas")
		void debug(final String schema, final int expectedSumOfParts) throws Exception {
			assertEquals(expectedSumOfParts, Day03.part1(schema));
		}

		static Stream<Arguments> testSchemas() {
			return Stream.of(
					// 1
					Arguments.of("""
							........
							.24..4..
							......*.
							 """, 4),
					// 2
					Arguments.of("""
							........
							.24$-4..
							......*.
							 """, 28),
					// 3
					Arguments.of("""
							11....11
							..$..$..
							11....11
							 """, 44),
					// 4
					Arguments.of("""
							$......$
							.1....1.
							.1....1.
							$......$
							 """, 4),
					// 5
					Arguments.of("""
							$......$
							.11..11.
							.11..11.
							$......$
							 """, 44),
					// 6
					Arguments.of("""
							$11
							...
							11$
							...
							 """, 22),
					// 7
					Arguments.of("""
							$..
							.11
							.11
							$..
							..$
							11.
							11.
							..$
							 """, 44),
					// 8
					Arguments.of("11.$.", 0),
					// 9
					Arguments.of("12+.\n....", 12),
					// 10
					Arguments.of("-1", 1),
					// 11
					Arguments.of("""
							.......5......
							..7*..*.....4*
							...*13*......9
							.......15.....
							..............
							..............
							..............
							..............
							..............
							..............
							21............
							...*9.........
							 """, 62),
					// 12
					Arguments.of("""
							12.23
							..*..
							.....
							 """, 35),
					// 13
					Arguments.of("""
							1-......
							........
							......-1
							........
							.24.....
							......*4
							 """, 6),
					// 14
					Arguments.of("""
							97..
							...*
							100.
							""", 100),
					// 15
					Arguments.of("""
							$......$
							.11..11.
							.11.11..
							$......$
							 """, 33),
					// 16
					Arguments.of("""
							....................
							..-52..52-..52..52..
							..................-.
							""", 156),
					// 17
					Arguments.of("""
							12.......*..
							+.........34
							..$....-12..
							..78........
							..*....60...
							78.........9
							.5.....23..$
							8...90*12...
							............
							2.2......12.
							.*.........*
							1.1..503+.56
							""", 925));
		}

		@Test
		void inputFile() throws Exception {
			Integer sumOfParts = Day03.part1(Files.readString(INPUT_FILE_PATH));
			System.out.println("Sum of parts: " + sumOfParts);
		}

	}

	@Nested
	class Part2 {
		@Test
		void example() throws Exception {
			assertEquals(467_835, Day03.part2(EXAMPLE));
		}

		@Test
		void inputFile() throws Exception {
			Integer gearRatios = Day03.part2(Files.readString(INPUT_FILE_PATH));
			System.out.println("Gear ratios : " + gearRatios);
		}
	}

}
