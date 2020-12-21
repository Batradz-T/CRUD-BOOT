package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
//@Transactional
public class UserDaoImpl implements UserDao {

    private final Map<String, User> userMap;

        public UserDaoImpl() {
            userMap = new HashMap<>();
            userMap.put("admin",new User(1, "admin", "admin", Collections.singleton(new Role(1, "ROLE_ADMIN"))));
            userMap.put("user",new User(2, "user", "user", Collections.singleton(new Role(2, "ROLE_USER"))));
    }


    @Autowired
    private EntityManager entityManager;

    public List<User> index() {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("from User").getResultList();
        entityManager.getTransaction().commit();
        return users;
    }

    public User show (int id) {
        Query query = entityManager.createQuery("FROM User where id=:userId");
        query.setParameter("userId", id);
        return (User)query.getResultList().get(0);

    }

    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public void update(int id, User updateUser) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE User set name = :name WHERE id = :userId");
        query.setParameter("name", updateUser.getName()).setParameter("userId", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
     }

    public void delete(int id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM User WHERE id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public User getUserByName(String name) {
        if (!userMap.containsKey(name)) {
            return null;
        }
        return userMap.get(name);
    }

//        @Autowired
//    private SessionFactory sessionFactory;
//
//    public List<User> index() {
//        Session session = sessionFactory.getCurrentSession();
//        //List<User> result =
//        return session.createQuery("from User").getResultList();
//    }
//
//    public User show (int id) {
//        Query query = sessionFactory.getCurrentSession().createQuery("FROM User where id=:userId");
//        query.setParameter("userId", id);
//        return (User)query.getResultList().get(0);
//        //return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
//    }
//
//    public void save(User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(user);
//    }
//
//    public void update(int id, User updateUser) {
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("UPDATE User set name = :name WHERE id = :userId");
//        query.setParameter("name", updateUser.getName()).setParameter("userId", id);
//        int result = query.executeUpdate();
//        System.out.println("Rows affected: " + result);
//    }
//
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("DELETE FROM User WHERE id = :userId");
//        query.setParameter("userId", id);
//        int result = query.executeUpdate();
//        System.out.println("Rows affected: " + result);
//        //users.removeIf(p -> p.getId() == id);
//    }


}
