package info.jawne.kalendarz.parsers;

import static java.lang.Integer.valueOf;

import java.time.Duration;

public class DurationParser {
	public static Duration parse(String input) {
		int colonIndex = input.indexOf(':');
		String mm = input.substring(0, colonIndex);
		String ss = input.substring(colonIndex + 1);
		return Duration.ofMinutes(valueOf(mm)).plusSeconds(valueOf(ss));
	}
}