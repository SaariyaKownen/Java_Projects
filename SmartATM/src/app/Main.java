package app;

import controller.ATMController;
import util.FileManager;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "====================================");

        System.out.println(
                "       SMART ATM BANKING SYSTEM");

        System.out.println(
                "====================================");

        FileManager.createFileIfNeeded();

        ATMController controller =
                new ATMController();

        controller.start();
    }
}