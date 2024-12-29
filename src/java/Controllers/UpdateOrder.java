package Controllers;

import DAO.DAO_OrderDetails;
import DAO.DAO_Orders;
import DAO.DAO_Products;
import Models.OrderDetailsDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class UpdateOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));

        DAO_Orders orderDAO = DAO_Orders.INSTANCE;
        DAO_OrderDetails orderDetailsDAO = DAO_OrderDetails.INSTANCE;
        DAO_Products productDAO = DAO_Products.INSTANCE;

        switch (action) {
            case "delete" -> {
                orderDetailsDAO.deleteOrderByOrderId(id);
                orderDAO.deleteOrderById(id);
                request.getSession().setAttribute("successMessage", "Order deleted successfully.");
            }
            case "accept" -> {
                orderDAO.updateOrderStatus(id, "Accepted");

                List<OrderDetailsDTO> orderDetails = orderDetailsDAO.loadOrderDetails(id);

                for (OrderDetailsDTO detail : orderDetails) {
                    productDAO.decreaseVegetableStock(detail.getVegetableId(), detail.getQuantity());
                }

                request.getSession().setAttribute("successMessage", "Order accepted and stock updated successfully.");
            }
        }
        
        response.sendRedirect("AdminController?order");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
