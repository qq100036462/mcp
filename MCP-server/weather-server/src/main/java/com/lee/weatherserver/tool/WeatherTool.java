package com.lee.weatherserver.tool;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class WeatherTool {

    @Tool(description = "根据城市获取天气")
    public String getWeather(String city) {

        System.out.println("城市参数: " + city);

        return city + "天气： 气温"+ ThreadLocalRandom.current().nextInt(15, 35 + 1) +"°C，湿度：" + ThreadLocalRandom.current().nextInt(45, 65 + 1) + "%";
    }
}