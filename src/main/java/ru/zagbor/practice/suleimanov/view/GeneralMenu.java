package ru.zagbor.practice.suleimanov.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class GeneralMenu implements Menu {

    private final static BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    public GeneralMenu() {
    }

    @Override
    public void execute() throws IOException, SQLException {
        panelGenerator();
    }

    private void panelGenerator() throws IOException, SQLException {
        System.out.println("Вы находитесь в приложении, которое может показывать и редактировать всю информацию о клиентах.");
        while (true) {
            generalPanel();
        }
    }

    private void generalPanel() throws IOException, SQLException {
        while (true) {
            System.out.println("Введите в консоль номер действия:");
            System.out.println("1. Посмотреть/отредактировать данные клиентов.");
            System.out.println("2. Добавить клиента/Удалить клиента.");
            System.out.println("3. Добавить специальность в базу/Удалить специальность из базы.");
            String choice = BUFFERED_READER.readLine();
            MenuSelect menu;
            switch (choice) {
                case ("1"):
                    menu = new MenuSelect(new EditMenu());
                    menu.executePlay();
                    continue;
                case ("2"):
                    menu = new MenuSelect(new AddDeleteCustomersMenu());
                    menu.executePlay();
                    break;
                case ("3"):
                    menu = new MenuSelect(new AddDeleteSpecialtiesMenu());
                    menu.executePlay();
                    break;
                default:
                    System.err.println("Вы выбрали вариант, которого не существует, попробуйте еще раз.");
                    continue;
            }
            break;
        }
    }


}




