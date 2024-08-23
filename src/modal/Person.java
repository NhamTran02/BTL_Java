package modal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private static int nextId = 0;
    private int id;
    private String name;
    private Date dateOfBirth;
    private String address;
    private double height;
    private double weight;

    public Person() {
        this.id = nextId++;
    }

    public Person(int id,String name, Date dateOfBirth, String address, double height, double weight) {
        this.id = nextId++;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.height = height;
        this.weight = weight;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateOfBirthFormatted = dateFormat.format(dateOfBirth);
        return "Person{" +
                "id=" + id +
                ", tên='" + name + '\'' +
                ", ngày sinh=" + dateOfBirthFormatted +
                ", địa chỉ='" + address + '\'' +
                ", chiều cao=" + height +
                ", cân nặng=" + weight +
                '}';
    }
}
