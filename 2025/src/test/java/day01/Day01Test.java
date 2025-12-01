package day01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import day01.Day01.Rotation;
import utils.Part;
import utils.TestUtils;

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

  static List<Rotation> parsedExample;
  static List<Rotation> parsedInput;

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
      assertEquals(3, Day01.calc(parsedExample));
    }

    @Test
    void inputFile()
    {
      System.out.println(Day01.calc(parsedInput));
    }
  }

  @Nested
  class Part2
  {

    @Test
    void example()
    {
      assertEquals(6, Day01.calc(parsedExample));
    }

    @Test
    void inputFile() throws Exception
    {
      System.out.println(Day01.calc(parsedInput));
    }
  }

}
