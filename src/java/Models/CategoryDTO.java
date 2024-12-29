package Models;

public class CategoryDTO {

    private int cid;
    private String cname;

    public CategoryDTO() {
    }

    public CategoryDTO(int id, String name) {
        this.cid = id;
        this.cname = name;
    }

    public int getId() {
        return cid;
    }

    public void setId(int id) {
        this.cid = id;
    }

    public String getName() {
        return cname;
    }

    public void setName(String name) {
        this.cname = name;
    }

}
