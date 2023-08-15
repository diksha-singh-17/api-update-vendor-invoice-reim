package com.sc.config;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("")
public class Configuration {
    private String username;
    private String credentials_json;

  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getCredentials_json() {
    return credentials_json;
  }

  public void setCredentials_json(String credentials_json) {
    this.credentials_json = credentials_json;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
    
}
