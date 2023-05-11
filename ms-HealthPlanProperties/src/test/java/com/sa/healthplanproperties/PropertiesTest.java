package com.sa.healthplanproperties;

import com.sa.healthplanproperties.HealthplanPropertiesApplication;
import com.sa.healthplanproperties.model.Properties;
import com.sa.healthplanproperties.repository.PropertiesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = HealthplanPropertiesApplication.class)
@ActiveProfiles("test")
@Transactional
public class PropertiesTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PropertiesRepository propertiesRepository;

    private Properties properties;

    @BeforeEach
    public void setUp() {

        properties = new Properties();
        properties.setCopay(300.5);
        properties.setInternation(1);
        properties.setDoctorToHome(0);
        properties.setOdontology("NO INCLUYE");
        properties.setOrthodontics("NO INCLUYE");
        properties.setMedicalGuide(1240);
        properties.setRefund(150.3);
    }

    @Test
    void testPropertiesCreation() {

        assertNotNull(properties);
        assertEquals(300.5, properties.getCopay());
        assertEquals(1, properties.getInternation());
        assertEquals(0, properties.getDoctorToHome());
        assertEquals("NO INCLUYE", properties.getOdontology());
        assertEquals("NO INCLUYE", properties.getOrthodontics());
        assertEquals(1240, properties.getMedicalGuide());
        assertEquals(150.3, properties.getRefund());

        propertiesRepository.save(properties);

        Long id = properties.getProperId();
        assertNotNull(id);

        Properties retrievedProperties = entityManager.find(Properties.class, id);
        assertNotNull(retrievedProperties);
    }

}
