package day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import day02.Day02.Ranges;
import utils.Part;
import utils.TestUtils;

class Day02Test
{
  private static final Path INPUT_FILE_PATH = TestUtils.getInputFile("day02-input.txt");

  private static final String EXAMPLE =
    "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";

  @Nested
  class Part1
  {
    @Test
    void example()
    {
      assertEquals(1_227_775_554L, Day02.parse(EXAMPLE)
        .getSumInvalidOfIds(Part.PART_1));
    }

    @Test
    void inputFile() throws Exception
    {
      Ranges ranges = Day02.parse(Files.readString(INPUT_FILE_PATH));

      System.out.println(ranges.getSumInvalidOfIds(Part.PART_1));
    }
  }

  @Nested
  class Part2
  {
    @Test
    void example()
    {
      assertEquals(4_174_379_265L, Day02.parse(EXAMPLE)
        .getSumInvalidOfIds(Part.PART_2));
    }

    @Test
    void inputFile() throws Exception
    {
      Ranges ranges = Day02.parse(Files.readString(INPUT_FILE_PATH));

      System.out.println(ranges.getSumInvalidOfIds(Part.PART_2));
    }
  }

}
