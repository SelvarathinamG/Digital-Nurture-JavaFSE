import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Scanner scanner = new Scanner(System.in);
             Socket socket = new Socket(host, port);
             BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to server. Type messages to chat.");

            Thread receiverThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = socketReader.readLine()) != null) {
                        System.out.println("Server: " + message);
                        if ("exit".equalsIgnoreCase(message.trim())) {
                            break;
                        }
                    }
                } catch (IOException exception) {
                    System.out.println("Connection closed.");
                }
            });
            receiverThread.start();

            while (true) {
                System.out.print("Client: ");
                String line = scanner.nextLine();
                socketWriter.println(line);
                if ("exit".equalsIgnoreCase(line.trim())) {
                    break;
                }
            }

            receiverThread.join();
        } catch (IOException | InterruptedException exception) {
            System.out.println("Client error: " + exception.getMessage());
        }
    }
}

/*
Input:
Client terminal text messages and server messages over socket

Output:
Connected to server. Type messages to chat.
Server: Hello client
*/