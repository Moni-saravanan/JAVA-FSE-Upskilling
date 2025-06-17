import java.util.Scanner;
public class TryCatchExample {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            int number1;
            System.out.print("Enter a number: ");
            number1 = scanner.nextInt();
            int number2;
            System.out.print("Enter another number: ");
            number2 = scanner.nextInt();
            int result = number1 / number2;
            
            
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            
            System.out.println("Execution completed.");
        }
    }
    
}
