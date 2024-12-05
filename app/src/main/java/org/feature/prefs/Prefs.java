package org.feature.prefs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Prefs {
    public static final String DB_JDBC_CONNECTION_URL = "dbUrl";
    public static final String INIT_DB_SQL_FILE_PATH = "initDbSql";
    public static final String POPULATE_DB_SQL_FILE_PATH = "populateDbSql";

    public static final String FIND_LONGEST_PROJECT_FILE_PATH = "find_longest_projectDbSql";
    public static final String FIND_MAX_PROJECTS_CLIENT_FILE_PATH = "find_max_projects_clientDbSql";
    public static final String FIND_MAX_SALARY_WORKER_FILE_PATH = "find_max_salary_workerDbSql";
    public static final String FIND_YOUNGEST_ELDEST_WORKERS_FILE_PATH = "find_youngest_eldest_workersDbSql";
    public static final String PRINT_PROJECT_PRICES_FILE_PATH = "print_project_pricesDbSql";

    public static final String DEFAULT_PREFS_FILENAME = "prefs.json";
    private Map<String, Object> prefs = new HashMap<>();

    public Prefs(){
        this(DEFAULT_PREFS_FILENAME);
    }

    public Prefs(String filename){
        try{
            String json = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(filename))
            );
            TypeToken<?> typeToken = TypeToken.getParameterized(
                    Map.class,
                    String.class,
                    Object.class);
            prefs = new Gson().fromJson(json, typeToken.getType());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getString(String key){
        return getPref(key).toString();
    }

    public Object getPref(String key){
        return prefs.get(key);
    }

    public static void main(String[] args) {
        Prefs prefs = new Prefs();
        System.out.println("prefs.getString(\"dbUrl\") = "+prefs.getString(DB_JDBC_CONNECTION_URL));
    }
}
