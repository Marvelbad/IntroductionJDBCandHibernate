package org.example;

import org.example.service.UserService;
import org.example.service.UserServiceJDBCImpl;
import org.example.util.Util;


public class Main {
    public static void main(String[] args) {
//        Util.getJDBCConnection();
//        Util.getHibernateConnection();
//        Util.getSessionFactoryProperties();

        Util.getSessionFactory();
//        script(new UserServiceJDBCImpl());

    }

    private static void script(UserService userService) {
        userService.createUserTable();

        userService.saveUser("Name1", "Lastanme1", (byte) 40);
        userService.saveUser("Name2", "Lastanme2", (byte) 45);
        userService.saveUser("Name3", "Lastanme3", (byte) 35);
        userService.saveUser("Name4", "Lastanme4", (byte) 23);

        System.out.println("-------------------------------------");
        userService.getAllUsers().forEach(System.out::println);
        userService.removeUserById(2L);

        System.out.println("-------------------------------------");
        userService.getAllUsers().forEach(System.out::println);

        System.out.println("-------------------------------------");
        userService.clearUserTable();

        System.out.println("-------------------------------------");
        userService.getAllUsers().forEach(System.out::println);

        userService.dropUserTable();
    }
}
