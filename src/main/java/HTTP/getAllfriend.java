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

public class getAllfriend implements getAllFriendsImp, Callable
{

    @Override
    public List<Map<String, Object>> getAllFriends()
    {
        try (CloseableHttpClient httpclient = HttpClients.createDefault() ;)
        {
            HttpPost post = new HttpPost("http://localhost:8080/loadAllFriendMessage");
            post.addHeader("Content-Type" , "application/json");
            HttpClientContext context = HttpClientContext.create();
            context.setCookieStore(cookieStore);
            try (CloseableHttpResponse response = httpclient.execute(post , context) ;)
            {
                // 获取全部响应信息
                String responseString = EntityUtils.toString(response.getEntity());
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


    @Override
    public List<Map<String, Object>> call()
    {
        return getAllFriends();
    }
}
