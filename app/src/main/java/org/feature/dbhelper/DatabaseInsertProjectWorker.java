package org.feature.dbhelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInsertProjectWorker {
    private PreparedStatement insertSt;

    public DatabaseInsertProjectWorker(Database database) {
        try{
            this.insertSt = database.getConnection().prepareStatement(
                    "INSERT INTO project_worker (project_id, worker_id) VALUES(?, ?)"
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean createNewProjectWorker(long project_id, long worker_id){
        try {
            insertSt.setLong(1, project_id);
            insertSt.setLong(2, worker_id);
            return insertSt.executeUpdate() == 1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
