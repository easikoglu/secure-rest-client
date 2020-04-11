package com.wasabros.securerestclientsamples.configuration;

import com.wasabros.securerestclient.resource.ClientCredentialsResource;
import com.wasabros.securerestclient.resttemplate.SecureRestTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfiguration {

  @Bean
  @ConfigurationProperties("wasabros.secure-rest-client.client.sample-client")
  public ClientCredentialsResource sampleCredentials() {
    return new ClientCredentialsResource();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new SecureRestTemplate(sampleCredentials());
  }
}
