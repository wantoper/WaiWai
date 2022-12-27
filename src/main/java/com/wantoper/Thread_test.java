package com.wantoper;


import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.nio.cs.ext.GBK;

import java.io.IOException;
import java.net.URISyntaxException;

public class Thread_test {

    public static void main(String[] args) throws Exception {

        CloseableHttpClient client = HttpClients.createDefault();

        URIBuilder uriBuilder = new URIBuilder("https://www.baidu.com/s");
        uriBuilder.setParameter("wd","吃饭了嘛");
        uriBuilder.setCharset(new GBK());
        System.out.println(uriBuilder.build());

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("user-agent","Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
        CloseableHttpResponse response = client.execute(httpGet);

        HttpEntity entity = response.getEntity();
        String res = EntityUtils.toString(entity);
        System.out.println(res);
    }
}
