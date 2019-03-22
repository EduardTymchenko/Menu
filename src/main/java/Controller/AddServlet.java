package Controller;

import Model.Dish;
import dao.MenuDao;
import dao.MenuDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add")
public class AddServlet extends HttpServlet {
    private MenuDao dao = MenuDaoImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Integer price = Integer.valueOf(request.getParameter("price"));
        Integer weight = Integer.valueOf(request.getParameter("weight"));
        Integer discount = Integer.valueOf(request.getParameter("discount")) ;
        Dish dish = new Dish(name, price, weight, discount);
        dao.addDish(dish);
        response.sendRedirect("/");
    }
}
