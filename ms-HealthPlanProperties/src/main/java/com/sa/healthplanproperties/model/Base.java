package com.sa.healthplanproperties.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public class Base implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long properId;
}
