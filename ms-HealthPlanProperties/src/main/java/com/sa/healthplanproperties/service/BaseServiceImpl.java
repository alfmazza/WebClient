package com.sa.healthplanproperties.service;

import com.sa.healthplanproperties.interfaces.BaseService;
import com.sa.healthplanproperties.model.Base;
import com.sa.healthplanproperties.repository.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;



public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
    
    @Autowired
    protected BaseRepository<E, ID> baseRepository;
    
    
    @Override
    public List<E> findAll() throws Exception {
        
        try {
            List <E> entities = baseRepository.findAll();
            if(entities.isEmpty()) {
                throw new NotFoundException();
            }

            return entities;
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    public Page<E> findAll(Pageable pageable) throws Exception {
        
        try {
            Page<E> entities = baseRepository.findAll(pageable);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    public E findById(ID id) throws Exception{
    
       try {
           Optional<E> entityOptional = baseRepository.findById(id);
           
           if(entityOptional.isPresent())
               return entityOptional.get();
           else 
               throw new Exception("No se encontró el registro");
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    
    @Override
    @Transactional
    public E save(E entity) throws Exception {
    
        try {
            entity = baseRepository.save(entity);
            
            return entity;
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
    
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if(entityOptional.isPresent()) {
                E existingEntity = entityOptional.get();
                BeanUtils.copyProperties(entity, existingEntity, "properId");
                return baseRepository.save(existingEntity);
                
            } else {
                throw new Exception("No se encontró el registro");
            }
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        
        try {
            
            if(baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No se encontró el registro");
            }
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
