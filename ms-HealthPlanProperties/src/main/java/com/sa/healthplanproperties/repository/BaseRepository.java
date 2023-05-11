package com.sa.healthplanproperties.repository;

import com.sa.healthplanproperties.model.Base;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository<E, ID>{
    
}
