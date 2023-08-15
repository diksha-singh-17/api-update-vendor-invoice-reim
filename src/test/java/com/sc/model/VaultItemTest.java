package com.sc.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class VaultItemTest {

    @Test
    void testVaultItem() {
        VaultItem item = new VaultItem();
        item.setFtpHost(null);
        item.setFtpHost(null);
        item.setFtpUser(null);
        item.setFtpPass(null);
        item.setFtpRemoteDirectory(null);
        item.setFtpLocalDirectory(null);
        item.setZipFilePath(null);
        item.setPassword(null);
        item.setUserName(null);
        item.setLocalFilePath(null);
        item.setRemoteFilePath(null);
        item.setRemoteTempFilePath(null);
        item.setHostName(null);


        assertNull(item.getFtpHost());
        assertNull(item.getFtpPort());
        assertNull(item.getFtpUser());
        assertNull(item.getFtpPass());
        assertNull(item.getFtpRemoteDirectory());
        assertNull(item.getFtpLocalDirectory());
        assertNull(item.getZipFilePath());
        assertNull(item.getPassword());
        assertNull(item.getUserName());
        assertNull(item.getLocalFilePath());
        assertNull(item.getRemoteFilePath());
        assertNull(item.getRemoteTempFilePath());
        assertNull(item.getHostName());

    }
}

