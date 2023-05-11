package com.sa.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    
    @NotBlank(message = "E-mail cannot be blank")
    @Column(name = "email")
    String email;
    
    @NotBlank(message = "Username cannot be blank")
    @Column(name = "username")
    String username;
    
    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password")
    String password;
}
