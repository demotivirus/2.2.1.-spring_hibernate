package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String name, String series) {
      long id = getIdByCar(name, series);
      return sessionFactory.getCurrentSession()
              .createQuery("from User where id = '" + id + "'", User.class).getSingleResult();
   }

   private Long getIdByCar(String name, String series){
      return (Long) sessionFactory.getCurrentSession().createQuery("select id from Car where " +
              "name = '" + name + "'and series = '" + series + "'").getSingleResult();
   }
}
