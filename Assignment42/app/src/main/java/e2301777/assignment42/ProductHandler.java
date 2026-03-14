package e2301777.assignment42;

import java.util.ArrayList;

public class ProductHandler {
    private static ArrayList<Product> products = new ArrayList<>();

    public static void addProduct(Product product){
        products.add(product);
    }

    public static boolean checkDuplicate(String id){
        for(Product product : products){
            if(product.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static String getAllProducts(){
        StringBuilder allProducts = new StringBuilder();
        for(Product product : products){
            allProducts.append(product.toString()).append("\n");
        }
        return allProducts.toString();
    }

}
