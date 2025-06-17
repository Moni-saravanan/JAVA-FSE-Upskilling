import java.util.*;
import java.util.stream.Collectors;

// Define a record for Person with name and age
record Person(String name, int age) {}

public class Record {
    public static void main(String[] args) {
        // Create a list of Person records
        List<Person> people = List.of(
            new Person("Alice", 22),
            new Person("Bob", 17),
            new Person("Charlie", 30),
            new Person("Daisy", 15)
        );

        // Print all people
        System.out.println("All people:");
        people.forEach(System.out::println);

        // Filter people aged 18 or older using Streams
        List<Person> adults = people.stream()
                                    .filter(p -> p.age() >= 18)
                                    .collect(Collectors.toList());

        // Print filtered list
        System.out.println("\nAdults (age >= 18):");
        adults.forEach(System.out::println);
    }
}
