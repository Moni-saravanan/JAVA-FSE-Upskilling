public class ClassAndObjectCreation {
   public static void main(String[] args) {
    Car myCar = new Car();
    myCar.displayInfo();
   } 
   public static class Car {
    String make= "Toyota";
    String model = "Camry";
    int year = 2020;
    public void displayInfo() {
        System.out.println("Car Make: " + make);
        System.out.println("Car Model: " + model);
        System.out.println("Car Year: " + year);
    }
   
    
   }
}
