import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 12345;
        try (Socket socket = new Socket(HOST, PORT)) {
            System.out.println("Connected to server.");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Thread to read messages from server
            new Thread(() -> {
                String serverMsg;
                try {
                    while ((serverMsg = input.readLine()) != null) {
                        System.out.println("Server: " + serverMsg);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            }).start();

            // Main thread sends messages
            String message;
            while ((message = userInput.readLine()) != null) {
                output.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
