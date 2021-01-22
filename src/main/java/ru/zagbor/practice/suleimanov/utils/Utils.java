package ru.zagbor.practice.suleimanov.utils;

import org.hibernate.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;
import java.util.function.Function;

public class Utils {


    public Utils() {
    }

    public static Long getId(Statement statement) throws SQLException {
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.first();
        return resultSet.getLong(1);

    }


}
