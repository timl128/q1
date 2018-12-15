package store.scanner.rule;

import store.scanner.item.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OpeningDaySpecialRules implements Rules{


    private static final long APPLE_TV_DEAL_X = 3;
    private static final long APPLE_TV_DEAL_Y = 2;
    private static final long SUPER_IPAD_DEAL  = 4;
    private static final BigDecimal SUPER_IPAD_PRICE  = new BigDecimal("499.99");

    /**
     * can speed up by multi threads with concurrent map
     * @param productList
     * @return
     */
    @Override
    public BigDecimal process(List<Product> productList) {

        BigDecimal total = BigDecimal.ZERO;
        Map<Class,Long> countMap = countProduct(productList);

        total = total.add(applyAppleTvBuy3For2(countMap));
        total = total.add(applySuperIPadDeal(countMap));
        total = total.add(applyVgaAdapter(countMap));
        total = total.add(remainingItems(countMap));

        return total;
    }

    /**
     * apple tv deal
     * @param countMap
     * @return
     */
    private BigDecimal applyAppleTvBuy3For2(Map<Class,Long> countMap){

        Long count = countMap.get(AppleTv.class);
        if(count == null || count < APPLE_TV_DEAL_X)
            return BigDecimal.ZERO;

        Long groups = count / APPLE_TV_DEAL_X;
        Long remaining = count % APPLE_TV_DEAL_X;
        Long toBePaidTv = groups * APPLE_TV_DEAL_Y + remaining;

        countMap.remove(AppleTv.class);

        return new AppleTv().getPrice().multiply(new BigDecimal(toBePaidTv));
    }

    /**
     * apply super ipad deal
     * @param countMap
     * @return
     */
    private BigDecimal applySuperIPadDeal(Map<Class,Long> countMap){

        Long count = countMap.get(Ipad.class);
        if(count == null)
            return BigDecimal.ZERO;

        BigDecimal price = new Ipad().getPrice();

        if(count > SUPER_IPAD_DEAL)
            price = SUPER_IPAD_PRICE;

        countMap.remove(Ipad.class);

        return price.multiply(new BigDecimal(count));
    }


    /**
     * Vga adapter deal
     * @param countMap
     * @return
     */
    private BigDecimal applyVgaAdapter(Map<Class,Long> countMap){

        Long count = countMap.get(MacBookPro.class);
        if(count == null)
            return BigDecimal.ZERO;

        Long countAdapter = countMap.get(VgaAdapter.class);

        if(countAdapter != null){
            if(count >= countAdapter)
                countAdapter = 0L;
            else
                countAdapter -= count;

            countMap.put(VgaAdapter.class,countAdapter);
        }

        countMap.remove(MacBookPro.class);
        BigDecimal price = new MacBookPro().getPrice();
        return price.multiply(new BigDecimal(count));

    }


    /**
     * calculate remaining  item
     * @param countMap
     * @return
     */
    private BigDecimal remainingItems(Map<Class,Long> countMap)  {

        BigDecimal amount = BigDecimal.ZERO;
        for( Map.Entry<Class,Long> record :countMap.entrySet()){

            try{
                Class<?> myClassType = record.getKey();
                Class<?>[] types = new Class[] {  };
                Constructor<?> cons = myClassType.getConstructor(types);
                Product product = (Product) cons.newInstance();
                amount = product.getPrice().multiply(new BigDecimal(record.getValue()));
            }
            catch (NoSuchMethodException | IllegalAccessException |InvocationTargetException |InstantiationException e){
                throw new RuntimeException();
            }

        }
        countMap.clear();
        return  amount;
    }


    /**
     * count product by type
     * @param productList
     * @return
     */
    private Map<Class,Long> countProduct(List<Product> productList){

        Map<Class,Long> productCount =
                productList.stream().collect(Collectors.groupingBy(Product::getClass,
                        Collectors.counting()));
        return productCount;
    }
}
