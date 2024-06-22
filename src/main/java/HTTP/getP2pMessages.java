package HTTP;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static HTTP.logInServletReq.MAPPER;
import static HTTP.logInServletReq.cookieStore;

/**
 * @Auther: Edge
 * @Date: 2024/6/22 13:45
 * @Description: TODO
 * @version: 1.0
 **/
public class getP2pMessages implements Callable
{
    private String friendName;

    public getP2pMessages(String friendName)
    {
        /**
         * @description: 构造函数
         * @param:
         * @param friendName
         * @return:
         * @author Edge
         * @date: 2024/6/22 13:45
         **/
        this.friendName = friendName;
    }

    public List<Map<String, Object>> getMessages(String friendName)
    {
        /**
         * @description: 获取指定好友的私聊消息
         * @param:
 * @param friendName 
         * @return: java.util.List<java.util.Map < java.lang.String , java.lang.Object>>
         * @author Edge
         * @date: 2024/6/22 14:03
         **/
        try (CloseableHttpClient httpclient = HttpClients.createDefault() ;)
        {
            //创建一个HttpPost对象，指定请求的URL
            HttpPost post2 = new HttpPost("http://localhost:8080/getp2pMessages");
            //设置请求头
            post2.addHeader("Content-Type" , "application/json");
            //设置请求体
            post2.setEntity(new StringEntity("hello"));
            //创建一个HttpClientContext对象
            HttpClientContext context = HttpClientContext.create();
            //将cookieStore设置到HttpClientContext中
            context.setCookieStore(cookieStore);
            //设置请求参数
            post2.setHeader("friendName" , friendName);
            try (CloseableHttpResponse response2 = httpclient.execute(post2 , context) ;)
            {
                //获取响应体
                BufferedReader in = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
                String inputLine;
                StringBuilder stringBuffer = new StringBuilder();
                while ((inputLine = in.readLine()) != null)
                {
                    stringBuffer.append(inputLine);
                }
                //解析响应体
                List<Map<String, Object>> messages = MAPPER.readValue(stringBuffer.toString() , List.class);
                return messages;
            }
            catch (Exception e)
            {
                return null;
            }
        }
        catch (IOException e)
        {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> call()
    {
        return getMessages(friendName);
    }
}
