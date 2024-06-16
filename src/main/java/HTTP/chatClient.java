package HTTP;

import java.io.*;
import java.net.Socket;

public class chatClient
{
    public void startClient(String username)
    {
        try (Socket socket = new Socket("172.0.0.1", 10086) ;
             OutputStream outputStream = socket.getOutputStream() ;
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream))
        {
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
