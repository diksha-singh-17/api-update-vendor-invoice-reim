package com.sc.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sc.service.VaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sc.exception.ServiceException;
import com.sc.model.VaultItem;
import com.jayway.jsonpath.JsonPath;
import sc.util.LoggerUtility;


@Service
public class VaultServiceImpl implements VaultService {
    @Autowired
    LoggerUtility log;

    @Value("${sc.apigee.client_id}")
    String clientId;
    @Value("git ${sc.apigee.client_secret}")
    String accessCodeClientSecret;
    @Value("${sc.apigee.grantType}")
    String grantType;
    @Value("${sc.apigee.accessCodRredirectUri}")
    String accessCodeRedirectUri;
    @Value("${sc.apigee.apiggeTokenUrl}")
    String apigeeTokenUrl;
    @Value("${sc.apigee.apigeeAuthUrl}")
    String apigeeAuthUrl;



    @Override
    public VaultItem retrieveSecretPropertyFromVault(String vaultPath) throws ServiceException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(getApigeeToken());
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(vaultPath);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
                    String.class);

            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(responseBody, VaultItem.class);
        } catch (Exception ex) {
            log.error("Error while calling Vault API",ex,"");
        }
        return null;
    }

    public String getApigeeToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", accessCodeClientSecret);
        map.add("grant_type", grantType);
        map.add("code", getApigeeTokenStep1());
        map.add("redirect_uri", accessCodeRedirectUri);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apigeeTokenUrl, request, String.class);
        String jsonResp = response.getBody();
        return JsonPath.parse(jsonResp).read("$.access_token");
    }

    public String getApigeeTokenStep1() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        String url = new StringBuilder().append(apigeeAuthUrl).append("?response_type=code&client_id=").append(clientId)
                .append("&redirect_uri=").append(accessCodeRedirectUri).toString();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        String jsonResp = responseEntity.getBody();
        return JsonPath.parse(jsonResp).read("$.code");
    }
}

