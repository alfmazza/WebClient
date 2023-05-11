package com.sa.user.service;

import com.sa.user.model.User;
import com.sa.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SpyUserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    public void setup() {
        
        //Crea mock para el repository
        userRepository = Mockito.mock(UserRepository.class);
       
        //Instancia spy de service 
        userService = spy(new UserService(userRepository));
        
        //Objeto para utilizar
        user = new User();
        user.setEmail("alfredolmazza@gmail.com");
        user.setPassword("35Lk!dj");
        user.setId(1L);
        user.setUsername("alfmazza");
    }

    @Test
    public void testFindAll() throws Exception {
        
        try {
            List<User> userList = Arrays.asList(user);
            
            //Configuración de comportamiento
            when(userRepository.findAll()).thenReturn(userList);
            
            //Metodo a probar
            List<User> result = userService.findAll();
            
            //Verificación
            assertEquals(userList, result);
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Se produjo una excepción: " + e.getMessage());
        }
    }
}