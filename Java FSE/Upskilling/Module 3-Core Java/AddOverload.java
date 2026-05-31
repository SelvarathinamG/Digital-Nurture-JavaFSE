import java.util.Scanner;

public class AddOverload {
    static int add(int a, int b) { return a + b; }
    static double add(double a, double b) { return a + b; }
    static int add(int a, int b, int c) { return a + b + c; }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter two integers: ");
        int intOne = scanner.nextInt();
        int intTwo = scanner.nextInt();

        System.out.print("Enter two doubles: ");
        double doubleOne = scanner.nextDouble();
        double doubleTwo = scanner.nextDouble();

        System.out.print("Enter three integers: ");
        int intThreeOne = scanner.nextInt();
        int intThreeTwo = scanner.nextInt();
        int intThreeThree = scanner.nextInt();

        System.out.println("add(int,int): " + add(intOne, intTwo));
        System.out.println("add(double,double): " + add(doubleOne, doubleTwo));
        System.out.println("add(int,int,int): " + add(intThreeOne, intThreeTwo, intThreeThree));

        scanner.close();
    }
}

/*

Sample Input:
2 3
2.5 3.1
1 2 3

Sample Output:
add(int,int): 5
add(double,double): 5.6
add(int,int,int): 6
*/
