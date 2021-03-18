package businessLayer;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderId;
    private Date date;
    private int table;

    public Order(){

    }
    public Order(int orderId, Date date, int table){
        this.orderId = orderId;
        this.date = date;
        this.table = table;
    }

    @Override
    public int hashCode(){
        int hashCode = 31;
        hashCode += hashCode*orderId + hashCode*date.getDay() +
                hashCode*date.getMonth() + hashCode*table;
        return hashCode;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;

        if(this.getClass() != obj.getClass())
            return false;

        Order order = (Order) obj;

        if(this.orderId != order.orderId)
            return false;
        if(this.date.getDay() != order.date.getDay())
            return false;
        if(this.date.getMonth() != order.date.getMonth())
            return false;
        if(this.table != order.table)
            return false;

        return true;

    }

    public String toString(){
        return orderId+" Order is served at the table "+table;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }
}
