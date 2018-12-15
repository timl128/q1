package store.scanner.rule;

import org.junit.Before;
import org.junit.Test;
import store.scanner.ItemGenerator;
import store.scanner.item.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class OpeningDaySpecialRulesTest {

    private OpeningDaySpecialRules rule;

    @Before
    public void setUp()  {
        rule = new OpeningDaySpecialRules();
    }

    @Test
    public void testThreeAppleTv() {

        Map<String,Integer> map = new HashMap<>();
        map.put(AppleTv.SKU,3);

        BigDecimal price = new AppleTv().getPrice();

        List<Product> productList = ItemGenerator.generateItem(map);
        BigDecimal result = rule.process(productList);
        assertThat(result,equalTo(price.multiply(new BigDecimal(2))));

    }

    @Test
    public void testFourAppleTv() {

        Map<String,Integer> map = new HashMap<>();
        map.put(AppleTv.SKU,4);

        BigDecimal price = new AppleTv().getPrice();

        List<Product> productList = ItemGenerator.generateItem(map);
        BigDecimal result = rule.process(productList);
        assertThat(result,equalTo(price.multiply(new BigDecimal(3))));

    }

    @Test
    public void testZeroAppleTv() {

        Map<String,Integer> map = new HashMap<>();
        map.put(AppleTv.SKU,0);

        List<Product> productList = ItemGenerator.generateItem(map);
        BigDecimal result = rule.process(productList);
        assertThat(result,equalTo(BigDecimal.ZERO));

    }


    @Test
    public void testFourIpad() {

        Map<String,Integer> map = new HashMap<>();
        map.put(Ipad.SKU,4);

        BigDecimal price = new Ipad().getPrice();

        List<Product> productList = ItemGenerator.generateItem(map);
        BigDecimal result = rule.process(productList);
        assertThat(result,equalTo(price.multiply(new BigDecimal(4))));

    }

    @Test
    public void testFiveIpad() {

        Map<String,Integer> map = new HashMap<>();
        map.put(Ipad.SKU,5);

        List<Product> productList = ItemGenerator.generateItem(map);
        BigDecimal result = rule.process(productList);
        assertThat(result,equalTo(new BigDecimal("499.99").multiply(new BigDecimal("5"))));

    }

    @Test
    public void testZeroIpad() {

        Map<String,Integer> map = new HashMap<>();
        map.put(Ipad.SKU,0);

        List<Product> productList = ItemGenerator.generateItem(map);
        BigDecimal result = rule.process(productList);
        assertThat(result,equalTo(BigDecimal.ZERO));

    }


    @Test
    public void testOneMacOneVga() {

        Map<String,Integer> map = new HashMap<>();
        map.put(MacBookPro.SKU,1);
        map.put(VgaAdapter.SKU,1);

        BigDecimal price = new MacBookPro().getPrice();

        List<Product> productList = ItemGenerator.generateItem(map);
        BigDecimal result = rule.process(productList);
        assertThat(result,equalTo(price));

    }

    @Test
    public void testOneMac() {

        Map<String,Integer> map = new HashMap<>();
        map.put(MacBookPro.SKU,1);

        List<Product> productList = ItemGenerator.generateItem(map);
        BigDecimal price = new MacBookPro().getPrice();
        BigDecimal result = rule.process(productList);
        assertThat(result,equalTo(price));

    }

    @Test
    public void testTZeroMac() {

        Map<String,Integer> map = new HashMap<>();
        map.put(MacBookPro.SKU,0);

        List<Product> productList = ItemGenerator.generateItem(map);
        BigDecimal result = rule.process(productList);
        assertThat(result,equalTo(BigDecimal.ZERO));

    }

    @Test
    public void testScenario1() {

        Map<String,Integer> map = new HashMap<>();
        map.put(AppleTv.SKU,3);
        map.put(VgaAdapter.SKU,1);

        List<Product> productList = ItemGenerator.generateItem(map);
        BigDecimal result = rule.process(productList);
        assertThat(result,equalTo(new BigDecimal("249.00")));

    }

    @Test
    public void testScenario2() {

        Map<String,Integer> map = new HashMap<>();
        map.put(AppleTv.SKU,2);
        map.put(Ipad.SKU,5);

        List<Product> productList = ItemGenerator.generateItem(map);
        BigDecimal result = rule.process(productList);
        assertThat(result,equalTo(new BigDecimal("2718.95")));

    }

    @Test
    public void testScenario3() {

        Map<String,Integer> map = new HashMap<>();
        map.put(MacBookPro.SKU,1);
        map.put(VgaAdapter.SKU,1);
        map.put(Ipad.SKU,1);

        List<Product> productList = ItemGenerator.generateItem(map);
        BigDecimal result = rule.process(productList);
        assertThat(result,equalTo(new BigDecimal("1949.98")));

    }
}