package org.feature.dbhelper;

import org.feature.prefs.Prefs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseInitService {
    public void initDb(Database database){
        String initDbFilename = new Prefs().getString(Prefs.INIT_DB_SQL_FILE_PATH);
        try {
            String sqlBlocks = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(initDbFilename))
            );
            String[] arraySQL = sqlBlocks.split(";");
            for (String sql : arraySQL){
                System.out.println("sql = "+sql);
                database.executeUpdate(sql);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
