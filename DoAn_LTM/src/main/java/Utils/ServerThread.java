
package Utils;

import Utils.Ceasar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;

public class ServerThread{
    private static int port;

    public ServerThread( int port){        
        this.port = port;
    }

    public void start() {
        //int port = 7878;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}

   
        public static class ClientHandler implements Runnable {
        private Socket socket;
        
        

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

       @Override
       public void run() {
       try {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String key = in.readLine();
        String cipherText = in.readLine();
        String vanBan = Ceasar.decrypt(cipherText, Integer.parseInt(key) );


        Map<Character, Integer> hoaCount = new HashMap<>();
        Map<Character, Integer> thuongCount = new HashMap<>();

        for (char c : vanBan.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hoaCount.put(c, hoaCount.getOrDefault(c, 0) + 1);
            } else if (Character.isLowerCase(c)) {
                thuongCount.put(c, thuongCount.getOrDefault(c, 0) + 1);
            }
        }

        StringBuilder result = new StringBuilder();

        // Thêm phần chữ Hoa vào kết quả
        
        for (Map.Entry<Character, Integer> entry : hoaCount.entrySet()) {
            result.append(entry.getKey()).append(" ").append(entry.getValue()).append(" ");
        }
        result.append("\n");

        // Thêm phần chữ thường vào kết quả
        
        for (Map.Entry<Character, Integer> entry : thuongCount.entrySet()) {
            result.append(entry.getKey()).append(" ").append(entry.getValue()).append(" ");
        }
        result.append("\n");

        out.println(result.toString());

        socket.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    }
}


