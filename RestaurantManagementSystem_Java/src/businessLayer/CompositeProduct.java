package businessLayer;

import java.util.ArrayList;

public class CompositeProduct  extends MenuItem{
    private static final long serialVersionUID = 1L;
    private ArrayList<BaseProduct> compositeProduct;

    public CompositeProduct(String name,ArrayList<BaseProduct> compositeProduct) {
        super(name);
        this.compositeProduct = compositeProduct;
        this.price = computePrice();
    }

    /**
     * the function compute the price of a composite Product
     * @return the sum of prices of base products composing the composite product
     */
    @Override
    public float computePrice() {
        float sum = 0.0f;
        for(BaseProduct product: compositeProduct){
            sum += product.getPrice();
        }
        return sum;
    }

    public ArrayList<BaseProduct> getCompositeProduct() {
        return compositeProduct;
    }

    public void setCompositeProduct(ArrayList<BaseProduct> compositeProduct) {
        this.compositeProduct = compositeProduct;
    }

    @Override
    public String toString(){
        String components = this.getName()+"(";
        for(BaseProduct comp : compositeProduct){
            components += comp.getName()+",";
        }
        components +=")";
        return components;
    }
}
