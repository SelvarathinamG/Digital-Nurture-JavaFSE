import java.util.Scanner;

public class ThreadCreation {
	private static class MessagePrinter extends Thread {
		private final String message;
		private final int count;

		MessagePrinter(String name, String message, int count) {
			super(name);
			this.message = message;
			this.count = count;
		}

		@Override
		public void run() {
			for (int i = 1; i <= count; i++) {
				System.out.println(getName() + ": " + message + " (" + i + ")");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter message for thread 1: ");
			String firstMessage = scanner.nextLine();
			System.out.print("Enter message for thread 2: ");
			String secondMessage = scanner.nextLine();
			System.out.print("Enter repeat count: ");
			int count = scanner.nextInt();

			Thread t1 = new MessagePrinter("Thread-1", firstMessage, count);
			Thread t2 = new MessagePrinter("Thread-2", secondMessage, count);
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		}
	}
}

/*
Input:
Enter message for thread 1: Hello from A
Enter message for thread 2: Hello from B
Enter repeat count: 3

Output:
Thread-1: Hello from A (1)
Thread-1: Hello from A (2)
Thread-1: Hello from A (3)
Thread-2: Hello from B (1)
Thread-2: Hello from B (2)
Thread-2: Hello from B (3)
*/
