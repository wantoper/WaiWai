package com.wantoper.GUI;

import com.alibaba.fastjson2.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.entity.StringEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static JTextArea t_result;
    public static JTextField t_time;
    public static JRadioButton www_select;
    public static JRadioButton json_select;
    public static JTextField t_url;
    public static JTextArea t_header;
    public static JTextArea t_body;
    public static JTextField t_num;
    public static JTextField t_th;
    public static JScrollPane resultjs;
    public static JButton start_ontime;
    public static ThreadPoolExecutor threadPool;

    public static Thread thread;
    public static void Time_Start() {

        if(thread == null){
            start_ontime.setText("取消定时");

            if(threadPool == null){
                threadPool=new ThreadPoolExecutor(Integer.parseInt(t_num.getText())+10, Integer.parseInt(t_num.getText())+10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
            }
            thread = new Thread(new Runnable() {

                public void run() {
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date parsedate = simpleDateFormat.parse(t_time.getText());
                        long timedata = parsedate.getTime() / 1000;
                        long nowdata = new Date().getTime() / 1000;
                        while (timedata - nowdata >= 0) {
                            consoloe_print((timedata - nowdata)+ "秒后运行...");
                            nowdata = new Date().getTime() / 1000;
                            Thread.sleep(200);
                        }
                        thread_run();
                        thread=null;
                        start_ontime.setText("定时操作");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }else{
            thread.stop();
            thread=null;
            start_ontime.setText("定时操作");
         }

    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("POST 请求");
        jf.setSize(1000,710);
        jf.setLayout(null);

        JLabel j_num = new JLabel("次数:");
        j_num.setBounds(700,0,80,20);
        jf.add(j_num);
        t_num = new JTextField("");
        t_num.setText("1");
        t_num.setBounds(780,0,80,20);
        jf.add(t_num);

        JLabel j_tnum = new JLabel("线程数:");
        j_tnum.setBounds(700,20,80,20);
        jf.add(j_tnum);
        t_th = new JTextField("");
        t_th.setText("10");
        t_th.setBounds(780,20,80,20);
        jf.add(t_th);

        JLabel j_time = new JLabel("定时:");
        j_time.setBounds(700,40,80,20);
        jf.add(j_time);
        t_time = new JTextField("");
        t_time.setText("2022-07-19 19:59:57");
        t_time.setBounds(780,40,120,20);
        jf.add(t_time);



        //单次发送按钮
        JButton b = new JButton("单次测试");
        b.setBounds(700,80,90,30);
        jf.add(b);

        //单次发送按钮
        JButton start = new JButton("开始操作");
        start.setBounds(800,80,90,30);
        jf.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(threadPool == null){
                    threadPool=new ThreadPoolExecutor(Integer.parseInt(t_num.getText())+10, Integer.parseInt(t_num.getText())+10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
                    thread_run();
                    start.setText("取消操作");
                }else{
                    threadPool.shutdown();
                    threadPool=null;
                    start.setText("开始操作");
                }
            }
        });

        //定时按钮
        start_ontime = new JButton("定时操作");
        start_ontime.setBounds(900,80,90,30);
        jf.add(start_ontime);
        start_ontime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Time_Start();
            }
        });


        //URL
        JLabel jLabel_url = new JLabel("URL:");
        jLabel_url.setBounds(18,40,40,50);
        jf.add(jLabel_url);
        t_url = new JTextField("");
        t_url.setBounds(80,50,600,30);
        jf.add(t_url);

        //请求头一块
        JLabel l_header = new JLabel("请求头:");
        l_header.setBounds(18,170,200,50);
        jf.add(l_header);
        JScrollPane js=new JScrollPane();
        js.setBounds(80,120,400,150);
        js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        t_header = new JTextArea();
        js.setViewportView(t_header);
        jf.add(js);

        //请求体一块
        JLabel l_body = new JLabel("请求体:");
        l_body.setBounds(508,170,200,50);
        jf.add(l_body);
        JScrollPane js1=new JScrollPane();
        js1.setBounds(568,120,400,150);
        js1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        js1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        t_body = new JTextArea();
        js1.setViewportView(t_body);
        jf.add(js1);


        json_select = new JRadioButton("json");
        json_select.setSelected(true);
        json_select.setBounds(600, 270, 80, 30);
        www_select = new JRadioButton("x-www-form");
        www_select.setBounds(688, 270, 130, 30);
        jf.add(json_select);
        jf.add(www_select);
        ButtonGroup bg = new ButtonGroup();
        bg.add(json_select);
        bg.add(www_select);

        //日志
        resultjs=new JScrollPane();

        resultjs.setBounds(0,300,985,350);
        resultjs.setHorizontalScrollBarPolicy(resultjs.HORIZONTAL_SCROLLBAR_ALWAYS);
        resultjs.setVerticalScrollBarPolicy(resultjs.VERTICAL_SCROLLBAR_ALWAYS);
        t_result = new JTextArea();
        resultjs.setViewportView(t_result);
        jf.add(resultjs);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                post_run();
            }
        });


        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

    }


    public static void consoloe_print(String text){
        t_result.append(getime()+"==--=="+text+"\n");
        JScrollBar vertical = resultjs.getVerticalScrollBar();
        vertical.setValue( vertical.getMaximum() );
    }

    public static void thread_run(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<Integer.parseInt(t_num.getText());i++){
                    threadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            post_run();
                        }
                    });
                }
            }
        }).start();
    }


    public static void post_run(){
        if(json_select.isSelected()){
            my_json_post(t_url.getText(),t_header.getText(), t_body.getText());
        }else{
            my_www_url_post(t_url.getText(),t_header.getText(), t_body.getText());
        }
    }


    public static void my_json_post(String url,String header,String body){
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
        postMethod.addRequestHeader("content-type","application/json");
        //headerData
        Map headermaps =(Map)JSON.parse(header);
        for (Object o : headermaps.keySet()) {
            String key=o.toString();
            String value= (String) headermaps.get(o);
            postMethod.addRequestHeader(key,value);
        }



        String responseBodyAsString="服务器无响应...";
        try {
            postMethod.setRequestEntity(new StringRequestEntity(body));
            httpClient.executeMethod(postMethod);
            responseBodyAsString = postMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        consoloe_print(responseBodyAsString);
    }


    public static void my_www_url_post(String url,String header,String body){
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
        postMethod.addRequestHeader("content-type","application/x-www-form-urlencoded");
        //headerData
        Map headermaps =(Map)JSON.parse(header);
        for (Object o : headermaps.keySet()) {
            String key=o.toString();
            String value= (String) headermaps.get(o);
            postMethod.addRequestHeader(key,value);
        }

        //BodyData
        Map maps =(Map)JSON.parse(body);
        NameValuePair[] data = new NameValuePair[maps.size()];
        int data_s=0;
        for (Object o : maps.keySet()) {

            String key=o.toString();
            String value= (String) maps.get(o);

            data[data_s]=new NameValuePair(key,value);
            data_s++;
        }
        postMethod.setRequestBody(data);



        String responseBodyAsString="服务器无响应...";
        try {
            httpClient.executeMethod(postMethod);
            responseBodyAsString = postMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        consoloe_print(responseBodyAsString);
    }





    public static String getime(){
        return LocalDateTime.now().toString();
    }
}
