package Model;

import javafx.scene.control.Button;

import java.util.Objects;

/*
*@author Khaled Saidi
*/
public class ProduitStock {
    //Attributes

    private int id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private int quantity;
    Button update;
    //Constructors

    public ProduitStock() {
    }

    public ProduitStock(String name, Double price, String description, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
    }

    public ProduitStock(int id, String name, Double price, String description, String category, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
    }

    public ProduitStock(int id, String name, Double price, String description, String category, int quantity, Button update) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.update = update;
    }
    //Getters & Setters


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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //Overriding

    @Override
    public String toString() {
        return "ProduitStock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price= " + price +"DT"+
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProduitStock other = (ProduitStock) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        return true;
    }
}
