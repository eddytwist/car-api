package by.edik.car_api.service;

import by.edik.car_api.dao.UserInformationDao;
import by.edik.car_api.model.UserInformation;

import java.util.List;

public class UserInformationService implements Service<UserInformation> {

    private final UserInformationDao userInformationDao = UserInformationDao.getInstance();

    @Override
    public UserInformation create(UserInformation userInformation) {
        return userInformationDao.create(userInformation);
    }

    @Override
    public UserInformation getById(long id) {
        return userInformationDao.getById(id);
    }

    @Override
    public List<UserInformation> getAll() {
        return userInformationDao.getAll();
    }

    @Override
    public void update(UserInformation userInformation) {
        userInformationDao.update(userInformation);
    }

    @Override
    public void delete(long id) {
        userInformationDao.delete(id);
    }
}