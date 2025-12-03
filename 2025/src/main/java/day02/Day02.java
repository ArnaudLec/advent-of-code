package day02;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

class Day02
{
  private static final Pattern RANGE_SPLIT_PATTERN = Pattern.compile("-");

  public static Ranges parse(String input)
  {
    return new Ranges(Arrays.stream(input.trim()
      .split(","))
      .map(range -> {
        String[] splittedRange = RANGE_SPLIT_PATTERN.split(range);
        return new Range(splittedRange[0], splittedRange[1]);
      })
      .toList());
  }

  public record Ranges(List<Range> ranges)
  {
    public long getSumInvalidOfIds()
    {
      return ranges.stream()
        .mapToLong(Range::sumInvalidIds)
        .sum();
    }
  }

  private record Range(String startStr, String endStr)
  {
    public long sumInvalidIds()
    {
      int startLen = startStr.length();
      if (startLen % 2 == 1 && endStr.length() == startLen)
      {
        // numbers cannot be symmetric if odd length
        return 0;
      }

      long start = Long.parseLong(startStr);
      long end = Long.parseLong(endStr);

      long invalidIdSum = 0;

      String nextStr =
        startLen % 2 == 0 ? startStr.substring(0, startLen / 2) : ("1" + "0".repeat(startLen / 2));

      int startOfNumber = Integer.parseInt(nextStr);
      long numberToTest;
      do
      {
        numberToTest =
          Long.parseLong(String.valueOf(startOfNumber) + String.valueOf(startOfNumber));
        if (start <= numberToTest && numberToTest <= end)
        {
          invalidIdSum += numberToTest;
        }
        startOfNumber++;
      }
      while (numberToTest <= end);

      return invalidIdSum;
    }
  }
}
