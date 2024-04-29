package com.devsu.customerservice.infraestructure.data.repository.integration;

import com.devsu.customerservice.application.api.model.EmbeddedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.util.Collections;

public class TestUtils {


    public static HttpEntity getEntityWithBodyAndTenant(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Content-Type", "application/json");
        return new HttpEntity<>(body, headers);
    }

    public static HttpEntity getEntityWithTenant() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>("body", headers);
    }
}
