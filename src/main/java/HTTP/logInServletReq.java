package HTTP;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.example.javachat.Login.userMap;

public class logInServletReq implements logInServletReqImp
{
    private static final ObjectMapper MAPPER = new ObjectMapper();
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
            try (CloseableHttpResponse response = httpclient.execute(post))
            {
                System.out.println(response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                if (entity != null)
                {
                    String json = EntityUtils.toString(entity);
                    Map<String, Object> user = MAPPER.readValue(json, Map.class);
                    if (user.get("username") != null)
                    {
                        userMap = user;
                    }
                    return user.get("username") != null;
                }
                return false;
            }
        }
        catch (IOException e)
        {
            System.out.println("Error: " + "连接服务器失败");
            return false;
        }
    }
}
