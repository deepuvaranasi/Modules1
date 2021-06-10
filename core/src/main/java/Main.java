import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

   // public static List<String> billBoard = (new ArrayList<>());

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket socket = new ServerSocket(5050)) {
            while (true) {
                Socket client = socket.accept();
                System.out.println("Connection from:" + client.getInetAddress());
                executorService.submit(() -> handleConnection(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnection(Socket client) {
        try {
            System.out.println(client.getInetAddress());
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(500);


//            List<String> tempList = new ArrayList<>();
//            while (true) {
//                var line = inputFromClient.readLine();
//                if (line == null || line.isEmpty()) {
//                    break;
//                }
//                tempList.add(line);
//
//            }
//            synchronized (billBoard) {
//                billBoard.addAll(tempList);
//            }

            var inputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            readRequest(inputFromClient);


            var outputToClient = new PrintWriter(client.getOutputStream());
            sendResponse(outputToClient);

//            synchronized (billBoard) {
//                for (String line : billBoard) {
//                    outputToClient.print(line + "\r\n");
//                }
//            }
            outputToClient.print("\r\n");
            outputToClient.flush();
            inputFromClient.close();
            outputToClient.close();
            client.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sendResponse(PrintWriter outputToClient) {

        List<Person> persons = new ArrayList<Person>();

           persons.add(new Person("Martin",43,true));
           persons.add(new Person("Bita",40,true));
           persons.add(new Person("Anna",30,true));


        Gson gson = new Gson();
        String json = gson.toJson(persons);
        System.out.println(json);

        outputToClient.println("HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n");
        outputToClient.flush();
    }

    private static void readRequest(BufferedReader inputFromClient) throws IOException {
        while (true) {
            var line = inputFromClient.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            System.out.println(line);

        }

    }
}
