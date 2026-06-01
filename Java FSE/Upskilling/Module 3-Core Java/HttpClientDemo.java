import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class HttpClientDemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                System.out.print("Enter API URL: ");
                String url = scanner.nextLine();
                
                HttpClient client = HttpClient.newHttpClient();
                
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();
                
                HttpResponse<String> response =
                        client.send(request, HttpResponse.BodyHandlers.ofString());
                
                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Response Body:");
                System.out.println(response.body());
                
            } catch (IOException | InterruptedException e) {
                System.out.println("Error: " + e);
            }
        }
    }
}

/*
Input:
Enter API URL: https://api.github.com/users/octocat

Output:
Status Code: 200
Response Body:
{
  "login":"octocat"
}
*/