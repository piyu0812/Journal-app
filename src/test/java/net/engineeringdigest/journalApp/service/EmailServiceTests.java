package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    EmailService emailService;

    @Test
    public void TestSendMail(){
        emailService.sendMail("piyuahuja08@gmail.com",
                "Testing Java mail sender",
                "Hii Piyush Seeing whether it is working or not");
    }
}
