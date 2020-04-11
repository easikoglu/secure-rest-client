package com.wasabros.securerestclient.resource;

public abstract class TokenResource {
  private String grantType;
  private String clientId;
  private String clientSecret;
  private String accessTokenProviderUri;

  public String getGrantType() {
    return grantType;
  }

  public void setGrantType(String grantType) {
    this.grantType = grantType;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getAccessTokenProviderUri() {
    return accessTokenProviderUri;
  }

  public void setAccessTokenProviderUri(String accessTokenProviderUri) {
    this.accessTokenProviderUri = accessTokenProviderUri;
  }
}
