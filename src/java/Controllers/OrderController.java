package Controllers;

import DAO.DAO_OrderDetails;
import DAO.DAO_Orders;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DAO_OrderDetails od = new DAO_OrderDetails();
        DAO_Orders o = new DAO_Orders();
        
        request.setAttribute("detail", o.getOrderByID(id));
        request.setAttribute("cartItems", od.loadOrderDetails(id));

        request.getRequestDispatcher("viewOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
