package ru.zagbor.practice.suleimanov.utils;


import ru.zagbor.practice.suleimanov.controller.CustomerController;
import ru.zagbor.practice.suleimanov.model.AccountStatus;
import ru.zagbor.practice.suleimanov.model.Customer;
import ru.zagbor.practice.suleimanov.model.Specialty;

import java.io.PrintStream;
import java.util.List;
import java.util.Set;


public class UtilsPrint {
    private final CustomerController customerController = new CustomerController();

    public UtilsPrint() {
    }

    public void showListAccountStatus(List<AccountStatus> accountStatusList) {
        System.out.printf("%-5s%-10s%n", "ID", "Имя");
        accountStatusList.stream().forEach(accountStatus -> System.out.printf("%-5d%-10s%n", accountStatus.getId(), accountStatus.getStatusesEnum()));
    }

    public void showSetSpecialties(Set<Specialty> specialties) {
        System.out.printf("%-5s%-11s%n", "ID", "Имя");
        specialties.stream().forEach(specialty -> System.out.printf("%-5d%-11s%n", specialty.getId(), specialty.getName()));
    }

    public void showCustomer(Customer customer) {
        System.out.printf("%-5s%-11s%-40s%-11s%n", "ID", "1-Имя", "2-Специальности", "3-Состояние аккаунта");
        System.out.printf("%-5d%-11s%-40s%-11s%n",
                customer.getId(), customer.getName(), customer.toStringSpecialties(), customer.getAccount().getAccountStatus());
    }

    public void showCustomerLessId(Customer customer) {
        System.out.printf("%-10s%-40s%-11s%n", "Имя", "Специальности", "Состояние аккаунта");
        System.out.printf("%-10s%-40s%-11s%n",
                customer.getName(), customer.toStringSpecialties(), customer.getAccount().getAccountStatus());
    }

    public void showAllListCustomers() {
        System.out.printf("%-5s%-11s%-20s%-11s%n", "ID", "Имя", "Номер аккаунта", "Состояние аккаунта");
        customerController.getAll()
                .forEach(this::printCustomer);
    }

    private PrintStream printCustomer(Customer customer) {
        return System.out.printf("%-5d%-11s%-20d%-11s%n",
                customer.getId(), customer.getName(), customer.getAccount().getId(), customer.getAccount().getAccountStatus());
    }
}
