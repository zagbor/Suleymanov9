package ru.zagbor.practice.suleimanov.view;

import ru.zagbor.practice.suleimanov.controller.AccountController;
import ru.zagbor.practice.suleimanov.controller.AccountStatusController;
import ru.zagbor.practice.suleimanov.controller.CustomerController;
import ru.zagbor.practice.suleimanov.controller.SpecialtyController;
import ru.zagbor.practice.suleimanov.model.AccountStatus;
import ru.zagbor.practice.suleimanov.model.Customer;
import ru.zagbor.practice.suleimanov.model.Specialty;
import ru.zagbor.practice.suleimanov.utils.UtilsParse;
import ru.zagbor.practice.suleimanov.utils.UtilsPrint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class EditMenu implements Menu {
    private final static BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    private final SpecialtyController specialtyController = new SpecialtyController();
    private final CustomerController customerController = new CustomerController();
    private final AccountController accountController = new AccountController();
    private final AccountStatusController accountStatusController = new AccountStatusController();
    private final UtilsPrint utilsPrint = new UtilsPrint();


    public EditMenu() {
    }

    @Override
    public void execute() throws IOException, SQLException {
        chooseCustomerPanel();
    }

    private void chooseCustomerPanel() throws IOException, SQLException {
        while (true) {
            utilsPrint.showAllListCustomers();
            System.out.println("Чтобы увидеть и/или отредактировать всю информацию по определенному клиенту введите его ID.");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад.");
            String maybeId = BUFFERED_READER.readLine();
            if (maybeId.equals("e")) {
                break;
            }
            long id = UtilsParse.parseLong(maybeId);
            if (id == -1 || !customerController.isCustomerExist(id)) {
                System.err.println("Вы выбрали вариант, которого не существует, попробуйте еще раз.");
                continue;
            }
            editPanel(id);
        }

    }

    private void editPanel(long id) throws IOException, SQLException {
        while (true) {
            Customer customer = customerController.getCustomerForID(id).get();
            utilsPrint.showCustomer(customer);
            System.out.println("Чтобы отредактировать данные введите номер колонки, которую хотите изменить.");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад.");
            String choice = BUFFERED_READER.readLine();
            switch (choice) {
                case ("1"):
                    changeNamePanel(id);
                    continue;
                case ("2"):
                    editSpecialtyPanel(id);
                    continue;
                case ("3"):
                    editAccountStatusPanel(id);
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

    private void editAccountStatusPanel(long id) throws IOException {
        while (true) {
            Customer customer = customerController.getCustomerForID(id).get();
            System.out.println("Вы хотите изменить статус аккаунта данного клиента, вот список возможных вариантов статусов," +
                    " которые могут быть применены для данного клиента:");
            List<AccountStatus> availableAccountStatuses = accountStatusController.getAll();
            availableAccountStatuses.remove(customer.getAccount().getAccountStatus());
            utilsPrint.showListAccountStatus(availableAccountStatuses);
            System.out.println("Введите в панель ID статуса аккаунта.");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад.");
            String maybeIdAccountStatus = BUFFERED_READER.readLine();
            long idAccountStatus = UtilsParse.parseLong(maybeIdAccountStatus);
            if (maybeIdAccountStatus.equals("e")) {
                break;
            }
            if (id == -1 || !accountStatusController.isAccountStatusExist(id)) {
                System.err.println("Вы выбрали вариант, которого не существует, попробуйте еще раз.");
                continue;
            }
            AccountStatus accountStatus = accountStatusController.getByID(idAccountStatus).orElseThrow(IllegalStateException::new);
            accountController.changeAccountStatus(customer.getAccount(), accountStatus);
            System.out.println("Вы изменили статус");
            break;
        }

    }

    private void editSpecialtyPanel(long id) throws IOException, SQLException {
        while (true) {
            Customer customer = customerController.getCustomerForID(id).get();
            utilsPrint.showCustomer(customer);
            System.out.println("Если вы хотите добавить специальность к клиенту, то введите 1. " +
                    "\nЕсли удалить то введите 2.");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад.");
            String choice = BUFFERED_READER.readLine();
            switch (choice) {
                case ("1"):
                    addSpecialtyPanel(id);
                    continue;
                case ("2"):
                    deleteSpecialtyPanel(id);
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

    private void deleteSpecialtyPanel(long id) throws IOException {
        while (true) {
            Customer customer = customerController.getCustomerForID(id).get();
            System.out.println("Вы хотите удалить специальность у данного клиента, вот список специальностей, которые Вы можете удалить:");
            utilsPrint.showSetSpecialties(customer.getSpecialties());
            System.out.println("Введите ID специальности, которую хотите удалить у клиента.");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад.");
            String maybeIdSpecialty = BUFFERED_READER.readLine();
            if (maybeIdSpecialty.equals("e")) {
                break;
            }
            long idSpecialty = UtilsParse.parseLong(maybeIdSpecialty);
            if (idSpecialty == -1) {
                System.err.println("Вы ввели не число");
                continue;
            }

            if (!customer.getSpecialties().contains(specialtyController.getSpecialtyForId(idSpecialty).get())) {
                System.err.println("Вы ввели ID, который невозможно удалить");
                continue;
            }
            Specialty specialty = specialtyController.getSpecialtyForId(idSpecialty).get();
            specialtyController.deleteSpecialtyCustomer(specialty, customer);
            break;
        }
    }

    private void addSpecialtyPanel(long id) throws IOException {
        while (true) {
            Customer customer = customerController.getCustomerForID(id).get();
            System.out.println("Вы хотите добавить специальность данному клиенту, вот список специальностей, которые Вы можете добавить:");
            Set<Specialty> whichCanAdd = specialtyController.specialtiesWhichCanAdd(customer.getSpecialties());
            utilsPrint.showSetSpecialties(whichCanAdd);
            System.out.println("Введите ID специальности, которую хотите добавить клиенту");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад.");
            String maybeIdSpecialty = BUFFERED_READER.readLine();
            if (maybeIdSpecialty.equals("e")) {
                break;
            }
            long idSpecialty = UtilsParse.parseLong(maybeIdSpecialty);
            if (idSpecialty == -1) {
                System.err.println("Вы ввели не число");
                continue;
            }

            if (!whichCanAdd.contains(specialtyController.getSpecialtyForId(idSpecialty).get())) {
                System.err.println("Вы ввели ID, который невозможно добавить");
                continue;
            }
            customerController.addSpecialtyCustomer(customer, idSpecialty);
            break;
        }
    }

    private void changeNamePanel(long id) throws IOException {
        while (true) {
            Customer customer = customerController.getCustomerForID(id).get();
            System.out.println("Введите новое имя для пользователя: \nили введите \"e\" на английской раскладке, чтобы вернуться назад.");
            String newName = BUFFERED_READER.readLine();
            if (newName.equals("e")) {
                break;
            }
            customerController.changeName(customer, newName);
            customer.setName(newName);
            System.out.println("Вы успешно отредактировали клиента, вот результат:");
            break;
        }
    }
}

