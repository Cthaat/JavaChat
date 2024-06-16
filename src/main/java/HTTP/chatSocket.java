package HTTP;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;

public class chatSocket implements Runnable
{
    @Override
    public void run()
    {
        try (Socket socket = new Socket("localhost", 10086);)
        {

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
