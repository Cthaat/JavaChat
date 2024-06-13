package HTTP;

import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class http
{
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static void main(String[] args)
    {
        try (CloseableHttpClient httpclient = HttpClients.createDefault();)
        {
            HttpPost post = new HttpPost("http://localhost:8080/hello");
            post.addHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity("hello"));
            //设置请求参数
            post.setHeader("userName" , "admin");
            post.setHeader("userID", "10000001");
            try (CloseableHttpResponse response = httpclient.execute(post))
            {
                System.out.println(response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                if (entity != null)
                {
                    String json = EntityUtils.toString(entity);
                    System.out.println(json);
                    ArrayList list = MAPPER.readValue(json, ArrayList.class);
                    System.out.println(list);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error: " + "连接服务器失败");
        }
    }
}
