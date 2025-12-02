package day01;

import day01.Day01.Rotations;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utils.TestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test
{
  private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day01-input.txt");

  private static final String EXAMPLE = """
      L68
      L30
      R48
      L5
      R60
      L55
      L1
      L99
      R14
      L82
      """;

  static Rotations parsedExample;
  static Rotations parsedInput;

  @BeforeAll
  static void prepare() throws IOException
  {
    parsedExample = Day01.parse(EXAMPLE);
    parsedInput = Day01.parse(Files.readString(INPUT_FILE_PATH));
  }

  @Nested
  class Part1
  {
    @Test
    void example()
    {
      assertEquals(3, parsedExample.calcPart1());
    }

    @Test
    void inputFile()
    {
      System.out.println(parsedInput.calcPart1());
    }
  }

  @Nested
  class Part2
  {
    @Test
    void example()
    {
      assertEquals(6, parsedExample.calcPart2());
    }

    @Test
    void inputFile()
    {
      System.out.println(parsedInput.calcPart2());
    }
  }

}
