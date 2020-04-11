package com.wasabros.securerestclientsamples;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootApplication
public class SecureRestClientSamplesApplication implements CommandLineRunner {

  @Resource private RestTemplate restTemplate;

  public static void main(String[] args) {
    SpringApplication.run(SecureRestClientSamplesApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    //please provide the url of the secured application you want to call
    String demoUrl =
        "http://remote-secure-api";

    restTemplate.getForEntity(demoUrl, String.class);
  }
}
