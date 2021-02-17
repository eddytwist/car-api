package by.edik.car_api.service;

import by.edik.car_api.dao.UserDao;
import by.edik.car_api.model.User;

import java.util.List;

public class UserService implements Service<User>{

    private final UserDao userDao = UserDao.getInstance();

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }
}
