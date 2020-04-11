package com.wasabros.securerestclient.provider;

import com.wasabros.securerestclient.grant.GrantType;
import com.wasabros.securerestclient.resource.TokenResource;

public class TokenProviderFactory {

  public static TokenProvider create(TokenResource tokenResource) {
    if (GrantType.CLIENT_CREDENTIALS.getType().equals(tokenResource.getGrantType())) {
      return new ClientCredentialsTokenProvider(tokenResource);
    } else {
      throw new RuntimeException("not implemented yet");
    }
  }
}
