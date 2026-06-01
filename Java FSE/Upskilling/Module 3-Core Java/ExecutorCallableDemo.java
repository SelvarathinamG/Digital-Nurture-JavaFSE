import java.util.*;
import java.util.concurrent.*;

public class ExecutorCallableDemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ExecutorService service = Executors.newFixedThreadPool(3);

            System.out.print("Enter number of tasks: ");
            int n = scanner.nextInt();
            List<Future<Integer>> list = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                int num = i;

                Callable<Integer> task = () -> num * num;

                Future<Integer> future = service.submit(task);

                list.add(future);
            }

            try {
                for (Future<Integer> f : list) {
                    System.out.println("Result: " + f.get());
                }
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
            }

            service.shutdown();
        }
    }
}

/*
Input:
Enter number of tasks: 3

Output:
Result: 1
Result: 4
Result: 9
*/
