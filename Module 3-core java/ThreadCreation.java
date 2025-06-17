 class MessagePrinter implements Runnable {
    private String message;

    public MessagePrinter(String message) {
        this.message = message;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(message + " - " + i);
            try {
                Thread.sleep(500); // Pause for 0.5 seconds
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }
}

public class ThreadCreation {
    public static void main(String[] args) {
        // Create two Runnable objects
        MessagePrinter printer1 = new MessagePrinter("Hello from Thread 1");
        MessagePrinter printer2 = new MessagePrinter("Hello from Thread 2");

        // Create two Thread objects
        Thread thread1 = new Thread(printer1);
        Thread thread2 = new Thread(printer2);

        // Start both threads
        thread1.start();
        thread2.start();
    }
}
 
    

