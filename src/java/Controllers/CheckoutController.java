package Controllers;

import DAO.DAO_Orders;
import DAO.DAO_OrderDetails;
import Models.OrderDetailsDTO;
import Models.UserDTO;
import Models.VegetableDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CheckoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            UserDTO user = (UserDTO) request.getSession().getAttribute("user");

            if (user == null) {
                response.sendRedirect("LoginController");
                return;
            }

            List<VegetableDTO> cartItems = (List<VegetableDTO>) request.getSession().getAttribute("cart");

            if (cartItems == null || cartItems.isEmpty()) {
                throw new ServletException("Cart is empty or not found in session.");
            }

            String total_amoun = request.getParameter("total_amount");

            DAO_Orders orderDAO = DAO_Orders.INSTANCE;
            DAO_OrderDetails orderDetailDAO = DAO_OrderDetails.INSTANCE;

            int isCreated = orderDAO.insertNewOrder(user.getId(), Double.parseDouble(total_amoun));
            if (isCreated == 0) {
                throw new ServletException("Failed to create a new order.");
            }

            int orderId = orderDAO.getLastestID();
            for (VegetableDTO item : cartItems) {
                OrderDetailsDTO orderDetail = new OrderDetailsDTO();
                orderDetail.setOrderId(orderId);
                orderDetail.setVegetableId(item.getVegetableId());
                orderDetail.setQuantity(item.getPack()); 
                orderDetailDAO.insertNewOrderDetail(orderDetail.getOrderId(), orderDetail.getVegetableId(), orderDetail.getQuantity());
            }

            request.getSession().removeAttribute("cart");

            response.sendRedirect("ConfirmationOrder");
        } catch (ServletException | IOException e) {
            throw new ServletException("Error processing checkout: " + e.getMessage());
        }
    }
}
