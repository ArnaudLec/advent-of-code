package day02;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
	private final int id;

	private static final Pattern RED_PATTERN = Pattern.compile("(\\d+) red");
	private static final Pattern GREEN_PATTERN = Pattern.compile("(\\d+) green");
	private static final Pattern BLUE_PATTERN = Pattern.compile("(\\d+) blue");

	private final int maxRed;
	private final int maxGreen;
	private final int maxBlue;

	public Game(final String game) {
		final int indexOfSep = game.indexOf(':');
		id = Integer.parseInt(game.substring(5, indexOfSep));

		final String[] rest = game.substring(indexOfSep + 1).split(";");

		maxRed = Arrays.stream(rest).mapToInt(sub -> extract(RED_PATTERN, sub)).max().orElse(0);
		maxGreen = Arrays.stream(rest).mapToInt(sub -> extract(GREEN_PATTERN, sub)).max().orElse(0);
		maxBlue = Arrays.stream(rest).mapToInt(sub -> extract(BLUE_PATTERN, sub)).max().orElse(0);
	}

	public int getId() {
		return id;
	}

	private int extract(final Pattern pattern, final String sub) {
		Matcher matcher = pattern.matcher(sub);
		if (!matcher.find()) {
			return 0;
		}
		return Integer.parseInt(matcher.group(1));
	}

	public boolean isPossible(final int red, final int green, final int blue) {
		return red >= maxRed && green >= maxGreen && blue >= maxBlue;
	}

	public int powerCubes() {
		return maxRed * maxGreen * maxBlue;
	}

	@Override
	public String toString() {
		return "Game " + id + ": maxRed=" + maxRed + ", maxGreen=" + maxGreen + ", maxBlue=" + maxBlue;
	}
}
