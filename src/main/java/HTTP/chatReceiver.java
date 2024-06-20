package HTTP;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static HTTP.chatClient.socket;
import static HTTP.logInServletReq.MAPPER;

public class chatReceiver extends Thread
{
    public void run()
    {
        try (InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream))
        {
            while (true)
            {
                String message = dataInputStream.readUTF();
                System.out.println(message);
                Map<String, String> messageMap = MAPPER.readValue(message, Map.class);
                System.out.println(messageMap);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
