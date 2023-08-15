package com.sc.config;

import com.sc.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.sc.model.VaultItem;
import com.sc.service.VaultService;

@ConfigurationProperties
public class VaultConfig {

    @Autowired
    private VaultService vaultService;

    public VaultConfig(VaultService vaultService) {
        this.vaultService = vaultService;
    }

    @Value("${vault.secret.path}") String vaultPath;
    @Bean("vaultItem")
    public VaultItem vaultSource() throws ServiceException {
        return vaultService.retrieveSecretPropertyFromVault(vaultPath);
    }


}
