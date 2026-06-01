import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) {
        int port = 5000;

        try (Scanner scanner = new Scanner(System.in);
             ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port + ". Waiting for client...");

            try (Socket socket = serverSocket.accept();
                 BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true)) {

                System.out.println("Client connected. Type messages to chat.");

                Thread receiverThread = new Thread(() -> {
                    try {
                        String message;
                        while ((message = socketReader.readLine()) != null) {
                            System.out.println("Client: " + message);
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
                    System.out.print("Server: ");
                    String line = scanner.nextLine();
                    socketWriter.println(line);
                    if ("exit".equalsIgnoreCase(line.trim())) {
                        break;
                    }
                }

                receiverThread.join();
            }
        } catch (IOException | InterruptedException exception) {
            System.out.println("Server error: " + exception.getMessage());
        }
    }
}

/*
Input:
Server terminal text messages and client messages over socket

Output:
Server started on port 5000. Waiting for client...
Client connected. Type messages to chat.
Client: Hello server
*/