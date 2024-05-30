package org.example.javachat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    private Client()
    {
    }

    public static void startClient()
    {
        try (Socket socket = new Socket("localhost" , 8888) ;
             OutputStream outputStream = socket.getOutputStream() ;
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream) ;)
        {
            Scanner scanner = new Scanner(System.in);
            while (true)
            {
                String message = scanner.nextLine();
                if (message.equals("exit"))
                {
                    System.exit(0);
                    break;
                }
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
