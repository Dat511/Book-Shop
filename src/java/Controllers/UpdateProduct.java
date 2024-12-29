package Controllers;

import DAO.DAO_Products;
import Models.UserDTO;
import Models.VegetableDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UpdateProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DAO_Products dao = new DAO_Products();
        VegetableDTO vege = dao.getVegetableByID(id);
        request.setAttribute("product", vege);
        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String origin = request.getParameter("origin");
            int pack = Integer.parseInt(request.getParameter("pack"));
            double price = Double.parseDouble(request.getParameter("price"));
            String status = request.getParameter("status");
            String description = request.getParameter("description");

            boolean isUpdated = DAO_Products.INSTANCE.updateProduct(id, name, origin, pack, price, status, description);

            if (isUpdated) {
                request.getSession().setAttribute("successMessage", "Product updated successfully!");
            } else {
                request.getSession().setAttribute("errorMessage", "Failed to update the product.");
            }

            response.sendRedirect("AdminController");

        } catch (NumberFormatException | IOException | SQLException e) {
            response.sendRedirect("error.jsp");
        }
    }

}
