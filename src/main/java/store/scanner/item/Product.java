package store.scanner.item;

import java.math.BigDecimal;

public abstract class Product {

    protected BigDecimal price;
    protected String name;
    protected String currency;

    abstract String getSku();

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * set up product details
     * @param name
     * @param price
     */
    protected void itemSetup(String name , BigDecimal price){
        this.name = name;
        this.price = price;
        this.currency = "AUD";
    }

}
