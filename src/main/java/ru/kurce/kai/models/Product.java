package ru.kurce.kai.models;

import java.util.Objects;

public class Product {
    private long id;
    private String product;
    private String price;

    public Product (long id, String product, String price){
        this.id = id;
        this.product = product;
        this.price = price;
    }

    public Product (String product, String price){
        this.product = product;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product1 = (Product) o;
        return id == product1.id &&
                Objects.equals(product, product1.product) &&
                Objects.equals(price, product1.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, product, price);
    }
}
