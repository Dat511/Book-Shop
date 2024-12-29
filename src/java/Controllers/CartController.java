package Controllers;

import Models.UserDTO;
import Models.VegetableDTO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CartController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CartController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        List<VegetableDTO> cartItems = (List<VegetableDTO>) session.getAttribute("cart");

        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cart", cartItems);
        }

        request.setAttribute("cartItems", cartItems);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<VegetableDTO> cartItems = (List<VegetableDTO>) session.getAttribute("cart");

        if (session.getAttribute("user") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cart", cartItems);
        }

        String action = request.getParameter("action");
        if (null != action) switch (action) {
            case "add" -> {
                int vegetableId = Integer.parseInt(request.getParameter("vegetableId"));
                String name = request.getParameter("name");
                String origin = request.getParameter("origin");
                String img = request.getParameter("img");
                double price = Double.parseDouble(request.getParameter("price"));
                int pack = Integer.parseInt(request.getParameter("pack"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                addItemToCart(vegetableId, name, origin, img, price, pack, quantity, cartItems);
                }
            case "remove" -> {
                int vegetableId = Integer.parseInt(request.getParameter("vegetableId"));
                removeItemFromCart(vegetableId, cartItems);
                }
            case "update" -> {
                int vegetableId = Integer.parseInt(request.getParameter("vegetableId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                updateItemQuantity(vegetableId, quantity, cartItems);
                }
        }

        session.setAttribute("cart", cartItems);
        request.setAttribute("cartItems", cartItems);
        doGet(request, response);
    }

    private void addItemToCart(int vegetableId, String name, String origin, String img, double price, int pack, int quantity, List<VegetableDTO> cartItems) {
        boolean found = false;
        for (VegetableDTO item : cartItems) {
            if (item.getVegetableId() == vegetableId) {
                item.setPack(item.getPack() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            VegetableDTO newItem = new VegetableDTO(vegetableId, name, origin, quantity, img, price, "Available", "Sample description");
            cartItems.add(newItem);
        }
    }

    private void removeItemFromCart(int vegetableId, List<VegetableDTO> cartItems) {
        cartItems.removeIf(item -> item.getVegetableId() == vegetableId);
    }

    private void updateItemQuantity(int vegetableId, int quantity, List<VegetableDTO> cartItems) {
        for (VegetableDTO item : cartItems) {
            if (item.getVegetableId() == vegetableId) {
                item.setPack(quantity);
                break;
            }
        }
    }
}
