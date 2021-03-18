package model;

import dao.WarehouseDAO;

public class Checkpoint extends WarehouseDAO<Checkpoint> {
    private int idCheck;
    private int series;
    private int ok = 0;

    /**
     * The Checkpoint class represents the actual checkpoints in the Warehouse where the orders can be proccessed. On each bill will be
     * printed the checkpoint with its ID-s and series where the order was proccessed.
     */
    public Checkpoint(){

    }

    public Checkpoint(int id, int series){
        this.idCheck = id;
        this.series = series;
    }

    public int getIdCheck() {
        return idCheck;
    }

    public void setIdCheck(int idCheck) {
        this.idCheck = idCheck;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }
}
