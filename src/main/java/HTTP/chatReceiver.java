package HTTP;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static HTTP.chatClient.socket;
import static HTTP.logInServletReq.MAPPER;

/**
 * @Auther: Edge
 * @Date: 2024/6/22 13:39
 * @Description: TODO
 * @version: 1.0
 */
public class chatReceiver extends Thread
{
    // 声明一个try-with-resources语句，确保在代码块执行完毕后，自动关闭资源
    public void run()
    {
        /**
         * @description: 使用try-with-resources语句，确保在代码块执行完毕后，自动关闭资源
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:44
         **/
        try (InputStream inputStream = socket.getInputStream() ;
             DataInputStream dataInputStream = new DataInputStream(inputStream))
        {
            // 使用while循环来不断接收客户端发送的消息
            while (true)
            {
                // 从输入流中读取UTF编码的字符串
                String message = dataInputStream.readUTF();
                // 打印消息
                System.out.println(message);
                // 将消息转换为Map对象
                Map<String, String> messageMap = MAPPER.readValue(message , Map.class);
                // 打印Map对象
                System.out.println(messageMap);
            }
        }
        // 捕获IO异常，并抛出运行时异常
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
