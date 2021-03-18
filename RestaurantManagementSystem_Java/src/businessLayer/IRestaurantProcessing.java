package businessLayer;

import java.util.ArrayList;

public interface IRestaurantProcessing {
    //Administrator operations

    /**
     * @param baseProduct
     * @pre baseProduct != null || compositeProduct != null
     * @post list.size == @pre list.size +1
     *
     */
    public void createNewMenuItem(BaseProduct baseProduct, CompositeProduct compositeProduct);

    /**
     * @param itemName
     * @pre itemName != null
     * @post list.size == @pre list.size -1
     */
    public void deteleMenuItem(String itemName);

    /**
     *
     * @param itemName
     * @param itemPrice
     * @pre itemName != null
     * @pre itemPrice != @post itemPrice
     */
    public void editMenuItem(String itemName, float itemPrice);

    //Waiter operations

    /**
     *
     * @param order
     * @param menuItems
     * @pre order != null
     * @post menuItems.size()!=0
     */
    public void createNewOrder(Order order, ArrayList<MenuItem> menuItems);

    /**
     *
     * @param idOrder
     * @return
     * @post @return!= 0
     */
    public float computePriceOfOrder(int idOrder);

    /**
     *
     * @param idOrder
     * @pre idOrder!=-1
     */
    public void generateBill(int idOrder);
}
