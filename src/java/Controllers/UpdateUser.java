package Controllers;


import DAO.DAO_Users;
import Models.UserDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        try {
            int ID = Integer.parseInt(id);
            UserDTO user = DAO_Users.INSTANCE.getUserById(ID);
            request.setAttribute("user", user);
            request.getRequestDispatcher("updateUser.jsp").forward(request, response);
        } catch (IOException | NumberFormatException e) {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("pass");
        String role = request.getParameter("role");

        try {
            int ID = Integer.parseInt(id);

            Boolean userRole = role != null && role.equalsIgnoreCase("ADMIN");

            boolean isUpdated = DAO_Users.INSTANCE.updateUser(ID, name, fullname, email, phone, password, userRole);

            if (isUpdated) {
                request.getSession().setAttribute("successMessage", "User updated successfully!");
            } else {
                request.getSession().setAttribute("errorMessage", "Failed to update the user.");
            }
            
            request.getRequestDispatcher("AdminController?user").forward(request, response);

        } catch (NumberFormatException | IOException | ServletException e) {
            response.sendRedirect("error.jsp");
        }

    }

}
