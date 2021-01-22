package ru.zagbor.practice.suleimanov;

import ru.zagbor.practice.suleimanov.db.DBInit;
import ru.zagbor.practice.suleimanov.view.GeneralMenu;
import ru.zagbor.practice.suleimanov.view.MenuSelect;

import java.io.IOException;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws IOException, SQLException {
        DBInit.init();
        new MenuSelect(new GeneralMenu()).executePlay();
    }
}
