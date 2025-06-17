import java.util.Scanner;
public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to check if it is a palindrome: ");
        String input = scanner.nextLine();
        
       String reveString = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reveString += input.charAt(i);
        }
        
        if(input.equalsIgnoreCase(reveString)) {
            System.out.println("The string \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("The string \"" + input + "\" is not a palindrome.");
        }
    }
}
