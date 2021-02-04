package src.com.company;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args){


        ArrayList<Book> arrayList = new ArrayList<Book>();

        try {
            ServerSocket server = new ServerSocket(2077);
            System.out.println("Server is started");

            while (true){
                Socket socket = server.accept();
                System.out.println("Client is connected");

                ClientHandler handler = new ClientHandler(socket, arrayList);
                handler.start();
                System.out.println("New thread for new client is started");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
