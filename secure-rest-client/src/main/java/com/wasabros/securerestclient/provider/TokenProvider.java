package com.wasabros.securerestclient.provider;

import com.wasabros.securerestclient.exception.AcquireTokenException;
import com.wasabros.securerestclient.resource.TokenResource;
import com.wasabros.securerestclient.token.dto.AccessTokenResponse;
import com.wasabros.securerestclient.token.model.AccessToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.wasabros.securerestclient.constants.HeaderParameters.*;

public abstract class TokenProvider {

  private RestTemplate restTemplate;
  private TokenResource tokenResource;
  private AccessToken token;

  protected TokenProvider(TokenResource tokenResource) {
    this.tokenResource = tokenResource;
  }

  protected RestTemplate getRestTemplate() {
    if (this.restTemplate == null) {
      synchronized (this) {
        if (this.restTemplate == null) {
          this.restTemplate = new RestTemplate();
        }
      }
    }
    return restTemplate;
  }

  protected HttpEntity getRequestEntity() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add(GRANT_TYPE, getTokenResource().getGrantType());
    map.add(CLIENT_ID, getTokenResource().getClientId());
    map.add(CLIENT_SECRET, getTokenResource().getClientSecret());
    return new HttpEntity<>(map, headers);
  }

  public AccessToken getToken() {
    if (Objects.isNull(token) || token.isExpired()) {
      token = acquireNewToken();
    }
    return token;
  }

  protected AccessToken acquireNewToken() {
    ResponseEntity<AccessTokenResponse> response =
        getRestTemplate()
            .postForEntity(
                getTokenResource().getAccessTokenProviderUri(),
                getRequestEntity(),
                AccessTokenResponse.class);

    if (response.hasBody()) {
      return new AccessToken(response.getBody());
    }
    throw new AcquireTokenException();
  }

  protected TokenResource getTokenResource() {
    return tokenResource;
  }
}
