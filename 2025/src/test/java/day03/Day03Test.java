package day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import day03.Day03.Banks;
import utils.TestUtils;

class Day03Test
{
  private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day03-input.txt");

  static final Banks EXAMPLE = Day03.parse("""
      987654321111111
      811111111111119
      234234234234278
      818181911112111
      """);

  static Banks inputFile;

  @BeforeAll
  static void beforeAll() throws IOException
  {
    inputFile = Day03.parse(Files.readString(INPUT_FILE_PATH));
  }

  @Nested
  class Part1
  {

    @Test
    void example()
    {
      assertEquals(357, EXAMPLE.getTotalOutputJoltage());
    }

    @Test
    void inputFile()
    {
      System.out.println(inputFile.getTotalOutputJoltage());
    }
  }

  @Nested
  class Part2
  {

    @Test
    void example() throws Exception
    {
      assertEquals(0, EXAMPLE);
    }

    @Test
    void inputFile() throws Exception
    {
      String file = Files.readString(INPUT_FILE_PATH);

      System.out.println();
    }
  }

}
