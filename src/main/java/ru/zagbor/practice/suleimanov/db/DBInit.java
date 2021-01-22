package ru.zagbor.practice.suleimanov.db;

public class DBInit {
    public static void init() {
        DBController.getDataUser();
        HibernateSessionFactory.getSessionFactory();
        FlywayInit.init();
    }


}
