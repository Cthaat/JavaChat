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

public class http
{
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static void main(String[] args)
    {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();)
        {
            HttpPost anotherRequest = new HttpPost("http://localhost:8080/Main");
            HttpResponse anotherResponse = httpClient.execute(anotherRequest);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
