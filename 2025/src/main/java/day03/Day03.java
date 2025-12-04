package day03;

import java.util.Arrays;
import java.util.List;

import utils.Utils;

class Day03
{

  public static Banks parse(String input)
  {
    return new Banks(Arrays.stream(Utils.splitLines(input))
      .map(Bank::new)
      .toList());
  }

  public record Banks(List<Bank> banks)
  {
    public int getTotalOutputJoltage()
    {
      return banks.stream()
        .mapToInt(Bank::getOutputJoltage)
        .peek(System.out::println)
        .sum();
    }
  }

  private record Bank(int[] batteries)
  {
    public Bank(String input)
    {
      this(input.chars()
        .map(c -> Utils.toInt((char) c))
        .toArray());
    }

    public int getOutputJoltage()
    {
      int biggestA = 0;
      int biggestB = 0;

      for (int i = 0; i < batteries.length - 1; i++)
      {
        if (batteries[i] > biggestA)
        {
          biggestA = batteries[i];
          biggestB = 0;
          for (int j = i + 1; j < batteries.length; j++)
          {
            if (batteries[j] > biggestB)
            {
              biggestB = batteries[j];
            }
          }
        }
      }
      return biggestA * 10 + biggestB;
    }
  }
}
