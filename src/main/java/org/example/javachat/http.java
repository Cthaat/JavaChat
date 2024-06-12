package org.example.javachat;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class http
{
    public static void main(String[] args)
    {
        try (CloseableHttpClient httpclient = HttpClients.createDefault();)
        {
            HttpPost post = new HttpPost("http://localhost:8080/hello");
            try (CloseableHttpResponse response = httpclient.execute(post))
            {
                System.out.println(response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                if (entity != null)
                {
                    System.out.println(EntityUtils.toString(entity));
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
