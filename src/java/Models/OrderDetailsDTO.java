package Models;

public class OrderDetailsDTO {

    private int orderDetailId;
    private int orderId;
    private int vegetableId;
    private VegetableDTO vegetable;
    private int quantity;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int orderDetailId, int orderId, int vegetableId, int quantity, VegetableDTO vegetable) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.vegetableId = vegetableId;
        this.quantity = quantity;
        this.vegetable = vegetable;
    }

    public VegetableDTO getVegetable() {
        return vegetable;
    }

    public void setVegetable(VegetableDTO vegetable) {
        this.vegetable = vegetable;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getVegetableId() {
        return vegetableId;
    }

    public void setVegetableId(int vegetableId) {
        this.vegetableId = vegetableId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public VegetableDTO getVegetableName() {
        return vegetable;
    }

}
