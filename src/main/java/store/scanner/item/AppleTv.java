package store.scanner.item;

import java.math.BigDecimal;

public class AppleTv extends Product {

    public static final String SKU = "atv";

    public AppleTv() {
        itemSetup("Apple TV", new BigDecimal("109.50"));
    }


    @Override
    String getSku() {
        return SKU;
    }
}
