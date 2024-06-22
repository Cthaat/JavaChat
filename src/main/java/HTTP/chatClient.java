package HTTP;

import java.io.*;
import java.net.Socket;

/**
 * @Auther: Edge
 * @Date: 2024/6/22 13:39
 * @Description: TODO
 * @version: 1.0
 */
public class chatClient extends Thread
{
    // 定义一个DataOutputStream对象
    public static DataOutputStream dataOutputStream;
    // 定义一个Socket对象
    public static final Socket socket;
    // 定义一个username变量
    private String username;

    // chatClient构造函数，传入一个username参数
    public chatClient(String username)
    {
        this.username = username;
    }

    // static代码块，在类被加载时执行
    static
    {
        try
        {
            // 创建一个Socket对象，连接到本地主机，端口号为10086
            socket = new Socket("localhost" , 10086);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    // run方法，客户端的主循环
    public void run()
    {
        /**
         * @description: 客户端的主循环
         * @param: [message]
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:39
         */
        try (OutputStream outputStream = socket.getOutputStream() ;)
        {
            // 将DataOutputStream对象赋值给dataOutputStream
            dataOutputStream = new DataOutputStream(outputStream);
            // 将username写入到dataOutputStream中
            dataOutputStream.writeUTF(username);
            // 无限循环
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
