package day02;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

import utils.Part;

class Day02
{
  private static final Pattern RANGE_SPLIT_PATTERN = Pattern.compile("-");
  private static final Pattern PART1_PATTERN = Pattern.compile("^(\\d+)\\1$");
  private static final Pattern PART2_PATTERN = Pattern.compile("^(\\d+)\\1+$");

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
    public long getSumInvalidOfIds(Part part)
    {
      return ranges.stream()
        .mapToLong(
          range -> range.sumInvalidIds(part == Part.PART_1 ? PART1_PATTERN : PART2_PATTERN))
        .sum();
    }
  }

  private record Range(String startStr, String endStr)
  {
    public long sumInvalidIds(Pattern pattern)
    {
      long start = Long.parseLong(startStr);
      long end = Long.parseLong(endStr);
      return LongStream.range(start, end + 1)
        .filter(l -> isInvalid(pattern, l))
        .sum();
    }

    private boolean isInvalid(Pattern pattern, long l)
    {
      String strLong = String.valueOf(l);
      return pattern.matcher(strLong)
        .matches();
    }
  }
}
