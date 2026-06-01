import java.util.Scanner;

public class PatternMatchingSwitch {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter an integer, decimal, boolean, or text: ");
			String input = scanner.nextLine();
			describeValue(parseValue(input));
		}
	}

	private static Object parseValue(String input) {
		if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
			return Boolean.parseBoolean(input);
		}
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException ignored) {
		}
		try {
			return Double.parseDouble(input);
		} catch (NumberFormatException ignored) {
		}
		return input;
	}

	private static void describeValue(Object value) {
		String message;
		if (value instanceof Integer number) {
			message = "Integer value: " + number;
		} else if (value instanceof String text) {
			message = "String value: " + text;
		} else if (value instanceof Double decimal) {
			message = "Double value: " + decimal;
		} else if (value instanceof Boolean flag) {
			message = "Boolean value: " + flag;
		} else {
			message = "Unknown type";
		}
		System.out.println(message);
	}
}

/*
Input:
Enter an integer, decimal, boolean, or text: 42

Output:
Integer value: 42
*/
