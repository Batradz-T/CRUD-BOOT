package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.Query;
import javax.transaction.Transaction;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDao {


    @Autowired
    private SessionFactory sessionFactory;

    public List<User> index() {
        Session session = sessionFactory.getCurrentSession();
        //List<User> result =
        return session.createQuery("from User").getResultList();
    }

    public User show (int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User where id=:userId");
        query.setParameter("userId", id);
        return (User)query.getResultList().get(0);
        //return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    public void update(int id, User updateUser) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE User set name = :name WHERE id = :userId");
        query.setParameter("name", updateUser.getName()).setParameter("userId", id);
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM User WHERE id = :userId");
        query.setParameter("userId", id);
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);
        //users.removeIf(p -> p.getId() == id);
    }
}
