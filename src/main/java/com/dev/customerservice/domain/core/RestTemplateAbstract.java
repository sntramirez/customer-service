package com.dev.customerservice.domain.core;

import com.dev.customerservice.application.api.model.EmbeddedDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class RestTemplateAbstract {

    // 5 minutos en milisegundos
    static final int TIMEOUT = 1000 * 60 * 5;

    @Value("${client.cuentas.baseUrl}")
    protected String urlCuentas;

    @Value("${client.movimientos.baseUrl}")
    protected String urlMovimientos;

    protected <T> ResponseEntity<EmbeddedDto<T>> findByIdEmbedded(String url, Long id,
                                                                  ParameterizedTypeReference<EmbeddedDto<T>> parameterizedTypeReference) {

        RestTemplate restTemplate = createRestTemplateWithTimeOut(TIMEOUT);
        return processRequest(restTemplate.exchange(url, HttpMethod.GET,
                getHttpEntity(), parameterizedTypeReference, id));
    }

    protected <T> ResponseEntity<EmbeddedDto<T>> findByParameter(String url, String parameter,
                                                                 ParameterizedTypeReference<EmbeddedDto<T>> parameterizedTypeReference) {
        RestTemplate restTemplate = createRestTemplateWithTimeOut(TIMEOUT);

        return processRequest(restTemplate.exchange(url,
                HttpMethod.GET, getHttpEntity(), parameterizedTypeReference, parameter));
    }

    public <T> ResponseEntity<T> executeGetRequest(String url
            ,
                                                   ParameterizedTypeReference<T> parameterizedTypeReference
            , Object... parameters) {
        log.info("Sending GET request to URL: {}", url);
        RestTemplate restTemplate = createRestTemplateWithTimeOut(TIMEOUT);
        ResponseEntity<T> response = processRequest(restTemplate.exchange(url,
                HttpMethod.GET, getHttpEntity(), parameterizedTypeReference, parameters));

        log.info("Received HTTP response with status code: {}", response.getStatusCode());

        return response;
    }

    protected <T> ResponseEntity<EmbeddedDto<T>> findByParameters(String url,
                                                                  ParameterizedTypeReference<EmbeddedDto<T>> parameterizedTypeReference, Object... parameters) {
        RestTemplate restTemplate = createRestTemplateWithTimeOut(TIMEOUT);
        return processRequest(restTemplate.exchange(url, HttpMethod.GET, getHttpEntity(),
                parameterizedTypeReference, parameters));
    }

    private HttpEntity<HttpHeaders> getHttpEntity() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return new HttpEntity<>(httpHeaders);
    }

    private <T> ResponseEntity<T> processRequest(final ResponseEntity<T> response) throws RuntimeException {
        try {
            if (HttpStatus.OK.equals(response.getStatusCode())) {
                return response;
            } else {
                throw new RuntimeException("Suite API error: Http Error Code: {}"
                        + response.getStatusCode().name());
            }
        } catch (HttpServerErrorException e) {
            log.error("Error in REST request: {}", e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private RestTemplate createRestTemplateWithTimeOut(int timeOutMins) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(clientHttpRequestFactory(timeOutMins));
        return restTemplate;
    }

    private ClientHttpRequestFactory clientHttpRequestFactory(int timeOutMins) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(timeOutMins);
        factory.setReadTimeout(timeOutMins);
        return factory;
    }

}
