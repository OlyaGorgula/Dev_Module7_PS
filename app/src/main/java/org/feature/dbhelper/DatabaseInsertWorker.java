package org.feature.dbhelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInsertWorker {
    private PreparedStatement insertSt;
    private PreparedStatement selectByIdSt;
    private PreparedStatement selectAllSt;

    public DatabaseInsertWorker(Database database) {
        try{
            this.insertSt = database.getConnection().prepareStatement(
                    "INSERT INTO worker (name, birthday, level, salary) VALUES(?, ?, ?, ?)"
            );

            this.selectByIdSt = database.getConnection().prepareStatement(
                    "SELECT name, birthday, level, salary FROM worker WHERE id = ?"
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean createNewWorker(
            String[] name,
            LocalDate[] birthday,
            String[] level,
            int[] salary){
        try {
            for (int i=0; i< name.length; i++){
                insertSt.setString(1, name[i]);
                insertSt.setString(2, birthday[i].toString());
                insertSt.setString(3, level[i]);
                insertSt.setInt(4, salary[i]);
                insertSt.addBatch();
            }
            insertSt.executeBatch();
            return  true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean printByIdWorker(long id){
        try{
            selectByIdSt.setLong(1, id);
        }catch (Exception e){
            return false;
        }
        try(ResultSet rs = selectByIdSt.executeQuery()) {
            if (!rs.next()){
                return false;
            }
            String name = rs.getString("name");
            String birthday = rs.getString("birthday");
            String level = rs.getString("level");
            String salary = rs.getString("salary");
            System.out.println("name: "+name+" birthday: "+birthday + " level: "+level+" salary: "+salary);
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public List<Long> getAllIdSt(){
        List<Long> result = new ArrayList<>();
        try(ResultSet rs = selectAllSt.executeQuery()) {
            while (rs.next()){
                result.add(rs.getLong("id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
