package com.crud.tasks.config;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminConfigTest {

    @Autowired
    AdminConfig adminConfig;

    @Test
    public void setAdminConfigTest() {

        //Given & When
        String a = adminConfig.getAdminMail();
        String b = adminConfig.getAdminName();

        //Then
        Assertions.assertEquals(a,"piotr.testowy123123@gmail.com");
        Assertions.assertEquals(b,"Kodilla User");

    }
}
