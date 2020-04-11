package com.wasabros.securerestclient.resttemplate;

import com.wasabros.securerestclient.provider.TokenProvider;
import com.wasabros.securerestclient.provider.TokenProviderFactory;
import com.wasabros.securerestclient.resource.TokenResource;
import com.wasabros.securerestclient.token.model.AccessToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

public class SecureRestTemplate extends RestTemplate {

  private ClientHttpRequestInterceptor interceptor;
  private TokenProvider tokenProvider;

  public SecureRestTemplate(TokenResource tokenResource) {
    tokenProvider = TokenProviderFactory.create(tokenResource);

    interceptor =
        (request, body, execution) -> {
          AccessToken token = tokenProvider.getToken();
          request.getHeaders().add(HttpHeaders.AUTHORIZATION, prepareAuthorizationHeader(token));
          return execution.execute(request, body);
        };

    this.getInterceptors().add(interceptor);
  }

  private String prepareAuthorizationHeader(AccessToken accessToken) {
    return MessageFormat.format(
        "{0} {1}", accessToken.getTokenType(), accessToken.getAccessToken());
  }
}
