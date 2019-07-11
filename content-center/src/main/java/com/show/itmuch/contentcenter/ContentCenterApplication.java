package com.show.itmuch.contentcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

// 扫描 mybatis 接口
@SpringBootApplication
@MapperScan("com.show.itmuch.contentcenter")
public class ContentCenterApplication {

  public static void main(String[] args) {

    SpringApplication.run(ContentCenterApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {

    return new RestTemplate();
  }
}
