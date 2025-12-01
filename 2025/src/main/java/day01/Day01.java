package day01;

import java.util.Arrays;
import java.util.List;

import utils.Part;
import utils.Utils;

class Day01
{
  public static List<Rotation> parse(String input)
  {
    return Arrays.stream(Utils.splitLines(input))
      .map(Rotation::new)
      .toList();
  }

  public static int calc(List<Rotation> rotations)
  {
    int dialStart = 50;
    int count = 0;
    for (Rotation rotation : rotations)
    {
      if (rotation.dir == Direction.LEFT)
      {
        dialStart -= rotation.count;
        while (dialStart <= -100)
        {
          dialStart += 100;
        }
      }
      else
      {
        dialStart += rotation.count;
        while (dialStart >= 100)
        {
          dialStart -= 100;
        }
      }
      if (dialStart == 0)
      {
        count++;
      }
    }
    return count;
  }

  public record Rotation(Direction dir, int count)
  {
    public Rotation(String line)
    {
      char dir = line.charAt(0);
      int count = Integer.parseInt(line.substring(1));
      this(dir == 'L' ? Direction.LEFT : Direction.RIGHT, count);
    }
  }

  private enum Direction
  {
    LEFT,
    RIGHT
  }
}
