package HTTP;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.example.javachat.Main;
import org.example.javachat.UserList;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class logInServletReq implements logInServletReqImp
{
    public logInServletReq()
    {
    }

    public static final ObjectMapper MAPPER = new ObjectMapper();
    // 创建一个CookieStore对象
    public static CookieStore cookieStore = new BasicCookieStore();
    @Override
    public boolean logIn(String userName, String password)
    {
        try (CloseableHttpClient httpclient = HttpClients.createDefault() ;)
        {
            HttpPost post = new HttpPost("http://localhost:8080/logoInResp");
            post.addHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity("hello"));
            //设置请求参数
            post.setHeader("userName" , userName);
            post.setHeader("password", password);
            HttpClientContext context = HttpClientContext.create();
            context.setCookieStore(cookieStore);
            try (CloseableHttpResponse response = httpclient.execute(post , context))
            {
                if (!cookieStore.getCookies().isEmpty())
                {
                    System.out.println("登录成功");
                    try
                    {
                        // 异步获取响应结果
                        getAllfriend call = new getAllfriend();
                        FutureTask<List<Map<String, Object>>> futureTask = new FutureTask<>(call);
                        new Thread(futureTask).start();
                        List<Map<String, Object>> result = futureTask.get();
                        UserList userList = new UserList(result);
                        Main.changeView("userList.fxml" , userList);
                        System.out.println(result);
                        new Thread(new chatReceiver()).start();
                    }
                    catch (InterruptedException | ExecutionException e)
                    {
                        throw new RuntimeException(e);
                    }
                    // 输出Cookie信息
                    for (Cookie cookie : cookieStore.getCookies())
                    {
                        System.out.println(cookie.getName() + " : " + cookie.getValue());
                        if (cookie.getName().equals("username"))
                        {
                            new Thread(new chatClient(userName)).start();
                        }
                    }
                    return true;
                }
                else
                {
                    System.out.println("Error: " + "登录失败");
                    return false;
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error: " + "连接服务器失败");
            return false;
        }
    }
}
