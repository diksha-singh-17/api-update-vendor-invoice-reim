package com.sc.service;

import com.sc.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sc.service.impl.VaultServiceImpl;
import com.sc.exception.ServiceException;
import com.sc.model.VaultItem;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sc.util.LoggerUtility;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VaultServiceImplTest {
   @Mock
   VaultService vaultService;
   @Mock
   LoggerUtility log;

   @BeforeEach
   void setup(){
      vaultService=new VaultServiceImpl();
      ReflectionTestUtils.setField(vaultService, "clientId","test client" );
      ReflectionTestUtils.setField(vaultService, "accessCodeClientSecret","test client" );
      ReflectionTestUtils.setField(vaultService, "grantType","test client" );
      ReflectionTestUtils.setField(vaultService, "clientId","054B7GHdix5CR5apPVOzIzgYZBEUhlEv2QMKp304vPSUGIZC" );
      ReflectionTestUtils.setField(vaultService, "accessCodeClientSecret","VFYVPqgGjiFTVKsplAzfOpkdY76AZkTb2Viz4WZdkypTYdAb11coTdJPUXKCTi15" );
      ReflectionTestUtils.setField(vaultService, "grantType","authorization_code" );
      ReflectionTestUtils.setField(vaultService, "accessCodeRedirectUri","https://dev-api.sleepcountry.ca/extractcode" );
      ReflectionTestUtils.setField(vaultService, "apigeeAuthUrl","https://dev-api.sleepcountry.ca/oauth/authorize" );
      ReflectionTestUtils.setField(vaultService, "apigeeTokenUrl","https://dev-api.sleepcountry.ca/oauth/token" );

   }
   @Test
   void retrieveSecretPropertyFromVaultTest() {
      try{
         RestTemplate template = mock(RestTemplate.class);
         String apigeeCode = TestData.APIGIE_CODE;
         String accessToken = TestData.APIGIE_CODE;
         final ResponseEntity<String> responseApigeeCode = Mockito.mock(ResponseEntity.class);
         when(template.postForEntity(Mockito.eq("http://test.com"), Mockito.any(HttpEntity.class),
                 Mockito.eq(String.class))).thenReturn(responseApigeeCode);
         Mockito.when(responseApigeeCode.getBody()).thenReturn(apigeeCode);

         final ResponseEntity<String> response = Mockito.mock(ResponseEntity.class);

         when(template.postForEntity(Mockito.eq("http://test.com"), Mockito.any(HttpEntity.class), Mockito.eq(String.class))).thenReturn(response);
         Mockito.when(response.getBody()).thenReturn(accessToken);
         VaultItem vaultItem = vaultService.retrieveSecretPropertyFromVault(TestData.VAULT_PATH);
         Assertions.assertNotNull(vaultItem);
      } catch (ServiceException e) {
            log.error(TestData.APPNAME +"{retrieveSecretPropertyFromVaultTest()}",e,TestData.CORRELATION_ID);
      }
   }
}
