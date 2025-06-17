import java.util.*;

public class LambdaExpression {
    public static void main(String[] args) {
        // Create a List of strings
        List<String> names = new ArrayList<>();
        names.add("Zara");
        names.add("Alex");
        names.add("John");
        names.add("Emma");

        // Sort the list using a lambda expression
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));

        // Display the sorted list
        System.out.println("Sorted list of names:");
        for (String name : names) {
            System.out.println(name);
        }
    }
}
