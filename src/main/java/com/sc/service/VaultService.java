package com.sc.service;

import org.springframework.beans.factory.annotation.Value;
import com.sc.exception.ServiceException;
import com.sc.model.VaultItem;



public interface VaultService {
    VaultItem retrieveSecretPropertyFromVault(@Value("${vault.secret.path}") String vaultPath) throws ServiceException;

}
