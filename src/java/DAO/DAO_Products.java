package DAO;

import Models.*;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_Products {

    public static DAO_Products INSTANCE = new DAO_Products();
    public Connection con;
    public String status = "OK";

    public DAO_Products() {
        try {
            con = DBUtils.getConnection();
            System.out.println("Kết nối database: " + (con != null && !con.isClosed()));
        } catch (Exception e) {
            status = "Error at connection: " + e.getMessage();
            System.out.println(status);
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean insertProduct(String name, String origin, int pack, String img, double price, String status, String description) throws SQLException {
        String sql = "INSERT INTO vegetable ([name], origin, pack, img, price, [status], [description]) VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, origin);
            pst.setInt(3, pack);
            pst.setString(4, img);
            pst.setDouble(5, price);
            pst.setString(6, status);
            pst.setString(7, description);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product inserted successfully.");
            } else {
                System.out.println("No rows affected.");
            }

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM vegetable WHERE vegetable_id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            status = "Error to read product" + e.getMessage();
        }
    }

    public boolean updateProduct(int id, String name, String origin, int pack, double price, String status, String description) throws SQLException {
        String sql = "UPDATE vegetable SET name = ?, origin = ?, pack = ?, price = ?, status = ?, description = ? WHERE vegetable_id = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, origin);
            pst.setInt(3, pack);
            pst.setDouble(4, price);
            pst.setString(5, status);
            pst.setString(6, description);
            pst.setInt(7, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("No rows affected. Product might not exist.");
            }

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public List<OrderDTO> loadAllOrders() {
        DAO_Users u = new DAO_Users();
        List<OrderDTO> list = new ArrayList<>();
        String sql = "SELECT order_id, user_id, order_date, status, total_amount FROM Orders";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new OrderDTO(
                        rs.getInt(1),
                        u.getUserById(rs.getInt(2)),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5)));
            }
        } catch (SQLException e) {
            status = "Error ar read Products " + e.getMessage();
        }
        return list;
    }

    public List<VegetableDTO> getAllVegetables() {
        List<VegetableDTO> vegetableList = new ArrayList<>();
        String sql = "SELECT vegetable_id, name, origin, pack, img, price, status, description FROM vegetable";
        try {
            try (PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    VegetableDTO vegetable = new VegetableDTO(
                            rs.getInt("vegetable_id"),
                            rs.getString("name"),
                            rs.getString("origin"),
                            rs.getInt("pack"),
                            rs.getString("img"),
                            rs.getDouble("price"),
                            rs.getString("status"),
                            rs.getString("description")
                    );
                    vegetableList.add(vegetable);
                }
            }
        } catch (SQLException e) {
            status = "Error at getAllVegetables: " + e.getMessage();
            System.out.println(status);
        }

        for (VegetableDTO veg : vegetableList) {
            System.out.println(veg.getName());
        }

        return vegetableList;
    }

    public List<VegetableDTO> getAllVegetablesByCate(int cid) {
        List<VegetableDTO> vegetableList = new ArrayList<>();
        String sql = "SELECT vegetable_id, name, origin, pack, img, price, status, description FROM vegetable";
        sql += " WHERE category_id = ?";
        try {
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, cid);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        VegetableDTO vegetable = new VegetableDTO(
                                rs.getInt("vegetable_id"),
                                rs.getString("name"),
                                rs.getString("origin"),
                                rs.getInt("pack"),
                                rs.getString("img"),
                                rs.getDouble("price"),
                                rs.getString("status"),
                                rs.getString("description")
                        );
                        vegetableList.add(vegetable);
                    }
                }
            }
        } catch (SQLException e) {
            status = "Error at getAllVegetables: " + e.getMessage();
            System.out.println(status);
        }

        return vegetableList;
    }

    public VegetableDTO getVegetableByID(int id) {
        String sql = "SELECT vegetable_id, name, origin, pack, img, price, status, description FROM vegetable";
        sql += " WHERE vegetable_id = ?";
        try {
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, id);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        return new VegetableDTO(
                                rs.getInt("vegetable_id"),
                                rs.getString("name"),
                                rs.getString("origin"),
                                rs.getInt("pack"),
                                rs.getString("img"),
                                rs.getDouble("price"),
                                rs.getString("status"),
                                rs.getString("description")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            status = "Error at getAllVegetables: " + e.getMessage();
            System.out.println(status);
        }
        return null;
    }

    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> list = new ArrayList<>();
        String sql = "SELECT category_id, name FROM Category";
        try {
            try (PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    CategoryDTO cate = new CategoryDTO(
                            rs.getInt("category_id"),
                            rs.getString("name")
                    );
                    list.add(cate);
                }
            }
        } catch (SQLException e) {
            status = "Error at getAllCategories: " + e.getMessage();
            System.out.println(status);
        }

        return list;
    }

    public List<VegetableDTO> loadProductForCustomer() {
        List<VegetableDTO> veget = new ArrayList<>();
        String sql = "SELECT * FROM [PRJ301_BL3W].[dbo].[vegetable] WHERE pack > 0";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                veget.add(new VegetableDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getString(7),
                        rs.getString(8)
                ));
            }
        } catch (SQLException e) {
            status = "Error ar read Products " + e.getMessage();
        }
        return veget;
    }

    public void decreaseVegetableStock(int vegetableId, int quantity) {
        String sql = "UPDATE vegetable SET pack = pack - ? WHERE vegetable_id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, quantity);
            pst.setInt(2, vegetableId);
            pst.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<VegetableDTO> searchVegetablesByName(String name, String cid) {
        List<VegetableDTO> list = new ArrayList<>();
        String sql;

        if (!cid.isBlank()) {
            sql = "SELECT * FROM vegetable WHERE name LIKE ? AND category_id = ?";
        } else {
            sql = "SELECT * FROM vegetable WHERE name LIKE ?";
        }

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + name + "%");
            if (!cid.isBlank()) {
                pst.setInt(2, Integer.parseInt(cid));
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                VegetableDTO vegetable = new VegetableDTO();
                vegetable.setVegetableId(rs.getInt("vegetable_id"));
                vegetable.setName(rs.getString("name"));
                vegetable.setPack(rs.getInt("pack"));
                vegetable.setPrice(rs.getDouble("price"));
                vegetable.setImg(rs.getString("img"));
                list.add(vegetable);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<VegetableDTO> searchVegetablesByPrice(double min, double max) {
        List<VegetableDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM vegetable WHERE price > ? AND price < ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDouble(1, min);
            pst.setDouble(2, max);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                VegetableDTO vegetable = new VegetableDTO();
                vegetable.setVegetableId(rs.getInt("vegetable_id"));
                vegetable.setName(rs.getString("name"));
                vegetable.setPack(rs.getInt("pack"));
                vegetable.setPrice(rs.getDouble("price"));
                vegetable.setImg(rs.getString("img"));
                list.add(vegetable);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public static void main(String[] args) throws SQLException {
        DAO_Products o = new DAO_Products();
        for (VegetableDTO vegetableDTO : o.searchVegetablesByName("orange", "")) {
            System.out.println(vegetableDTO.getName());
        }
    }
}
