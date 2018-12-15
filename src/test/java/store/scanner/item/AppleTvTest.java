package store.scanner.item;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class AppleTvTest {

    @Test
    public void testGetPrice(){
        AppleTv appleTv = new AppleTv();
        assertThat(appleTv.getPrice(),equalTo(new BigDecimal("109.50")));
        assertThat(appleTv.getSku(),equalTo("atv"));
        assertThat(appleTv.getName(),equalTo("Apple TV"));
    }

}