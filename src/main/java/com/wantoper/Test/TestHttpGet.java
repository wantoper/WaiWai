package com.wantoper.Test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.nio.cs.ext.GBK;

public class TestHttpGet {

    public static void main(String[] args) throws Exception{
        CloseableHttpClient client = HttpClients.createDefault();

        URIBuilder uriBuilder = new URIBuilder("https://www.baidu.com/s");
        uriBuilder.setParameter("wd","吃饭了嘛");
        uriBuilder.setCharset(new GBK());
        System.out.println(uriBuilder.build());

        org.apache.http.client.methods.HttpGet httpGet = new org.apache.http.client.methods.HttpGet(uriBuilder.build());
        httpGet.setHeader("user-agent","Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
        CloseableHttpResponse response = client.execute(httpGet);

        HttpEntity entity = response.getEntity();
        String res = EntityUtils.toString(entity);
        System.out.println(res);
    }
}
