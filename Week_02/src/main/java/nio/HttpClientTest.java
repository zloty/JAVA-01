package nio;

import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientTest {
  public static void main(String[] args) {
    CloseableHttpClient client = HttpClients.createDefault();
    String url = "http://localhost:8801";
    HttpGet httpGet = new HttpGet(url);
    CloseableHttpResponse response = null;
    try {
      response = client.execute(httpGet);
      StatusLine status = response.getStatusLine();
      if (HttpStatus.SC_OK == status.getStatusCode()) {
        System.out.println("响应成功");
        System.out.println("调用结果：" + EntityUtils.toString(response.getEntity(), Consts.UTF_8));
      } else {
        System.out.println("响应失败：" + status.getStatusCode());
      }

      EntityUtils.consume(response.getEntity());
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try{
        if (client != null) {
          client.close();
        }
        if (response != null) {
          response.close();
        }
      } catch (IOException e){
        e.printStackTrace();
      }

    }

  }
}
