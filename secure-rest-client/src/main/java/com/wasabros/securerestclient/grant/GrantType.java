package com.wasabros.securerestclient.grant;

public enum GrantType {
  CLIENT_CREDENTIALS("client_credentials");

  private String type;

  public String getType() {
    return this.type;
  }

  GrantType(String type) {
    this.type = type;
  }
}
