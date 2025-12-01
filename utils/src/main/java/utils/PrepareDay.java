package utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.util.Arrays;

public class PrepareDay {

	private static int year;
	private static int day;
	private static String dayStr;

	private static Path yearProjectPath;

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

		if (args.length != 2) {
			usage("Expected 2 integer parameters");
		}

		int[] parsedArgs = Arrays.stream(args).mapToInt(PrepareDay::secureParseInt).toArray();

		year = parsedArgs[0];
		int currentYear = Year.now().getValue();
		if (year < 2015 || year > currentYear) {
			usage("Year [%s] must be between 2015 and %d".formatted(args[0], currentYear));
		}

		day = parsedArgs[1];
		if (day < 1 || day > 25) {
			usage("Day [%s] must be between 1 and 25".formatted(args[1]));
		}

		dayStr = "%02d".formatted(day);
		yearProjectPath = Paths.get("../%d".formatted(year));

		prepareMainClass();
		prepareTestClass();
		downloadInput();

		System.out.printf("Day %d of year %d is ready. Happy coding !", day, year);
	}

	private static void prepareMainClass() throws IOException {
		Path mainClassPath = yearProjectPath.resolve("src/main/java/day%s".formatted(dayStr));
		Files.createDirectories(mainClassPath);

		String mainClass = """
				package day${day};

				class Day${day} {

				}
				""".replace("${day}", dayStr);

		stringToFile(mainClass, mainClassPath.resolve("Day%s.java".formatted(dayStr)));
	}

	private static void prepareTestClass() throws IOException {
		Path testClassPath = yearProjectPath.resolve("src/test/java/day%s".formatted(dayStr));
		Files.createDirectories(testClassPath);

		Path testClassTemplatePath = Paths.get("src/main/resources/TestClass.template");
		String testClassTemplate = Files.readString(testClassTemplatePath).replace("${day}", dayStr);

		stringToFile(testClassTemplate, testClassPath.resolve("Day%sTest.java".formatted(dayStr)));
	}

	private static void downloadInput() throws IOException, URISyntaxException, InterruptedException {
		String sessionCookieValue = Files.readString(Paths.get("src/main/resources/session-cookie")).trim();
		HttpCookie sessionCookie = new HttpCookie("session", sessionCookieValue);
		sessionCookie.setPath("/");
		sessionCookie.setVersion(0);

		CookieManager cookieHandler = new CookieManager();
		cookieHandler.getCookieStore().add(new URI("https://adventofcode.com"), sessionCookie);

		try (HttpClient client = HttpClient.newBuilder().cookieHandler(cookieHandler).proxy(ProxySelector.getDefault())
            .build()) {

      HttpRequest request = HttpRequest.newBuilder().GET()
              .uri(new URI("https://adventofcode.com/%d/day/%d/input".formatted(year, day))).build();

			HttpResponse<String>   response = client.send(request, BodyHandlers.ofString());
			Path inputsFolderPath = yearProjectPath.resolve("src/test/resources/inputs/");
			Files.createDirectories(inputsFolderPath);

			stringToFile(response.body(), inputsFolderPath.resolve("day%s-input.txt".formatted(dayStr)));
    }
	}

	private static void stringToFile(String fileContent, Path inputFilePath) throws IOException {
		Files.copy(new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8)), inputFilePath);
	}

	private static void usage(String errorMessage) {
		System.err.println(errorMessage);
		System.err.println("Usage: java utils.PrepareDay {year} {day}");
		System.exit(127);
	}

	private static int secureParseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}
