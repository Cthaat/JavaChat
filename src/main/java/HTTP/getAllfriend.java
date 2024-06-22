package HTTP;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
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
public class getAllfriend implements getAllFriendsImp, Callable
{
    // 实现getAllFriends方法，获取全部好友信息
    @Override
    public List<Map<String, Object>> getAllFriends()
    {
        /**
         * @description: 获取全部好友信息
         * @param: 
         * @return: java.util.List<java.util.Map < java.lang.String , java.lang.Object>>
         * @author Edge
         * @date: 2024/6/22 13:45
         **/
        try (CloseableHttpClient httpclient = HttpClients.createDefault() ;)
        {
            // 创建HttpPost对象，设置请求url
            HttpPost post = new HttpPost("http://localhost:8080/loadAllFriendMessage");
            // 设置请求头
            post.addHeader("Content-Type" , "application/json");
            // 创建HttpClientContext对象
            HttpClientContext context = HttpClientContext.create();
            // 将cookieStore设置到context中
            context.setCookieStore(cookieStore);
            try (CloseableHttpResponse response = httpclient.execute(post , context) ;)
            {
                // 获取全部响应信息
                String responseString = EntityUtils.toString(response.getEntity());
                // 将响应信息转换为List<Map<String, Object>>
                return MAPPER.readValue(responseString , List.class);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    // 实现call方法，调用getAllFriends方法
    @Override
    public List<Map<String, Object>> call()
    {
        return getAllFriends();
    }
}
