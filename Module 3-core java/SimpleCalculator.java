import java.util.*;
public class SimpleCalculator {
public static void main(String[] args) {
   Scanner scanner = new Scanner(System.in);
   System.out.println("Enter first number:");
   int num1 = scanner.nextInt();
    System.out.println("Enter second number:");
    int num2 = scanner.nextInt();
    System.out.println("Choose an operation: +, -, *, /");
    String operation = scanner.next();
    int result = 0;
    if(operation.equals("+")) {
        result = num1 + num2;
    } else if(operation.equals("-")) {
        result = num1 - num2;
    } else if(operation.equals("*")) {
        result = num1 * num2;
    } else if(operation.equals("/")) {
        if(num2 != 0) {
            result = num1 / num2;
        } else {
            System.out.println("Error: Division by zero is not allowed.");
            return;
        }
    } else {
        System.out.println("Invalid operation.");
        return;
    }
    System.out.println("The result is: " + result);
  }
    
}