package e2301777.assignment42;

import java.util.Locale;

public class Product {
    private String id;
    private String name;
    private double price;
    private int amount;

    public Product(String id, String name, double price, int amount){
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }



    public double getTotal(){
        return price*amount;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id: ").append(id).append("\n");
        stringBuilder.append("Name: ").append(name).append("\n");
        stringBuilder.append("Unit price: ").append(String.format(Locale.getDefault(), "%.2f", price)).append("€\n");
        stringBuilder.append("Amount: ").append(amount).append("\n");
        stringBuilder.append("Total: ").append(String.format(Locale.getDefault(), "%.2f", getTotal())).append("€\n");

        return stringBuilder.toString();
    }
}
