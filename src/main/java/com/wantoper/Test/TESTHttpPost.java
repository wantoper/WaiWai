package com.wantoper.Test;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import sun.nio.cs.ext.GBK;

public class TESTHttpPost {

    public static void main(String[] args) throws Exception{
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://api.taoyuzhou.net/api/v1/product/list");
        httpPost.setHeader("Content-type", "application/json");

        JSONObject obj = new JSONObject();
        obj.put("page", "1");
        obj.put("limit", "1000");
        System.out.println(obj.toString());
        httpPost.setEntity(new StringEntity(obj.toString()));

        CloseableHttpResponse response = client.execute(httpPost);
        String s = EntityUtils.toString(response.getEntity());

        System.out.println(s);
    }
}
