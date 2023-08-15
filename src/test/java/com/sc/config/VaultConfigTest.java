package com.sc.config;

import com.sc.TestData;
import com.sc.model.VaultItem;
import com.sc.service.VaultService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sc.exception.ServiceException;
import sc.util.LoggerUtility;


@ExtendWith(SpringExtension.class)
class VaultConfigTest {
    @Mock
    VaultConfig vaultConfig;
    @Mock
    LoggerUtility log;
    @Mock
    VaultService vaultService;
    @BeforeEach
    void setup() {
        vaultConfig = new VaultConfig(vaultService);
    }
    @Test
    void testVaultSource() throws ServiceException {
        try {
            VaultItem vaultItem = vaultConfig.vaultSource();
            Assertions.assertNull(vaultItem);
        }catch (Exception e) {
            log.error(TestData.APPNAME +"{testVaultSource()}",e,TestData.CORRELATION_ID);

        }
    }
}
