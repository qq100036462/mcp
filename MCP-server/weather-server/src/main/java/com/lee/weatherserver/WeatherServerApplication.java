package com.lee.weatherserver;

import com.lee.weatherserver.tool.WeatherTool;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class WeatherServerApplication {

	public static void main(String[] args) {
		// 重定向日志到文件
		/*try {
			System.setOut(new java.io.PrintStream(new java.io.FileOutputStream("weather-server.log", true)));
			System.setErr(new java.io.PrintStream(new java.io.FileOutputStream("weather-server.log", true)));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		ApplicationContext context = SpringApplication.run(WeatherServerApplication.class, args);

	}

	@Bean
	public ToolCallbackProvider weatherTools(WeatherTool weatherService) {
		System.out.println("== 调试工具注册 ==");
		Arrays.stream(weatherService.getClass().getDeclaredMethods())
				.forEach(m -> System.out.println("方法: " + m.getName()));
		ToolCallbackProvider provider = MethodToolCallbackProvider.builder()
				.toolObjects(weatherService)
				.build();
		System.out.println("工具注册 tools: " + Arrays.stream(provider.getToolCallbacks()).findAny());
		return MethodToolCallbackProvider.builder()
				.toolObjects(weatherService)
				.build();
	}

}
