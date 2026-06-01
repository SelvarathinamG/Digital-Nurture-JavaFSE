import java.util.Scanner;

public class VirtualThreadDemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of virtual threads: ");
            int n = scanner.nextInt();

            long start = System.currentTimeMillis();

            for (int i = 1; i <= n; i++) {
                int num = i;

                Thread.startVirtualThread(() -> {
                    System.out.println("Virtual Thread " + num + " running");
                });
            }

            long end = System.currentTimeMillis();

            System.out.println("Time Taken: " + (end - start) + " ms");
        }
    }
}

/*
Input:
Enter number of virtual threads: 5

Output:
Virtual Thread 1 running
Virtual Thread 2 running
Virtual Thread 3 running
Virtual Thread 4 running
Virtual Thread 5 running
Time Taken: 5 ms
*/