package co.edu.udea.certificacion.advshop.modulocompra.models;

public class Product {
    private final String name;
    private final String category;
    private final int quantity;

    public Product(String name, String category, int quantity) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
}
