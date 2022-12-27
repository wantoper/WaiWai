package com.wantoper.Test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class PostDemo1 {
    public static void main(String[] args) throws Exception{
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod("https://login.yunjiezhimen.com/prod-api/order/goods");
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
        postMethod.addRequestHeader("content-type","application/x-www-form-urlencoded");
        postMethod.addRequestHeader("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI4NDM2MyIsInN1YiI6IumZiOWwmuadsCIsImlhdCI6MTY1Nzk2NDUxOSwiaXNzIjoiQ1EiLCJleHAiOjE2NTgzMTQ5NzcsImlwIjoiMjIzLjc0LjE4My40NSwgMTAzLjIzOS4yNDcuMjE5LCAxMDAuMTE3LjEwNC44MSJ9.bmHIYKls16jghF82af4nu0dgStPM7TWCF0lFALGR_b94cMXDpvEQrz9dpCMprj3GHIoVMvkAu6aSu3bbRTQtcQ");



        NameValuePair[] data = {
                new NameValuePair("goodsId","10207")
        };
        postMethod.setRequestBody(data);

        httpClient.executeMethod(postMethod);
        String responseBodyAsString = postMethod.getResponseBodyAsString();

        System.out.println(responseBodyAsString);

    }
}
