package store.scanner.item;

public class ProductFactory {

    /**
     * get item
     * @param sku
     * @return
     */
    public static Product getItem(String sku){

        switch (sku){

            case Ipad.SKU :
                return new Ipad();
            case MacBookPro.SKU:
                return new MacBookPro();
            case AppleTv.SKU:
                return new AppleTv();
            case VgaAdapter.SKU:
                return new VgaAdapter();
             default:
                 return null;


        }
    }
}
