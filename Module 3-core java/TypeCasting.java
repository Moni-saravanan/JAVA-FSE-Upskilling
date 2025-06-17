import java.util.Scanner;
public class TypeCasting {
    public static void main(String[] args) {
        double num1 = 10.5;
        int num2 = (int) num1;
        System.out.println("Original double value: " + num1);
        System.out.println("Converted int value: " + num2);

        int num3 = 20;
        double num4 = (double) num3;
        System.out.println("Original int value: " + num3);
        System.out.println("Converted double value: " + num4);
    }
}
