package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServicetests {
    @Autowired
    UserEntryRepository userEntryRepository;
    @Disabled
    @Test
    public void testAdd()
    {
        assertEquals(4,2+2);
        assertNotNull(userEntryRepository.findByuserName("Piyush0502"));

    }
    @Disabled
    @Test
    public void testfindByUserName()
    {
        User user=userEntryRepository.findByuserName("ram");
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"

    })
    public void test(int a, int b, int expected)
    {
        assertEquals(expected,a+b);
    }
}
