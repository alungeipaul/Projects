package businessLayer;

import dao.CheckpointDAO;
import model.Checkpoint;

import java.util.ArrayList;

public class CheckpointBLL {
    private CheckpointDAO chk;

    public CheckpointBLL(){
        this.chk = new CheckpointDAO();
    }

    /**
     * finds all the checkpoints in the database
     * @return
     */
    public ArrayList<Checkpoint> findAllCheckpoints(){
        ArrayList<Checkpoint> checks = new ArrayList<>();
        checks = chk.findAll();
        if(checks == null){
            System.out.println("ERROR - Find all checkpoints");
        }
        return checks;
    }

    public void addCheckpoint(Checkpoint checkpoint){
        chk.insert(checkpoint);
    }

    public void deleteCheckpoint(int id){
        chk.delete(id);
    }
}
