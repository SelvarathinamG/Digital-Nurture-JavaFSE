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
			return Boolean.valueOf(input);
		}
		try {
			return Integer.valueOf(input);
		} catch (NumberFormatException ignored) {
		}
		try {
			return Double.valueOf(input);
		} catch (NumberFormatException ignored) {
		}
		return input;
	}

	private static void describeValue(Object value) {
		String message;
            switch (value) {
                case Integer number -> message = "Integer value: " + number;
                case String text -> message = "String value: " + text;
                case Double decimal -> message = "Double value: " + decimal;
                case Boolean flag -> message = "Boolean value: " + flag;
                default -> message = "Unknown type";
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
