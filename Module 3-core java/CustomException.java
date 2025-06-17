import java.util.Scanner;

// Step 1: Define the custom exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class CustomException {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Step 2: Get user input
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();

            // Step 3: Check the condition and throw custom exception
            if (age >= 18) {
                System.out.println("You are eligible to vote.");
            } else {
                throw new InvalidAgeException("Age must be 18 or older to vote.");
            }

        } catch (InvalidAgeException e) {
            // Step 4: Catch the custom exception
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
