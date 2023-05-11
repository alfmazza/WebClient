package com.sa.healthplanproperties.interfaces;

import com.sa.healthplanproperties.model.Properties;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PropertiesService extends BaseService<Properties, Long> {
   
    List<Properties> search(String filter) throws Exception;
    Page<Properties> search(String filter, Pageable pageable) throws Exception;
}
