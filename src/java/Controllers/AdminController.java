package Controllers;

import DAO.DAO_Orders;
import DAO.DAO_Products;
import Models.CategoryDTO;
import Models.UserDTO;
import Models.VegetableDTO;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("LoginController");
            return;
        }

        if (!user.isRole()) {
            response.sendRedirect("HomeController");
            return;
        }
        String cid = request.getParameter("cid");

        List<UserDTO> users = DAO.DAO_Users.INSTANCE.loadAllUsers();
        request.setAttribute("users", users);

        DAO_Orders ordersDAO = new DAO_Orders();
        request.setAttribute("listOfOrder", ordersDAO.getAllOrders());

        DAO_Products productDAO = new DAO_Products();
        ArrayList<VegetableDTO> list;

        if (cid != null) {
            list = (ArrayList<VegetableDTO>) productDAO.getAllVegetablesByCate(Integer.parseInt(cid));
        } else {
            list = (ArrayList<VegetableDTO>) productDAO.getAllVegetables();
        }

        ArrayList<CategoryDTO> listC = (ArrayList<CategoryDTO>) productDAO.getAllCategories();
        request.setAttribute("listP", list);
        request.setAttribute("listC", listC);

        String hehe = request.getQueryString();
        
        if (hehe == null) {
            request.getRequestDispatcher("productAdmin.jsp").forward(request, response);
        }

        if (hehe != null) {
            switch (hehe) {
                case "product" -> {
                    request.getRequestDispatcher("productAdmin.jsp").forward(request, response);
                }
                case "order" -> {
                    request.getRequestDispatcher("orderAdmin.jsp").forward(request, response);
                }
                case "user" -> {
                    request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
                }
            }
            request.getRequestDispatcher("productAdmin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
