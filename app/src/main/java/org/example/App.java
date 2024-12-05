package org.example;

import org.feature.dbhelper.*;
import java.time.LocalDate;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        Database database = Database.getInstance();

        new DatabaseInitService().initDb(database);
        new DatabasePopulateService().populateDb(database);

        int countWorker = 3;
        Random random = new Random();
        String[] nameWorker = new String[countWorker];
        LocalDate[] birthdayWorker = new LocalDate[countWorker];
        String[] enumlevel = {"Trainee", "Junior", "Middle", "Senior"};
        String[] levelWorker = new String[countWorker];
        int[] salaryWorker = new int[countWorker];
        for (int i=0; i<countWorker; i++){
            nameWorker[i] = "Worker"+i;
            birthdayWorker[i] = LocalDate.now().minusYears(20+i);
            levelWorker[i] = enumlevel[random.nextInt(3)];
            salaryWorker[i] = 10000+(i*100);
        }

        DatabaseInsertWorker databaseInsertWorker = new DatabaseInsertWorker(database);
        boolean resultInsertWorker = databaseInsertWorker.createNewWorker(nameWorker, birthdayWorker, levelWorker, salaryWorker);
        System.out.println("resultInsertWorker = " +resultInsertWorker);

        DatabaseInsertClient databaseInsertClient = new DatabaseInsertClient(database);
        boolean resultClient = databaseInsertClient.createNewClient("Client 12");
        System.out.println("resultClient = " +resultClient);

        DatabaseInsertProject databaseInsertProject = new DatabaseInsertProject(database);
        boolean resultProject = databaseInsertProject.createNewProject(
                1, "Project12",
                LocalDate.now().minusYears(25),
                LocalDate.now().minusYears(30));
        System.out.println("resultProject = " +resultProject);

        DatabaseInsertProjectWorker databaseInsertProjectWorker = new DatabaseInsertProjectWorker(database);
        boolean resultProjectWorker = databaseInsertProjectWorker.createNewProjectWorker(1, 4);
        System.out.println("resultProjectWorker = " +resultProjectWorker);

    }
}
