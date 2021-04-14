package com.crud.tasks.domain;


import com.crud.tasks.scheduler.EmailScheduler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailTest {

    @Autowired
    EmailScheduler emailScheduler;


    @Test
    public void mailTest() {

        //Given
        Mail mail = new Mail("A","B","C","D");
        emailScheduler.sendInformationEmail();

        //When
        String a = mail.getMailTo();
        String b = mail.getMessage();
        String c = mail.getSubject();
        String d = mail.getToCc();

        //Then

        Assertions.assertEquals(a,"A");
        Assertions.assertEquals(b,"C");
        Assertions.assertEquals(c,"B");
        Assertions.assertEquals(d,"D");

    }
}
