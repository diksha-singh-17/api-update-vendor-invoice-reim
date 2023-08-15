package com.sc;

import com.sc.model.VaultItem;

public class TestData {
    public static String CORRELATION_ID="CORRELATION_ID";
    public static String APPNAME="api-update-vendor-invoice-reim";

    public static final String APIGIE_CODE="{code:\"test\"}";
    public static final String VAULT_PATH="https://dev-api.sleepcountry.ca/v1/vault-api/secret/sleepcountry/dev/rmsCloudsql";

    public static VaultItem prepareVault(){
        VaultItem vaultItem = new VaultItem();
        vaultItem.setFtpHost("35.227.69.29");
        vaultItem.setFtpPort("21");
        vaultItem.setFtpUser("scc-ftp");
        vaultItem.setFtpPass("Md19gFsP10");
        vaultItem.setFtpLocalDirectory("src/test/resources/testing");
        vaultItem.setFtpRemoteDirectory("/UAT/In");
        vaultItem.setZipFilePath("src/test/resources/edidlinv.zip");
        vaultItem.setLocalFilePath("src/test/resources/testing");

        return vaultItem;
    }
}
