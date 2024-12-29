package Controllers;

import DAO.DAO_Products;
import Models.CategoryDTO;
import Models.VegetableDTO;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO_Products productDAO = new DAO_Products();
        
        ArrayList<VegetableDTO> list = (ArrayList<VegetableDTO>) productDAO.getAllVegetables();
        ArrayList<CategoryDTO> listC = (ArrayList<CategoryDTO>) productDAO.getAllCategories();
        
        request.setAttribute("listP", list);
        request.setAttribute("listC", listC);

        request.getRequestDispatcher("./Home.jsp").forward(request, response);
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
