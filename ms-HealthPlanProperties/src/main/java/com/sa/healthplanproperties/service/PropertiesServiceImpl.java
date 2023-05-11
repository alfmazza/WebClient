package com.sa.healthplanproperties.service;

import com.sa.healthplanproperties.interfaces.PropertiesService;
import com.sa.healthplanproperties.model.Properties;
import com.sa.healthplanproperties.repository.PropertiesRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PropertiesServiceImpl extends BaseServiceImpl<Properties, Long> implements PropertiesService {

    @Autowired
    private PropertiesRepository propertiesRepository;

    @Override
    public List<Properties> search(String filter) throws Exception {
        
        try{
            //List<Properties> entities = propertiesRepository.findByOdontologyContainingOrOrthodonticsContaining(filter, filter);
            //List<Properties> entities = propertiesRepository.search(filter);
            List<Properties> entities = propertiesRepository.searchNative(filter);
            
            return entities;
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Properties> search(String filter, Pageable pageable) throws Exception {
        
        try {
            //List<Properties> entities = propertiesRepository.findByOdontologyContainingOrOrthodonticsContaining(filter, filter);
            //List<Properties> entities = propertiesRepository.search(filter);
            Page<Properties> entities = propertiesRepository.searchNative(filter, pageable);
            
            return entities;
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
}

