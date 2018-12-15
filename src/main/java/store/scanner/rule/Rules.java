package store.scanner.rule;

import store.scanner.item.Product;

import java.math.BigDecimal;
import java.util.List;

public interface Rules {

    BigDecimal process(List<Product> productList);
}
