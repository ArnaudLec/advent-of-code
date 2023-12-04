package utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestUtils {

	public static Path getInputFile(final String name) {
		return Paths.get("./target/test-classes/inputs/").resolve(name);
	}
}
