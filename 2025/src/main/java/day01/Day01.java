package day01;

import java.util.Arrays;
import java.util.List;

import utils.Part;
import utils.Utils;

class Day01
{
  private static final int DIAL_START = 50;

  public static List<Rotation> parse(String input)
  {
    return Arrays.stream(Utils.splitLines(input))
      .map(Rotation::new)
      .toList();
  }

  public static int calc(List<Rotation> rotations, Part part)
  {
    int dial = DIAL_START;
    int count = 0;
    int dialBruteforce = DIAL_START;
    int countBrutefore = 0;

    for (Rotation rotation : rotations)
    {
      int dialBeforeRotation = dial;
      if (rotation.dir == Direction.LEFT)
      {
        dial -= rotation.count;
        while (dial < 0)
        {
          if (part == Part.PART_2)
          {
            count++;
          }
          dial += 100;
        }

      }
      else
      {
        dial += rotation.count;
        while (dial >= 100)
        {
          if (part == Part.PART_2)
          {
            count++;
          }
          dial -= 100;
        }
      }
      if (part == Part.PART_1 && dial == 0)
      {
        count++;
      }

      System.out.printf("%2d move %s results in dial %2d (count %d)%n", dialBeforeRotation,
        rotation, dial, count);

      // debug bruteforce p2
      if (part == Part.PART_2)
      {
        for (int i = 0; i < rotation.count; i++)
        {
          if (rotation.dir == Direction.LEFT)
          {
            dialBruteforce--;
          }
          else
          {
            dialBruteforce++;
          }
          if (dialBruteforce == -1)
          {
            dialBruteforce = 99;
          }
          else if (dialBruteforce == 100)
          {
            dialBruteforce = 0;
          }
          if (dialBruteforce == 0)
          {
            countBrutefore++;
          }
        }
        if (dialBruteforce != dial || countBrutefore != count)
        {
          System.out.printf("BRUTEFORE: %2d move %s results in dial %2d (count %d)%n",
            dialBeforeRotation, rotation, dialBruteforce, countBrutefore);
          // throw new RuntimeException("Failing on " + rotation);
        }
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

    @Override
    public final String toString()
    {
      return "%5s %3d".formatted(dir, count);
    }
  }

  private enum Direction
  {
    LEFT,
    RIGHT
  }
}
