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

/**
 * @Auther: Edge
 * @Date: 2024/6/22 13:38
 * @Description: TODO
 * @version: 1.0
 */

public class addFriend implements addFriendImp
{
    @Override
    public boolean addFriendByName(String friendName)
    {
        /**
         * @description: 向服务器发送添加好友请求
         * @param:
         * @param friendName 好友的用户名
         * @return: boolean
         * @author Edge
         * @date: 2024/6/22 13:44
         **/
        try (CloseableHttpClient httpclient = HttpClients.createDefault() ;)
        {
            //创建一个HttpPost对象，指定请求的URL
            HttpPost post2 = new HttpPost("http://localhost:8080/addMyFriend");
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
                in.close();
                System.out.println(stringBuffer);
                //判断响应体是否为success
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
