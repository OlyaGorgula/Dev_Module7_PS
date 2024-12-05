package org.feature.dbhelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInsertClient {
    private PreparedStatement insertSt;

    public DatabaseInsertClient(Database database) {
        try{
            this.insertSt = database.getConnection().prepareStatement(
                    "INSERT INTO client (name) VALUES(?)"
            );
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public boolean createNewClient(String name){
        try {
            insertSt.setString(1, name);
            return insertSt.executeUpdate() == 1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
