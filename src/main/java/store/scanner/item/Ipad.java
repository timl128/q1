package store.scanner.item;

import java.math.BigDecimal;

public class Ipad extends Product {

    public static final String SKU = "ipd";

    public Ipad() {
        itemSetup(" Super iPad", new BigDecimal("549.99"));
    }

    @Override
    String getSku() {
        return SKU;
    }


}
