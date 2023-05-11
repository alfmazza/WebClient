package com.sa.user.service;

import com.sa.user.model.User;
import com.sa.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        // Inicialización de los mocks y la instancia de UserService
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setEmail("alfredolmazza@gmail.com");
        user.setPassword("35Lk!dj");
        user.setId(1L);
        user.setUsername("alfmazza");
    }

    @Test
    void findAll() throws Exception {
        // Configuración del comportamiento del UserRepository mock
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        
        // Llamada al método a probar y verificación del resultado
        assertNotNull(userService.findAll());
    }
}