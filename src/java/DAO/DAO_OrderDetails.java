package DAO;

import Models.OrderDetailsDTO;
import Utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class DAO_OrderDetails {

    public static DAO_OrderDetails INSTANCE = new DAO_OrderDetails();
    private Connection conn;
    private String status = "OK";

    public DAO_OrderDetails() {
        conn = DBUtils.getConnection();
        if (INSTANCE == null) {
            try {
                System.out.println("Database connection status: " + !conn.isClosed());
            } catch (SQLException e) {
                status = "Error at connection: " + e.getMessage();
            }
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void insertNewOrderDetail(int orderId, int vegetableId, int quantity) {
        String sql = "INSERT INTO OrderDetails (order_id, vegetable_id, quantity) VALUES (?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, orderId);
            pst.setInt(2, vegetableId);
            pst.setInt(3, quantity);
            pst.executeUpdate();
        } catch (SQLException e) {
            status = "Error at insert new order detail: " + e.getMessage();
        }
    }

    public void deleteOrderDetail(int orderId) {
        String sql = "DELETE FROM [PRJ301_BL3W].[dbo].[OrdersDetails] WHERE [orderId] = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, orderId);
            pst.executeUpdate();
        } catch (SQLException e) {
            status = "Error at delete order detail: " + e.getMessage();
        }
    }

    public List<OrderDetailsDTO> loadOrderDetails(int orderId) {
        DAO_Products p = new DAO_Products();
        List<OrderDetailsDTO> orderDetailsList = new ArrayList<>();
        String sql = """
                     SELECT * FROM OrderDetails od
                     LEFT JOIN vegetable v
                     ON od.vegetable_id = v.vegetable_id
                     WHERE order_id = ?""";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                OrderDetailsDTO detail = new OrderDetailsDTO(
                        rs.getInt("order_detail_id"),
                        rs.getInt("order_id"),
                        rs.getInt("vegetable_id"),
                        rs.getInt("quantity"),
                        p.getVegetableByID(rs.getInt("vegetable_id"))
                );
                orderDetailsList.add(detail);
            }
        } catch (SQLException e) {
            status = "Error loading order details: " + e.getMessage();
        }
        return orderDetailsList;
    }

    public void deleteOrderByOrderId(int orderId) {
        String sql = "DELETE FROM OrderDetails WHERE order_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, orderId);
            pst.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
    }
}
