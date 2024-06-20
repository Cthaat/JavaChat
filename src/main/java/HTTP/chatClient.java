package HTTP;

import java.io.*;
import java.net.Socket;

public class chatClient extends Thread
{
    public static DataOutputStream dataOutputStream;
    public static final Socket socket;
    private String username;

    public chatClient(String username)
    {
        this.username = username;
    }

    static
    {
        try
        {
            socket = new Socket("localhost", 10086);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void run()
    {
        try (OutputStream outputStream = socket.getOutputStream() ;)
        {
            dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(username);
            while (true)
            {

            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
