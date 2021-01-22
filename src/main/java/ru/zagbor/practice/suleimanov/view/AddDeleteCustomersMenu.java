package ru.zagbor.practice.suleimanov.view;

import ru.zagbor.practice.suleimanov.controller.AccountStatusController;
import ru.zagbor.practice.suleimanov.controller.CustomerController;
import ru.zagbor.practice.suleimanov.controller.SpecialtyController;
import ru.zagbor.practice.suleimanov.model.Account;
import ru.zagbor.practice.suleimanov.model.AccountStatus;
import ru.zagbor.practice.suleimanov.model.Customer;
import ru.zagbor.practice.suleimanov.model.Specialty;
import ru.zagbor.practice.suleimanov.utils.UtilsParse;
import ru.zagbor.practice.suleimanov.utils.UtilsPrint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AddDeleteCustomersMenu implements Menu {
    private final static BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    private final UtilsPrint utilsPrint = new UtilsPrint();
    private final CustomerController customerController = new CustomerController();
    private final SpecialtyController specialtyController = new SpecialtyController();
    private final AccountStatusController accountStatusController = new AccountStatusController();

    public AddDeleteCustomersMenu() throws IOException {

    }

    @Override
    public void execute() throws IOException {
        chooseAddOrDeleter();
    }

    private void chooseAddOrDeleter() throws IOException{
        while (true) {
            System.out.println("Если вы хотите добавить клиента, то введите 1. " +
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

    private void addCustomerPanel() throws IOException{
        while (true) {
            Customer customer = new Customer();
            System.out.println("Чтобы создать пользователя введите его имя.");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад.");
            String maybeName = BUFFERED_READER.readLine();
            if (maybeName.equals("e")) {
                break;
            }
            customer.setName(maybeName);
            System.out.println("Вы добавили имя клиенту.");

            if (addSpecialties(customer)) {
                break;
            }

        }
    }

    private boolean addSpecialties(Customer customer) throws IOException{
        Set<Specialty> specialties = new HashSet<>();
        while (true) {
            System.out.println("Теперь необходимо добавить пользователю специальности.\nУ клиента их может быть несколько, но не меньше одной." +
                    " Вводить их нужно по одному." +
                    "Вот список специальностей, которые Вы можете добавить:");
            if (customer.getSpecialties() == null) {
                utilsPrint.showSetSpecialties(specialtyController.getAll());
            } else {
                utilsPrint.showSetSpecialties(specialtyController.specialtiesWhichCanAdd(customer.getSpecialties()));
            }
            System.out.println("Введите ID специальности, которую хотите добавить");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад");
            System.out.println("Введите \"g\" на английской раскладке, чтобы пропустить данный пункт меню");
            String maybeId = BUFFERED_READER.readLine();
            if (maybeId.equals("e")) {
                return false;
            }
            if (maybeId.equals("g") && customer.getSpecialties() != null) {
                if (addAccountStatus(customer)) {
                    return true;
                } else {
                    continue;
                }

            }
            long id = UtilsParse.parseLong(maybeId);
            if (id == -1 || !specialtyController.isSpecialtyExist(id)) {
                System.err.println("Вы выбрали вариант, которого не существует или не добавили ни одной специальности, попробуйте еще раз.");
                continue;
            }
            specialties.add(specialtyController.getSpecialtyForId(id).get());
            customer.setSpecialties(specialties);
            System.out.println("Вы добавили специальность клиенту.");
        }
    }

    private void deleteCustomerPanel() throws IOException {
        while (true) {
            System.out.println("Вы хотите удалить пользвателя. Вот список пользователей:");
            utilsPrint.showAllListCustomers();
            System.out.println("Чтобы удалить пользователя введите номер его ID.");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад.");
            String maybeId = BUFFERED_READER.readLine();
            long id = UtilsParse.parseLong(maybeId);
            if (maybeId.equals("e")) {
                break;
            }
            if (id == -1 || !customerController.isCustomerExist(id)) {
                System.err.println("Вы выбрали вариант, которого не существует, попробуйте еще раз.");
                continue;
            }
            customerController.deleteCustomerForID(id);
            System.out.println("Вы удалили клиента");
        }
    }

    private boolean addAccountStatus(Customer customer) throws IOException {
        while (true) {
            System.out.println("Теперь необходимо добавить пользователю статус аккаунта. " +
                    "Вот список специальностей, которые Вы можете добавить:");
            utilsPrint.showListAccountStatus(accountStatusController.getAll());
            System.out.println("Введите в панель ID статуса аккаунта.");
            System.out.println("Введите \"e\" на английской раскладке, чтобы вернуться назад");
            String maybeId = BUFFERED_READER.readLine();
            long id = UtilsParse.parseLong(maybeId);
            if (maybeId.equals("e")) {
                return false;
            }
            if (id == -1 || !accountStatusController.isAccountStatusExist(id)) {
                System.err.println("Вы выбрали вариант, которого не существует, попробуйте еще раз.");
                continue;
            }
            AccountStatus accountStatus = accountStatusController.getByID(id).orElseThrow(IllegalStateException::new);
            Account account = new Account();
            account.setAccountStatus(accountStatus);
            customer.setAccount(account);
            System.out.println("Вы присвоили статус аккаунта клиенту");
            if (saveClientPanel(customer)) {
                return true;
            }
        }
    }

    private boolean saveClientPanel(Customer customer) throws IOException {
        while (true) {
            System.out.println("Если хотите сохранить данного клиента в базу введите на английской раскладке \"y\", если хотите выйти назад, то \"e\"");
            utilsPrint.showCustomerLessId(customer);
            String choice = BUFFERED_READER.readLine();
            if (choice.equals("e")) {
                return false;
            }
            if (choice.equals("y")) {
                customerController.create(customer);
                System.out.println("Вы успешно добавили клиента.");
                return true;
            }
        }
    }
}

