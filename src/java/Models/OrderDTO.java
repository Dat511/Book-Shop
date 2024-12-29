package Models;

public class OrderDTO {

    private int orderId;
    private UserDTO user;
    private String orderDate;
    private String status;
    private double total;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, UserDTO user, String orderDate, String status, double totalAmount) {
        this.orderId = orderId;
        this.user = user;
        this.orderDate = orderDate;
        this.status = status;
        this.total = totalAmount;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double totalAmount) {
        this.total = totalAmount;
    }
    
}
