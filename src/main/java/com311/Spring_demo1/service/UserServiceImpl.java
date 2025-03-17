package com311.Spring_demo1.service;

import com311.Spring_demo1.dao.UserDao;
import com311.Spring_demo1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void save(User user){
        userDao.save(user);
    }


    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
