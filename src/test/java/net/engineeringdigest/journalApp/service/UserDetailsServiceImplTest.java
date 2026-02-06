package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;

import static org.mockito.Mockito.when;


class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserEntryRepository userEntryRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldLoadUserByUsername() {

        User mockUser = User.builder()
                .userName("Ram")
                .password("ink123")
                .roles(new ArrayList<>())
                .build();

        when(userEntryRepository.findByuserName(ArgumentMatchers.anyString()))
                .thenReturn(mockUser);

        var result = userDetailsService.loadUserByUsername("Ram");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Ram", result.getUsername());
    }
}
