package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private static int count = 0;

//    @Autowired
//    private SessionFactory sessionFactory;
    private List<User> users;
    {
        users = new ArrayList<>();
        users.add(new User(++count, "Tom"));
        users.add(new User(++count, "Julia"));
        users.add(new User(++count, "Mike"));
    }

    public List<User> index() {
        return users;
    }

    public User show (int id) {
        return users.stream().filter(user -> user.getId() == id)
                .findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++count);
        users.add(user);
    }

    public void update(int id, User updateUser) {
        User user = show(id);
        user.setName(updateUser.getName());
    }

    public void delete(int id) {
        users.removeIf(p -> p.getId() == id);
    }
}
