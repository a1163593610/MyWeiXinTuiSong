package com.example.dacongming;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class DaCongMingControl {

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 5000)
    public String insert(){

        String access_token = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx21a6d4fefda0dd09&secret=bc328d1dc583a01f7dd1cbd66384ca6f",String.class);
        System.out.println(access_token);
        JSONObject jsonObject = JSONObject.parseObject(access_token);
        String access_token1 = jsonObject.getString("access_token");
        MultiValueMap<String ,String> headers = new LinkedMultiValueMap();
        JSONObject data = new JSONObject();
        headers.add("Content-Type","application/json");
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        data.put("touser","oZOlH6kAyLUUGEYpKQYrS09lio0Q");
        data.put("template_id","otDXstNH221MG1s5z3F47ehpzXk0f5rOpb9n2XfCLV4");
        data.put("url","http://weixin.qq.com/download");
        data.put("topcolor","#0000FF");
        JSONObject data3 = new JSONObject();

        JSONObject region = new JSONObject();
        region.put("value","太原市");
        region.put("color","#0000FF");

        JSONObject weather = new JSONObject();
        weather.put("value","太原市");
        weather.put("color","#0000FF");

        data3.put("region",region);
        data.put("data",data3);
        System.out.println(data);

        return restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token1,new HttpEntity<>(data,headers),String.class);

    }
}
