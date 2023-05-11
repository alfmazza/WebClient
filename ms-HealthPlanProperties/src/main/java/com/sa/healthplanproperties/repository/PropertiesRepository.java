package com.sa.healthplanproperties.repository;

import com.sa.healthplanproperties.model.Properties;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository extends BaseRepository<Properties, Long> {
    
    
    //filter
    List<Properties> findByOdontologyContainingOrOrthodonticsContaining(String odontology, String Orthodontics);
    
    //filter query JPQL, you write queries against entity classes and their properties, not against database tables and columns. 
    @Query(value = "SELECT a FROM Properties a WHERE a.odontology LIKE %:filter% OR a.orthodontics LIKE %:filter%")
    List<Properties> search (@Param("filter") String filter);
    
    //native query
    @Query(
            value = "SELECT * FROM healthplan_properties WHERE odontology LIKE %:filter% OR orthodontics LIKE %:filter%",
            nativeQuery = true
    )
    List<Properties> searchNative (@Param("filter") String filter);
    
    //paginado
    
    //filter
    Page<Properties> findByOdontologyContainingOrOrthodonticsContaining(String odontology, String Orthodontics, Pageable pageable);
    
    //query JPQL
    @Query(value = "SELECT a FROM Properties a WHERE a.odontology LIKE %:filter% OR a.orthodontics LIKE %:filter%")
    Page<Properties> search (@Param("filter") String filter, Pageable pageable);
    
    //Native Query
    @Query(
            value = "SELECT * FROM healthplan_properties WHERE odontology LIKE %:filter% OR orthodontics LIKE %:filter%",
            nativeQuery = true
    )
    Page<Properties> searchNative (@Param("filter") String filter, Pageable pageable);
}
