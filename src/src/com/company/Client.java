package src.com.company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        int id = 0;
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("127.0.0.1",2077);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            while (true){
                System.out.println("\n");
                System.out.println("PRESS 1 TO LIST BOOKS");
                System.out.println("PRESS 2 TO ADD BOOKS");
                System.out.println("PRESS 0 TO DISCONNECT FROM SERVER");
                int vibor = in.nextInt();
                if(vibor==2){
                    PackageData packageData = new PackageData();
                    id++;
                    System.out.print("Insert name - ");
                    String name = in.next();
                    System.out.print("Insert author - ");
                    String author = in.next();
                    packageData.setBook(new Book(id,name,author));
                    packageData.setOperationType("ADD");
                    outputStream.writeObject(packageData);
                }else if(vibor==1){
                    PackageData packageData1 = new PackageData();
                    packageData1.setOperationType("LIST");
                    outputStream.writeObject(packageData1);
                    PackageData datada = (PackageData) inputStream.readObject();
                    System.out.println(datada.getBooks());
                }else{
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
