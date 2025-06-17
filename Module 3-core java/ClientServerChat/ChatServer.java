import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        final int PORT = 12345;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for client...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

            // Thread for receiving messages from client
            new Thread(() -> {
                String clientMsg;
                try {
                    while ((clientMsg = input.readLine()) != null) {
                        System.out.println("Client: " + clientMsg);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            }).start();

            // Main thread sends messages
            String message;
            while ((message = serverInput.readLine()) != null) {
                output.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
