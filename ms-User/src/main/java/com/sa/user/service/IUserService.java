package com.sa.user.service;

import com.sa.user.model.User;
import java.util.List;


public interface IUserService {
    
    public List<User> findAll() throws Exception;
    public User findById(Long id) throws Exception;
    public User save(User user) throws Exception;
    public User update(Long id, User user) throws Exception;
    public boolean delete(Long id) throws Exception;
}
