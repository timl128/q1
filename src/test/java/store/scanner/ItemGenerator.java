package store.scanner;

import store.scanner.item.Product;
import store.scanner.item.ProductFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ItemGenerator {

    public static List<Product> generateItem(Map<String,Integer> map){

        List<Product> products = new LinkedList<>();

        map.forEach(
                (k,v) ->{
                    for(int i = 0 ; i < v ;i++)
                        products.add(ProductFactory.getItem(k));
                }

        );


        return products;
    }
}
