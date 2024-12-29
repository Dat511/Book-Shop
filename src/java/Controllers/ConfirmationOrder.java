package Controllers;

import DAO.DAO_Orders;
import DAO.DAO_OrderDetails;
import Models.OrderDetailsDTO;
import Models.OrderDTO;
import Models.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ConfirmationOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserDTO user = (UserDTO) request.getSession().getAttribute("user");

            if (user == null) {
                response.sendRedirect("LoginController");
                return;
            }

            DAO_Orders orderDAO = DAO_Orders.INSTANCE;
            OrderDTO order = orderDAO.getLastOrder();

            if (order == null) {
                throw new ServletException("No recent order found for confirmation.");
            }

            DAO_OrderDetails orderDetailDAO = DAO_OrderDetails.INSTANCE;
            List<OrderDetailsDTO> orderDetails = orderDetailDAO.loadOrderDetails(order.getOrderId());

            request.setAttribute("order", order);
            request.setAttribute("orderDetails", orderDetails);

            // Forward to the confirmation page
            request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new ServletException("Error displaying confirmation: " + e.getMessage());
        }
    }
}
