package utils;

import java.util.Arrays;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class IntegerArrayConverter implements ArgumentConverter {

	@Override
	public Integer[] convert(final Object source, final ParameterContext context) throws ArgumentConversionException {
		if (!(source instanceof String string)) {
			throw new IllegalArgumentException("Can't convert input which is not a String: " + source);
		}

		try {
			return Arrays.stream(string.split("[\\[\\],]"))
					.filter(Utils.not(String::isBlank))
					.map(String::trim)
					.map(Integer::valueOf)
					.toArray(Integer[]::new);
		} catch (RuntimeException e) {
			throw new ArgumentConversionException("Could not convert to int[]", e);
		}
	}

}
