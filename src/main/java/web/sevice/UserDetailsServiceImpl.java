package web.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.UserDaoImpl;
import web.model.User;


import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDaoImpl userDaoImpl = new UserDaoImpl();


    public List<User> index() {
        return userDaoImpl.index();
    }

    public User show (int id) {
        return userDaoImpl.show(id);
    }

    public void save(User user) {
        userDaoImpl.save(user);

    }

    public void update(int id, User updateUser) {
        userDaoImpl.update(id, updateUser);
    }

    public void delete(int id) {
        userDaoImpl.delete(id);
    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDaoImpl.getUserByName(s);
    }
}