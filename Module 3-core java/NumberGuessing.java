import java.util.Scanner;
public class NumberGuessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int randomNumber = (int) (Math.random() * 100) + 1; 
        int userGuess = 0;
        while(userGuess != randomNumber) {
            System.out.print("Guess a number between 1 and 100: ");
            userGuess = scanner.nextInt();
            if (userGuess < randomNumber) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > randomNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You've guessed the number: " + randomNumber);
            }
        }

    }
}
