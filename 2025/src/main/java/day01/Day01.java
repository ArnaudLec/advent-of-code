package day01;

import utils.Utils;

import java.util.Arrays;
import java.util.List;

class Day01
{
  private static final int DIAL_START = 50;

  public static Rotations parse(String input)
  {
    return new Rotations(Arrays.stream(Utils.splitLines(input))
      .map(Rotation::new)
      .toList());
  }

  public record Rotations(List<Rotation> rotations){

    public int calcPart1(){
      int dial = DIAL_START;
      int count = 0;

      for (Rotation rotation : rotations)
      {
        if (rotation.dir == Direction.LEFT)
        {
          dial -= rotation.count;
          while (dial < 0)
          {
            dial += 100;
          }
        }
        else
        {
          dial += rotation.count;
          while (dial >= 100)
          {
            dial -= 100;
          }
        }
        if (dial == 0)
        {
          count++;
        }
      }
      return count;
    }

    public int calcPart2(){
      int dial = DIAL_START;
      int count = 0;

      for (Rotation rotation : rotations)
      {
        for (int i = 0; i < rotation.count; i++)
        {
          dial += rotation.dir == Direction.LEFT ? -1 : 1;
          if (dial == -1)
          {
            dial = 99;
          }
          else if (dial == 100)
          {
            dial = 0;
          }
          if (dial == 0)
          {
            count++;
          }
        }
      }
      return count;
    }
  }

  private record Rotation(Direction dir, int count)
  {
    public Rotation(String line)
    {
      char dir = line.charAt(0);
      int count = Integer.parseInt(line.substring(1));
      this(dir == 'L' ? Direction.LEFT : Direction.RIGHT, count);
    }

    @Override
    public String toString()
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
