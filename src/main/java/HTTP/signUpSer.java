package HTTP;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.example.javachat.Main;
import org.example.javachat.UserList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description: 注册事件
 * @ClassName: signUp
 * @Author: Edge
 * @Date: 2024/6/25 21:12
 * @Version: 1.0
 */
public class signUpSer
{
    public boolean signUp(String username , String password)
    {
        try (CloseableHttpClient httpclient = HttpClients.createDefault() ;)
        {
            HttpPost post = new HttpPost("http://localhost:8080/signUp");
            post.addHeader("Content-Type" , "application/json");
            post.setEntity(new StringEntity("hello"));
            //设置请求参数
            post.setHeader("username" , username);
            post.setHeader("password" , password);
            try (CloseableHttpResponse response = httpclient.execute(post) ;)
            {
                //获取响应体
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String inputLine;
                StringBuilder stringBuffer = new StringBuilder();
                while ((inputLine = in.readLine()) != null)
                {
                    stringBuffer.append(inputLine);
                }
                in.close();
                System.out.println(stringBuffer);
                //判断响应体是否为success
                return stringBuffer.toString().equals("success");
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
