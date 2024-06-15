package HTTP;

import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class http
{
    // 定义一个ObjectMapper对象
private static final ObjectMapper MAPPER = new ObjectMapper();
    public static void main(String[] args)
    {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();)
        {
            // 创建一个HttpPost对象，指定请求的URL
            HttpPost anotherRequest = new HttpPost("http://localhost:8080/Main");
            // 执行请求，获取响应
            HttpResponse anotherResponse = httpClient.execute(anotherRequest);
            // 将响应转换为字符串
            String responseString = EntityUtils.toString(anotherResponse.getEntity());
            // 打印响应字符串
            System.out.println(responseString);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
