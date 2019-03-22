package Controller;

import Model.Criterion;
import dao.MenuDao;
import dao.MenuDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/")
public class MenuServlet extends HttpServlet {
    private MenuDao dao = MenuDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("dishes", dao.getDishesAll());
        req.getRequestDispatcher("menu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer priceFrom;
        Integer priceTo;
        Boolean weightTo;
        Boolean discount;
        req.setAttribute("dishes", dao.getDishesAll());
        priceFrom = (req.getParameter("priceFrom").equals("") ? null : Integer.valueOf(req.getParameter("priceFrom")));
        priceTo = (req.getParameter("priceTo").equals("") ? null : Integer.valueOf(req.getParameter("priceTo")));
        discount = (req.getParameter("disc_only") != null);
        weightTo = (req.getParameter("weight_up") != null);
        req.setAttribute("dishes_select", dao.getDishes(new Criterion(priceFrom,priceTo,discount,weightTo)));
        req.getRequestDispatcher("menu.jsp").forward(req, resp);
    }
}
