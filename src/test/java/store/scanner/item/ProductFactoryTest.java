package store.scanner.item;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class ProductFactoryTest {



    @Test
    public void getIpad() {

        String sku = Ipad.SKU;

        Product product = ProductFactory.getItem(sku);
        assertThat(product, instanceOf(Ipad.class));

    }

    @Test
    public void getMac() {

        String sku = MacBookPro.SKU;

        Product product = ProductFactory.getItem(sku);
        assertThat(product, instanceOf(MacBookPro.class));

    }

    @Test
    public void getAppleTv() {

        String sku = AppleTv.SKU;

        Product product = ProductFactory.getItem(sku);
        assertThat(product, instanceOf(AppleTv.class));

    }

    @Test
    public void getVga() {

        String sku = VgaAdapter.SKU;

        Product product = ProductFactory.getItem(sku);
        assertThat(product, instanceOf(VgaAdapter.class));

    }



}