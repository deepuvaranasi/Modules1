import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5050)) {

            var output = new PrintWriter(socket.getOutputStream());

            output.println("Hello from Deepus Client\r\n");
            output.flush();

            var inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                var line = inputFromServer.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                System.out.println(line);
            }
            inputFromServer.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
