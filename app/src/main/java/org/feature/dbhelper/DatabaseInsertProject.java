package org.feature.dbhelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseInsertProject {
    private PreparedStatement insertSt;

    public DatabaseInsertProject(Database database) {
        try{
            this.insertSt = database.getConnection().prepareStatement(
                    "INSERT INTO project (client_id, name, start_date, finish_date) VALUES(?, ?, ?, ?)"
            );
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public boolean createNewProject(
            long client_id, String name, LocalDate start_date, LocalDate finish_date){
        try {
            insertSt.setLong(1, client_id);
            insertSt.setString(2, name);
            insertSt.setString(3, start_date.toString());
            insertSt.setString(4, finish_date.toString());
            return insertSt.executeUpdate() == 1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
