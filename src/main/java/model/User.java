package model;

public class User {
    private int id;
    private String name;
    private Province province;

    public User() {
    }

    public User(String name, Province province) {
        this.name = name;
        this.province = province;
    }

    public User(int id, String name, Province province) {
        this.id = id;
        this.name = name;
        this.province = province;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", province=" + province +
                '}';
    }
}
