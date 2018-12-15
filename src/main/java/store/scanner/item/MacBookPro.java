package store.scanner.item;

import java.math.BigDecimal;

public class MacBookPro extends Product {

    public static final String SKU = "mbp";

    public MacBookPro(){
        itemSetup("MacBook Pro", new BigDecimal("1399.99"));
    }

    @Override
    String getSku() {
        return SKU;
    }
}
