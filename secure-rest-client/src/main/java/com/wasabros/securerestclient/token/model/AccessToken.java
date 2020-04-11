package com.wasabros.securerestclient.token.model;

import com.wasabros.securerestclient.token.dto.AccessTokenResponse;

import java.util.Date;

public class AccessToken {
  private String accessToken;
  private String refreshToken;
  private String tokenType;
  private String scope;
  private long expiresIn;
  private Date expirationDate;

  public AccessToken(AccessTokenResponse accessTokenResponse) {
    this.accessToken = accessTokenResponse.getAccessToken();
    this.refreshToken = accessTokenResponse.getRefreshToken();
    this.expiresIn = accessTokenResponse.getExpiresIn();
    this.tokenType = accessTokenResponse.getTokenType();
    this.scope = accessTokenResponse.getScope();
    setExpirationDate();
  }

  public boolean isExpired() {
    return getExpirationDate().before(new Date());
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public String getScope() {
    return scope;
  }

  public long getExpiresIn() {
    return expiresIn;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  private void setExpirationDate() {
    this.expirationDate = new Date(System.currentTimeMillis() + expiresIn);
  }
}
