package store.scanner;

import store.scanner.item.Product;
import store.scanner.item.ProductFactory;
import store.scanner.rule.OpeningDaySpecialRules;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class StoreScanner {

    private static final String INVALID_ITEM = "Invalid sku : %s";

    /**
     * main method
     * @param arg
     */
    public static void main(String arg[]){

        String message = "SKUs Scanned: ";
        System.out.println(message);

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        List<Product> productList = convertToItem(s);

        Checkout co = new Checkout(new OpeningDaySpecialRules());

        productList.forEach(
                i -> co.scan(i)
        );

        co.total();

    }

    /**
     * convert to items
     * @param input
     * @return
     */
    private static List<Product> convertToItem(String input){

        List<Product> productList = new LinkedList<>();

        String[] array = input.split(",");
        int length = array.length;

        for(int i = 0; i < length ; i++){
            String sku = array[i].trim();
            Product product = ProductFactory.getItem(sku);

            if(product == null)
                System.out.println(String.format(INVALID_ITEM,sku));
            else
                productList.add(product);
        }

        return productList;
    }

}
