package web.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;


import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao = new UserDao();


    public List<User> index() {
        return userDao.index();
    }

    public User show (int id) {
        return userDao.show(id);
    }

    public void save(User user) {
        userDao.save(user);

    }

    public void update(int id, User updateUser) {
        userDao.update(id, updateUser);
    }

    public void delete(int id) {
        userDao.delete(id);
    }
}