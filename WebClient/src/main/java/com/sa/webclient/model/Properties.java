package com.sa.webclient.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Properties implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    Long properId;
    double copay;
    private int medicalGuide;
    private int internation;
    private int doctorToHome;
    private String odontology;
    private String orthodontics;
    private double refund;
}
