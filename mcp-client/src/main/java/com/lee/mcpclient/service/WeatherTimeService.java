package com.lee.mcpclient.service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class WeatherTimeService {

    private ChatClient chatClient;

    public WeatherTimeService(
            ChatClient.Builder chatClientBuilder,
            ToolCallbackProvider tools) {
        this.chatClient = chatClientBuilder
                .defaultSystem("你是一个专业的天气助手，能够提供准确的天气信息。你的回复应该简洁明了，直接提供用户请求的城市的天气情况。")
                .defaultTools(tools)
                .build();
    }


    public String getWeatherAndTime(String city) {
        // 调用天气工具
        String weatherPrompt = String.format("请调用天气工具查询%s的当前天气情况。", city);
        Prompt weatherPromptObj = new Prompt(new UserMessage(weatherPrompt));
        String text = chatClient.prompt(weatherPromptObj).call().content();

        return text;
    }
}