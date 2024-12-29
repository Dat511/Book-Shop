package Models;

public class VegetableDTO {

    private int id;
    private String name;
    private String origin;
    private int pack;
    private String img;
    private double price;
    private String status;
    private String description;

    public VegetableDTO() {
    }

    public VegetableDTO(int vegetableId, String name, String origin, int pack, String img, double price, String status, String description) {
        this.id = vegetableId;
        this.name = name;
        this.origin = origin;
        this.pack = pack;
        this.img = img;
        this.price = price;
        this.status = status;
        this.description = description;
    }

    public int getVegetableId() {
        return id;
    }

    public void setVegetableId(int vegetableId) {
        this.id = vegetableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPack() {
        return pack;
    }

    public void setPack(int pack) {
        this.pack = pack;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "VegetableDTO{" + "vegetableId=" + id + ", name=" + name + ", origin=" + origin + ", pack=" + pack + ", img=" + img + ", price=" + price + ", status=" + status + ", description=" + description + '}';
    }

}
