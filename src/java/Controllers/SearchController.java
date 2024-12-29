package Controllers;

import DAO.DAO_Products;
import Models.CategoryDTO;
import Models.VegetableDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SearchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO_Products productDAO = new DAO_Products();
        
        String searchQuery = request.getParameter("search");
        String searchCateID = request.getParameter("cid");

        ArrayList<CategoryDTO> listC = (ArrayList<CategoryDTO>) productDAO.getAllCategories();
        
//        PrintWriter out = response.getWriter();
        
        request.setAttribute("listC", listC);
        List<VegetableDTO> list = (ArrayList<VegetableDTO>) productDAO.searchVegetablesByName(searchQuery, searchCateID);

//        out.print(list);
//        out.print(searchQuery);
//        out.print(searchCateID);
//        for (VegetableDTO vegetableDTO : list) {
//            out.println(vegetableDTO.getName());
//        }
        request.setAttribute("listP", list);

        request.getRequestDispatcher("./Home.jsp").forward(request, response);
    }

}
