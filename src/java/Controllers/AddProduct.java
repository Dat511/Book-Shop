package Controllers;

import DAO.DAO_Products;
import Models.UserDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.sql.SQLException;
import java.nio.file.Paths;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 15
)
public class AddProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("LoginController");
            return;
        }
        if (!user.isRole()) {
            response.sendRedirect("HomeController");
            return;
        }
        
        try {
            String name = request.getParameter("name");
            String origin = request.getParameter("origin");
            int pack = Integer.parseInt(request.getParameter("pack"));
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");

            Part filePart = request.getPart("productImage");
            String fileName = "";
            
            if (filePart != null && filePart.getSize() > 0) {
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                // Make filename unique using timestamp
                fileName = System.currentTimeMillis() + "_" + fileName;
                
                String uploadPath = "D:\\PRJ\\Sources\\project\\Book\\web\\img";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                
                filePart.write(uploadPath + File.separator + fileName);
            }
            
            // Save the product with the image filename
            boolean isAdded = DAO_Products.INSTANCE.insertProduct(
                name, origin, pack, 
                "" + fileName, // Store the relative path
                price, "in stock", description
            );
            
            if (isAdded) {
                request.getSession().setAttribute("successMessage", "Product updated successfully!");
            }
            response.sendRedirect("AdminController?product");
            
        } catch (NumberFormatException | IOException | SQLException e) {
            e.printStackTrace(); // Add this for debugging
            response.sendRedirect("error.jsp");
        }
    }
}
