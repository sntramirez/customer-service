package com.dev.customerservice.infraestructure.data.repository.integration;

import org.springframework.http.*;

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
