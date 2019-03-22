package dao;

import Model.Criterion;
import Model.Dish;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuDaoImpl implements MenuDao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Menu");
    private static final MenuDaoImpl instance = new MenuDaoImpl();

    private MenuDaoImpl() {
        defaultData();
    }

    public static MenuDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Dish> getDishesAll() {
        EntityManager em = emf.createEntityManager();
        List<Dish> dishesAll = em.createQuery("FROM Dish", Dish.class).getResultList();
        em.close();
        return dishesAll;
    }

    @Override
    public List<Dish> getDishes(Criterion criterion) {
        List<Dish> dishesCriterion = null;
        EntityManager em = emf.createEntityManager();
        if (criterion.isCriterion()) {
            dishesCriterion = em.createQuery("FROM Dish" + criterion.toRequest("d"), Dish.class).getResultList();
            if (criterion.getWeightTo()) dishesCriterion = weightSelect(dishesCriterion);
        }
        em.close();
        return dishesCriterion;
    }

    private List<Dish> weightSelect(List<Dish> dishesCriterion) {
        dishesCriterion.sort((ob1, ob2) -> ob2.getWeight() - ob1.getWeight());
        List<Dish> result = new ArrayList<>();
        for (Dish dish : dishesCriterion) {
            if (getWeight(result) + dish.getWeight() <= 1000) {
                result.add(dish);
            }
        }
        return result;
    }

    private int getWeight(List<Dish> dishes) {
        int weight = 0;
        for (Dish dish : dishes) {
            weight += dish.getWeight();
        }
        return weight;
    }

    @Override
    public void addDish(Dish dish) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(dish);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    private void defaultData() {
        Random random = new Random();
        for (int i = 1; i <= 5; i++) {
            String name = "Dish" + i;
            int price = (random.nextInt(15) + 1) * 10;
            int weight = (random.nextInt(6) + 1) * 100;
            int discount = random.nextInt(11);
            addDish(new Dish(name, price, weight, discount));
        }
    }
}
