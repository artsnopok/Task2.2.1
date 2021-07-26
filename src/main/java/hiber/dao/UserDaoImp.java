package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
      return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
   }

   @Override
   public User getUserByModelAndSeries(String model, int series) {
         String HQL = "FROM Car car LEFT JOIN FETCH car.user WHERE " +
                 "car.model = :model AND car.series = :series";
         Car car = sessionFactory.getCurrentSession().createQuery(HQL, Car.class)
                 .setParameter("model", model).setParameter("series", series)
                 .uniqueResult();
         return car.getUser();
   }
}
