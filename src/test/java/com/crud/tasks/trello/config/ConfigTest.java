package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigTest {

    @Autowired
    TrelloConfig trelloConfig;

    @Test
    public void configTest() {

        //Given
        String a;
        String b;
        String c;
        String d;


        //When
        a = trelloConfig.getTrelloUser();
        b = trelloConfig.getTrelloApiEndpoint();
        c = trelloConfig.getTrelloAppKey();
        d = trelloConfig.getTrelloToken();

        //Then
        Assertions.assertEquals(a,"piotrdziedzic8");
        Assertions.assertEquals(b,"https://api.trello.com/1");
        Assertions.assertEquals(c,"4b2dc22a3b7b42f62a2ee0d95a7b408f");
        Assertions.assertEquals(d,"886c4b0574767f593d3f34aabeab1c18f008cccee402e152e8a72f5cd9c5d186");


    }
}
