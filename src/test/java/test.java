import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class test
{
    @Test
    public void test()
    {
        ObjectMapper MAPPER = new ObjectMapper();
        try (CloseableHttpClient httpclient = HttpClients.createDefault() ;)
        {
            HttpPost post = new HttpPost("http://localhost:8080/logoInResp");
            post.addHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity("hello"));
            //设置请求参数
            post.setHeader("userName" , "admin");
            post.setHeader("password", "123456..a");
            try (CloseableHttpResponse response = httpclient.execute(post))
            {
                System.out.println(response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                if (entity != null)
                {
                    String json = EntityUtils.toString(entity);
                    Map<String, Object> user = MAPPER.readValue(json, Map.class);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error: " + "连接服务器失败");
        }
    }
}
