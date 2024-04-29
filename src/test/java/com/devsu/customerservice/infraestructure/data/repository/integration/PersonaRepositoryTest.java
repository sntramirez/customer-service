package com.devsu.customerservice.infraestructure.data.repository.integration;

import com.devsu.customerservice.application.api.model.EmbeddedDto;
import com.devsu.customerservice.infraestructure.data.entities.Persona;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;



@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonaRepositoryTest extends TestUtils {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void integrationPersonaTest(){

        String url = "/personas";

        Persona persona = new Persona();
        persona.setNombre("Jose Test");
        persona.setGenero("Masculino");
        persona.setEdad(21);
        persona.setIdentificacion("1720811349");
        persona.setDireccion("Direccion de test");
        persona.setTelefono("0983614351");


        ResponseEntity<Persona> postResponse = post(
                url,
                persona,
                Persona.class);

        validateResponse(postResponse, HttpStatus.CREATED);


        String urlPut = url +"/" + postResponse.getBody().getId();

        persona.setNombre("Jose Put");
        ResponseEntity<Persona> putResponse = put(
                urlPut,
                persona,
                new ParameterizedTypeReference<Persona>() {
                });

        Assert.assertEquals("Jose Put",putResponse.getBody().getNombre());
        validateResponse(putResponse, HttpStatus.OK);

        String urlDelete = url +"/" + postResponse.getBody().getId();

        ResponseEntity<Persona> deleteRequest = delete(
                urlDelete,
                new ParameterizedTypeReference<Persona>() {
                });

        validateResponse(deleteRequest, HttpStatus.NO_CONTENT);

        ResponseEntity<EmbeddedDto<Persona>> getResponse = get(
                url,
                new ParameterizedTypeReference<EmbeddedDto<Persona>>() {
                }, 0, 100);

        validateResponse(getResponse, HttpStatus.OK);


    }

    public static void validateResponse(ResponseEntity<?> response, HttpStatus httpStatus) {
        Assert.assertNotNull(response);
        Assert.assertEquals(httpStatus, response.getStatusCode());
    }


    private <T> ResponseEntity<T> post(String url, Object body, Class<T> clazz) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setReadTimeout(1200000);
        this.restTemplate.getRestTemplate().setRequestFactory(requestFactory);
        return this.restTemplate.exchange(url, HttpMethod.POST, getEntityWithBodyAndTenant(body),
                clazz);
    }


    private <T> ResponseEntity<T> get(String url,
                                      ParameterizedTypeReference<T> parameterizedTypeReference, Object... parameters) {
        return this.restTemplate.exchange(url, HttpMethod.GET, getEntityWithTenant(),
                parameterizedTypeReference,
                parameters);
    }

    private <T> ResponseEntity<T> delete(String url,
                                         ParameterizedTypeReference<T> parameterizedTypeReference, Object... parameters) {
        return this.restTemplate.exchange(url, HttpMethod.DELETE, getEntityWithTenant(),
                parameterizedTypeReference,
                parameters);
    }

    private <T> ResponseEntity<T> put(String url, Object body,
                                      ParameterizedTypeReference<T> parameterizedTypeReference, Object... parameters) {
        return this.restTemplate.exchange(url, HttpMethod.PUT, getEntityWithBodyAndTenant(body),
                parameterizedTypeReference,
                parameters);
    }


}