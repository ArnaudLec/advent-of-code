package day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import utils.Utils;

class Day05 {

	private static final Pattern HEADER_PATTERN = Pattern.compile("(?<from>[a-z]+)-to-(?<to>[a-z]+) map:");

	public enum Type {
		SEED(Result::getSeed, null),
		SOIL(Result::getSoil, Result::setSoil),
		FERTILIZER(Result::getFertilizer, Result::setFertilizer),
		WATER(Result::getWater, Result::setWater),
		LIGHT(Result::getLight, Result::setLight),
		TEMPERATURE(Result::getTemperature, Result::setTemperature),
		HUMIDITY(Result::getHumidity, Result::setHumidity),
		LOCATION(Result::getLocation, Result::setLocation);

		public final Function<Result, Long> getter;
		public final BiConsumer<Result, Long> setter;

		Type(final Function<Result, Long> getter, final BiConsumer<Result, Long> setter) {
			this.getter = getter;
			this.setter = setter;
		}

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	private record AlmanacRange(long destinationRangeStart, long sourceRangeStart, long rangeLength) {

		public OptionalLong forSourceValue(final long sourceValue) {
			if ((sourceValue < sourceRangeStart) || (sourceValue > sourceRangeStart + rangeLength - 1)) {
				return OptionalLong.empty();
			}
			long shift = sourceValue - sourceRangeStart;
			return OptionalLong.of(destinationRangeStart + shift);
		}
	}

	private record AlmanacMap(Type from, Type to, List<AlmanacRange> ranges) {

		public long forSourceValue(final long sourceValue) {
			return ranges.parallelStream()
					.map(range -> range.forSourceValue(sourceValue))
					.filter(OptionalLong::isPresent)
					.findFirst()
					.orElse(OptionalLong.of(sourceValue))
					.getAsLong();
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder().append(from).append("-to-").append(to).append(" map:");
			ranges.forEach(range -> sb.append(System.lineSeparator()).append(range));
			return sb.toString();
		}

	}

	public static class Result {
		private final long seed;
		private long soil;
		private long fertilizer;
		private long water;
		private long light;
		private long temperature;
		private long humidity;
		private long location;

		public Result(final long seed) {
			this.seed = seed;
		}

		public long getSoil() {
			return soil;
		}

		public void setSoil(final long soil) {
			this.soil = soil;
		}

		public long getFertilizer() {
			return fertilizer;
		}

		public void setFertilizer(final long fertilizer) {
			this.fertilizer = fertilizer;
		}

		public long getWater() {
			return water;
		}

		public void setWater(final long water) {
			this.water = water;
		}

		public long getLight() {
			return light;
		}

		public void setLight(final long light) {
			this.light = light;
		}

		public long getTemperature() {
			return temperature;
		}

		public void setTemperature(final long temperature) {
			this.temperature = temperature;
		}

		public long getHumidity() {
			return humidity;
		}

		public void setHumidity(final long humidity) {
			this.humidity = humidity;
		}

		public long getLocation() {
			return location;
		}

		public void setLocation(final long location) {
			this.location = location;
		}

		public long getSeed() {
			return seed;
		}

		@Override
		public String toString() {
			return "Result [seed=" + seed + ", soil=" + soil + ", fertilizer=" + fertilizer + ", water=" + water
					+ ", light=" + light + ", temperature=" + temperature + ", humidity=" + humidity + ", location="
					+ location + "]";
		}
	}

	public record Almanac(long[] seeds, List<AlmanacMap> maps) {

		public Stream<Result> streamResults() {
			return Arrays.stream(seeds)
					.parallel()
					.mapToObj(Result::new)
					.map(result -> findAndPopulateResult(Type.SOIL, result))
					.map(result -> findAndPopulateResult(Type.FERTILIZER, result))
					.map(result -> findAndPopulateResult(Type.WATER, result))
					.map(result -> findAndPopulateResult(Type.LIGHT, result))
					.map(result -> findAndPopulateResult(Type.TEMPERATURE, result))
					.map(result -> findAndPopulateResult(Type.HUMIDITY, result))
					.map(result -> findAndPopulateResult(Type.LOCATION, result));
		}

		private Result findAndPopulateResult(final Type toType, final Result result) {

			Type fromType = Type.values()[toType.ordinal() - 1];
			long sourceValue = fromType.getter.apply(result);

			AlmanacMap map = maps.parallelStream().filter(m -> m.from == fromType).findFirst().orElseThrow();

			long toValue = map.forSourceValue(sourceValue);

			toType.setter.accept(result, toValue);

			return result;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("seeds: ");
			for (long seed : seeds) {
				sb.append(seed).append(' ');
			}
			maps.forEach(map -> sb.append(System.lineSeparator()).append(map));
			return sb.toString();
		}

	}

	public static Almanac parse(final String input) {
		String[] lines = Utils.splitLines(input);
		long[] seeds = Arrays.stream(lines[0].substring(6).split("\s+"))
				.filter(Utils.not(String::isBlank))
				.mapToLong(Long::parseLong)
				.toArray();

		List<AlmanacMap> maps = new ArrayList<>();

		Type currentFrom = null;
		Type currentTo = null;
		List<AlmanacRange> ranges = new ArrayList<>();

		for (int i = 2; i < lines.length; i++) {
			String line = lines[i];

			if (line.isBlank()) {
				// adding previous complete Map
				maps.add(new AlmanacMap(currentFrom, currentTo, ranges));
				continue;
			}

			Matcher headerMatcher = HEADER_PATTERN.matcher(line);
			if (headerMatcher.find()) {

				currentFrom = Type.valueOf(headerMatcher.group("from").toUpperCase());
				currentTo = Type.valueOf(headerMatcher.group("to").toUpperCase());
				ranges = new ArrayList<>();
			} else {
				long[] parts = Arrays.stream(line.split("\s+")).mapToLong(Long::parseLong).toArray();
				ranges.add(new AlmanacRange(parts[0], parts[1], parts[2]));
			}
		}
		maps.add(new AlmanacMap(currentFrom, currentTo, ranges));

		return new Almanac(seeds, maps);
	}
}
