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

import static HTTP.logInServletReq.cookieStore;

public class delFriend implements delFriendImp
{
    @Override
    public boolean delFriendByName(String friendName)
    {
        try (CloseableHttpClient httpclient = HttpClients.createDefault();)
        {
            HttpPost post2 = new HttpPost("http://localhost:8080/delMyFriend");
            post2.addHeader("Content-Type", "application/json");
            post2.setEntity(new StringEntity("hello"));
            HttpClientContext context = HttpClientContext.create();
            context.setCookieStore(cookieStore);
            //设置请求参数
            post2.setHeader("friendName" , friendName);
            try (CloseableHttpResponse response2 = httpclient.execute(post2 , context) ;)
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
                String inputLine;
                StringBuilder stringBuffer = new StringBuilder();
                while ((inputLine = in.readLine()) != null)
                {
                    stringBuffer.append(inputLine);
                }
                in.close();
                System.out.println(stringBuffer);
                return stringBuffer.toString().equals("success");
            }
            catch (Exception e)
            {
                return false;
            }
        }
        catch (IOException e)
        {
            return false;
        }
    }
}
