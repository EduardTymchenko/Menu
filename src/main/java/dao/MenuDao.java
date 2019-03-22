package dao;

import Model.Criterion;
import Model.Dish;

import java.util.List;

public interface MenuDao {
        List<Dish> getDishesAll();
        List<Dish> getDishes(Criterion criterion);

        void addDish(Dish dish);
}
