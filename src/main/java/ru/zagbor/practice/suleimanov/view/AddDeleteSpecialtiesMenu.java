package ru.zagbor.practice.suleimanov.view;


import ru.zagbor.practice.suleimanov.controller.SpecialtyController;
import ru.zagbor.practice.suleimanov.model.Specialty;
import ru.zagbor.practice.suleimanov.utils.UtilsParse;
import ru.zagbor.practice.suleimanov.utils.UtilsPrint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class AddDeleteSpecialtiesMenu implements Menu {
    private final static BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    private final SpecialtyController specialtyController = new SpecialtyController();
    UtilsPrint utilsPrint = new UtilsPrint();

    public AddDeleteSpecialtiesMenu() throws IOException {
    }

    @Override
    public void execute() throws IOException, SQLException {
        chooseAddOrDelete();
    }

    private void chooseAddOrDelete() throws IOException, SQLException {
        while (true) {
            System.out.println("Если вы хотите добавить специальность, то введите 1. " +
                    "\nЕсли удалить то введите 2.");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад");
            String choice = BUFFERED_READER.readLine();
            switch (choice) {
                case ("1"):
                    addCustomerPanel();
                    continue;
                case ("2"):
                    deleteCustomerPanel();
                    continue;
                case ("e"):
                    break;
                default:
                    System.err.println("Вы ввели несуществующий вариант, попробуйте еще раз.");
                    continue;
            }
            break;
        }
    }

    private void addCustomerPanel() throws IOException, SQLException {
        while (true) {
            Specialty specialty = new Specialty();
            System.out.println("Чтобы добавить специальность введите её имя.");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад.");
            String maybeName = BUFFERED_READER.readLine();
            if (maybeName.equals("e")) {
                break;
            }
            specialty.setName(maybeName);
            System.out.println("Вы добавили имя специальности.");
            specialtyController.create(specialty);
        }
    }

    private void deleteCustomerPanel() throws IOException, SQLException {
        while (true) {
            System.out.println("Вы хотите удалить специальность. Вот список специальностей:");
            utilsPrint.showSetSpecialties(specialtyController.getAll());
            System.out.println("Чтобы удалить специальность введите номер ее ID.");
            System.out.println("Помните, если вы удалите специальность, то она исчезнет у всех пользователей, где она была добавлена.");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад.");
            String maybeId = BUFFERED_READER.readLine();
            long id = UtilsParse.parseLong(maybeId);
            if (maybeId.equals("e")) {
                break;
            }
            if (id == -1 || !specialtyController.isSpecialtyExist(id)) {
                System.err.println("Вы выбрали вариант, которого не существует, попробуйте еще раз.");
                continue;
            }
            specialtyController.deleteById(id);
            System.out.println("Вы удалили специальность");
        }
    }


}
