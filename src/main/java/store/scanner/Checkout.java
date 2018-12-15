package store.scanner;

import store.scanner.item.Product;
import store.scanner.rule.Rules;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Checkout {

    private static final String TOTAL_EXPECTED = "Total expected: $%s";

    // rules should not be changed in the process
    private final Rules rules;
    private BigDecimal total;
    private List<Product> productList = new LinkedList<>();

    public Checkout(Rules rules) {
        this.rules = rules;
        total = BigDecimal.ZERO;
    }

    /**
     * scan product
     * @param product
     */
    public void scan(Product product){
        productList.add(product);

    }

    /**
     * get total and print
     * @return
     */
    public void total(){
        total = rules.process(productList);
        System.out.println(String.format(TOTAL_EXPECTED,total.toString()));
    }
}
