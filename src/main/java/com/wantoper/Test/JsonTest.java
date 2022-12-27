package com.wantoper.Test;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONObject;

import java.util.Map;
import java.util.Scanner;

public class JsonTest {
    public static void main(String[] args) {

        String header="";
        Scanner scanner = new Scanner(System.in);
        header=scanner.nextLine();

        System.out.println(header);


        Map maps =(Map)JSON.parse(header);
        NameValuePair[] data = new NameValuePair[maps.size()];
        int data_s=0;
        for (Object o : maps.keySet()) {

            String key=o.toString();
            String value= (String) maps.get(o);

            data[data_s]=new NameValuePair(key,value);
        }

        System.out.println(data.toString());

    }
}
