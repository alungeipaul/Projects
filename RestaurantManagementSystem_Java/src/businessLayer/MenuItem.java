package businessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String name;
    protected float price;

    public MenuItem(String name, float price){
        this.name = name;
        this.price = price;
    }

    public MenuItem(String name){
        this.name = name;
    }

    public abstract float computePrice();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String toString(){
        return name+",";
    }

}
