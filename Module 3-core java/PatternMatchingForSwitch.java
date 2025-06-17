public class PatternMatchingForSwitch {

    public static void checkType(Object obj) {
        // Using enhanced switch with pattern matching
        String result = switch (obj) {
            case Integer i -> "It's an Integer with value: " + i;
            case String s -> "It's a String with value: " + s;
            case Double d -> "It's a Double with value: " + d;
            case null -> "The object is null";
            default -> "Unknown type: " + obj.getClass().getSimpleName();
        };

        System.out.println(result);
    }

    public static void main(String[] args) {
        checkType(42);
        checkType("Hello");
        checkType(3.14);
        checkType(true);  // Boolean type (not matched explicitly)
        checkType(null);
    }
}











