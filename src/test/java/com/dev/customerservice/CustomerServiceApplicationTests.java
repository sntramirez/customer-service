package com.dev.customerservice;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerServiceApplicationTests {

    @Test
    void contextLoads() {
        try {
            CustomerServiceApplication.main(new String[]{});
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}