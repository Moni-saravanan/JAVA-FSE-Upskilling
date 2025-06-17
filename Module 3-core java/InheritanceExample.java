public class InheritanceExample {
    public static void main(String[] args) {
        Dog myDog = new Dog("Buddy");
        myDog.makeSound(); 
        

 
        Animal myAnimal = new Animal("Generic Animal");
        myAnimal.makeSound();
    }
}
 class Animal {
    String name;
    
    public Animal(String name) {
        this.name = name;
    }
    

    public void makeSound() {
        System.out.println(name + " makes a sound.");
    }

    
}
 class Dog extends Animal {
    
    public Dog(String name) {
        super(name);
    }

    
    public void makeSound() {
        System.out.println(name + " barks.");
    }
}
