package DAO;

import Models.OrderDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_Orders {

    public static DAO_Orders INSTANCE = new DAO_Orders();
    private Connection conn;
    private String status = "OK";

    public DAO_Orders() {
        conn = DBUtils.getConnection();
        if (INSTANCE == null) {
            try {
                System.out.println(conn);
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

    public int insertNewOrder(int userId, double total) {
        int orderId = (int) System.currentTimeMillis() % Integer.MAX_VALUE;

        String sql = "INSERT INTO [BookShop].[dbo].[Orders] (user_id, status, total_amount) VALUES (?, ?, ?);";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, userId);
            pst.setString(2, "Pending");
            pst.setDouble(3, total);
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting order failed, no rows affected.");
            }

            return (int) orderId;
        } catch (SQLException e) {
            status = "Error at inserting new order: " + e.getMessage();
            return 0;
        }

    }

    public OrderDTO checkOrdersStatus(int userId) {
        DAO_Users u = new DAO_Users();
        String sql = "SELECT [order_id], [user_id], [order_date], [status], [total_amount] "
                + "FROM [BookShop].[dbo].[Orders] WHERE [status] = 1 AND [user_id] = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new OrderDTO(
                        rs.getInt("order_id"),
                        u.getUserById(rs.getInt("user_id")),
                        rs.getString("order_date"),
                        rs.getString("status"),
                        rs.getDouble("total_amount")
                );
            }
        } catch (SQLException e) {
            status = "Error at reading orders: " + e.getMessage();
        }
        return null;
    }

    public OrderDTO getLastOrder() {
        DAO_Users u = new DAO_Users();
        String sql = "SELECT TOP 1 * FROM Orders ORDER BY order_id DESC";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new OrderDTO(
                        rs.getInt("order_id"),
                        u.getUserById(rs.getInt("user_id")),
                        rs.getString("order_date"),
                        rs.getString("status"),
                        rs.getDouble("total_amount")
                );
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void updateOrderTotalAmount(int orderId, double totalAmount) {
        String sql = "UPDATE Orders SET total_amount = ? WHERE order_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1, totalAmount);
            pst.setInt(2, orderId);
            pst.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public int getLastestID() {
        String sql = "SELECT TOP 1 order_id FROM Orders ORDER BY order_id DESC;";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int lastOrderId = rs.getInt("order_id");
                return lastOrderId;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return 0;
    }

    public List<OrderDTO> getAllOrders() {
        DAO_Users u = new DAO_Users();
        List<OrderDTO> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                OrderDTO order = new OrderDTO(
                        rs.getInt("order_id"),
                        u.getUserById(rs.getInt("user_id")),
                        rs.getString("order_date"),
                        rs.getString("status"),
                        rs.getDouble("total_amount")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
        }
        return orders;
    }

    public void deleteOrderById(int orderId) {
        String sql = "DELETE FROM Orders WHERE order_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, orderId);
            pst.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE Orders SET status = ? WHERE order_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, status);
            pst.setInt(2, orderId);
            pst.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public OrderDTO getOrderByID(int orderId) {
        DAO_Users u = new DAO_Users();
        String sql = "SELECT * FROM Orders WHERE order_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new OrderDTO(
                        rs.getInt("order_id"),
                        u.getUserById(rs.getInt("user_id")),
                        rs.getString("order_date"),
                        rs.getString("status"),
                        rs.getDouble("total_amount")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error at getOrderByID: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        DAO_Orders o = new DAO_Orders();
//        System.out.println(o.getOrderByID(33).getUser().getUsername());
        o.insertNewOrder(4, 10000);
//System.out.println(o.conn);
//        System.out.println(o.getLastestID());
    }
}
