package com.vaha.justForFun.service;

import com.vaha.justForFun.domain.Role;
import com.vaha.justForFun.domain.User;
import com.vaha.justForFun.repository.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MailSender mailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void addUserTest() {
        User user = new User();
        user.setEmail("omg@mail.ru");

        boolean isUserCreated = userService.addUser(user);
        assertTrue(isUserCreated);
        assertNotNull(user.getActivationCode());
        assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        verify(userRepository, times(1)).save(user);
        verify(mailSender, times(1)).
                send(
                        eq(user.getEmail()),
                        anyString(),
                        anyString());
    }

    @Test
    public void addUserFailTest() {
        User user = new User();
        user.setUsername("Viktor");

        Mockito.doReturn(new User()).when(userRepository).findByUsername("Viktor");

        boolean isUserCreated = userService.addUser(user);
        assertFalse(isUserCreated);

        verify(userRepository, times(0)).save(any(User.class));
        verify(mailSender, times(0)).
                send(
                        anyString(),
                        anyString(),
                        anyString());
    }

    @Test
    public void activateUserTest() {
        User user = new User();
        user.setActivationCode("omg");

        Mockito.doReturn(new User()).when(userRepository).findByActivationCode("activate");

        boolean isUserActivated = userService.activateUser("activate");

        assertTrue(isUserActivated);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void activateUserFailTest() {
        boolean isUserActivated = userService.activateUser("not activated");

        assertFalse(isUserActivated);
        verify(userRepository, times(0)).save(any(User.class));
    }
}