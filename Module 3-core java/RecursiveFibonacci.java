import java.util.Scanner;
public class RecursiveFibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       int n;
        System.out.println("Enter the positive integer to find the Fibonacci number: ");
        n = scanner.nextInt();
        if (n < 0) {
            System.out.println("Fibonacci is not defined for negative numbers.");
        } else {
            int result = fibonacci(n);
            System.out.println("The Fibonacci number at position " + n + " is: " + result);
        }
    }
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
