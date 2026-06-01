import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ReflectionDemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
               
                Class<?> c = Class.forName("ReflectionDemo$TestClass");
                
                Object obj = c.getDeclaredConstructor().newInstance();
                
                Method[] methods = c.getDeclaredMethods();
                
                for (Method m : methods) {
                    System.out.println("Method Name: " + m.getName());
                    
                    m.invoke(obj, name);
                }
                
            } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                System.out.println(e);
            }
        }
    }
}

/*
Input:
Enter name: Selva

Output:
Method Name: show
Hello Selva
*/