package com.sa.user.service;

import com.sa.user.model.User;
import com.sa.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    
   // @Autowired
    private UserRepository userRepository;
    
    //sacar este metodo despues de la prueba de mock
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() throws Exception {
        
        try {
            List<User> user = userRepository.findAll();
            if(user.isEmpty()) {
                throw new NotFoundException();
            }
            return user;
            
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public User findById(Long id) throws Exception {
        
        try {
            Optional<User> user = userRepository.findById(id);
            
            if(user.isPresent())
                return user.get();
            else
                throw new Exception("No se encontró el registro");
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public User save(User user) throws Exception {
        
        try {
            user = userRepository.save(user);
            return user;
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public User update(Long id, User user) throws Exception {
        
    
        try {
            Optional<User> entityOptional = userRepository.findById(id);
            if(entityOptional.isPresent()) {
                User existingEntity = entityOptional.get();
                BeanUtils.copyProperties(user, existingEntity, "id");
                return userRepository.save(existingEntity);
            } else {
                throw new Exception("No se encontró el registro");
            }
            
        } catch(Exception e){//catch (BeansException | NotFoundException e) {
            throw new Exception(e.getMessage());
        }
    
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        
        try {
            
            if(userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No se encontró el registro");
            }
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    
}
