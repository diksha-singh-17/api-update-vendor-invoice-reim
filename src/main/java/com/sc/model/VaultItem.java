package com.sc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class VaultItem {

    private String ftpHost;


    private String ftpPort;

    private String ftpUser;

    private String ftpPass;

    private String ftpLocalDirectory;

    private String ftpRemoteDirectory;
    @JsonProperty("zipFilePath")
    private String zipFilePath;

    @JsonProperty("hostName")
    private String hostName;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("localFilePath")
    private String localFilePath;
    @JsonProperty("remoteFilePath")
    private String remoteFilePath;
    @JsonProperty("remoteTempFilePath")
    private String remoteTempFilePath;

    public String getFtpHost() {
        return ftpHost;
    }

    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost;
    }

    public String getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public String getFtpPass() {
        return ftpPass;
    }

    public void setFtpPass(String ftpPass) {
        this.ftpPass = ftpPass;
    }

    public String getFtpLocalDirectory() {
        return ftpLocalDirectory;
    }

    public void setFtpLocalDirectory(String ftpLocalDirectory) {
        this.ftpLocalDirectory = ftpLocalDirectory;
    }

    public String getFtpRemoteDirectory() {
        return ftpRemoteDirectory;
    }

    public void setFtpRemoteDirectory(String ftpRemoteDirectory) {
        this.ftpRemoteDirectory = ftpRemoteDirectory;
    }

    public String getZipFilePath() {
        return zipFilePath;
    }

    public void setZipFilePath(String zipFilePath) {
        this.zipFilePath = zipFilePath;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }

    public String getRemoteFilePath() {
        return remoteFilePath;
    }

    public void setRemoteFilePath(String remoteFilePath) {
        this.remoteFilePath = remoteFilePath;
    }

    public String getRemoteTempFilePath() {
        return remoteTempFilePath;
    }

    public void setRemoteTempFilePath(String remoteTempFilePath) {
        this.remoteTempFilePath = remoteTempFilePath;
    }
}