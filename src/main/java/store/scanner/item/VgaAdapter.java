package store.scanner.item;

import java.math.BigDecimal;

public class VgaAdapter extends Product {

    public static final String SKU = "vga";

    public VgaAdapter() {
        itemSetup("VGA adapter" , new BigDecimal("30.00"));
    }

    @Override
    String getSku() {
        return SKU;
    }
}
