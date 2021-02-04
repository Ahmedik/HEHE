package src.com.company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler extends Thread{
    Socket socket;
    ArrayList<Book> books;

    public ClientHandler(Socket socket, ArrayList<Book> books) {
        this.socket = socket;
        this.books =  books;
    }

    @Override
    public void run() {

        try {
            Scanner in = new Scanner(System.in);

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            while (true){
                PackageData data = (PackageData) inputStream.readObject();
                if (data.getOperationType().equals("ADD")){
                    books.add(data.getBook());
                }else if (data.getOperationType().equals("LIST")){
                    data.setBooks(books);
                    System.out.println(books);
                    outputStream.writeObject(data);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
