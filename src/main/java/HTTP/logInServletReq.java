package HTTP;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class logInServletReq implements logInServletReqImp
{
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static CookieStore cookieStore = new BasicCookieStore();
    @Override
    public boolean logIn(String userName, String password)
    {
        // 检查Cookie是否存在
        // 若存在，则直接返回true
        if (!cookieStore.getCookies().isEmpty())
        {
            // 输出Cookie信息
            for (Cookie cookie : cookieStore.getCookies())
            {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
            return true;
        }
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
                    // 输出Cookie信息
                    for (Cookie cookie : cookieStore.getCookies())
                    {
                        System.out.println(cookie.getName() + " : " + cookie.getValue());
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
