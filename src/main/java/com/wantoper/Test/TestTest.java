package com.wantoper.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTest {
    public static void main(String[] args) throws ParseException, InterruptedException {
        String date="2022-07-18 23:45:57";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedate = simpleDateFormat.parse(date);

        long timedata=parsedate.getTime()/1000;
        long nowdata=new Date().getTime()/1000;
        System.out.println(timedata-nowdata);
        while (timedata-nowdata >=0){
            System.out.println(timedata-nowdata+"秒后运行...");
            nowdata = new Date().getTime()/1000;
            Thread.sleep(200);
        }

    }
}
