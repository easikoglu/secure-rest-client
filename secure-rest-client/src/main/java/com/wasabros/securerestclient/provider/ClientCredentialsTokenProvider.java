package com.wasabros.securerestclient.provider;

import com.wasabros.securerestclient.resource.TokenResource;

public class ClientCredentialsTokenProvider extends TokenProvider {
  public ClientCredentialsTokenProvider(TokenResource resource) {
    super(resource);
  }
}
