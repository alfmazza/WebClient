package com.sa.webclient.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
  
    private static final long serialVersionUID = 1L;
    
    long id;
    String email;
    String username;
    String password;
}
