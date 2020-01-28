/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending.machine;

import java.util.Scanner;

/**
 *
 * @author janua
 */
public class VendingMachine {

    static Scanner input = new Scanner(System.in);
    static String method = "";

    private static boolean CheckInput(String methodInput) {
        boolean valid = false;
        String value = methodInput;
        String[] valueInputs = value.split(" ");

        // check command number
        String method1 = valueInputs[0];
        for (int i = 1; i <= 5; i++) {
            if (method1.equalsIgnoreCase(String.valueOf(i))) {
                valid = true;
            } else {
                valid = false;
            }
        }
        if (valid = false) {
            System.out.println("Wrong command number input");
        }

        // check argument
        if (valueInputs.length > 1) {
            String method2 = valueInputs[1];
            if (method1.equalsIgnoreCase("1")) {
                if (method2.equalsIgnoreCase("10")
                        || method2.equalsIgnoreCase("50")
                        || method2.equalsIgnoreCase("100")
                        || method2.equalsIgnoreCase("500")) {
                    valid = true;
                } else {
                    System.out.println("Wrong arguments input");
                    valid = false;
                }
            } else if (method1.equalsIgnoreCase("2")) {
                if (method2.equalsIgnoreCase("1")
                        || method2.equalsIgnoreCase("2")
                        || method2.equalsIgnoreCase("3")) {
                    valid = true;
                } else {
                    System.out.println("Wrong arguments input");
                    valid = false;
                }
            }

        }
        return valid;
    }

    private static void CaseOne(String method1, String method2) {
        String returnGate = "empty";
        String status1 = " ";
        String status2 = " ";
        String status3 = " ";

        System.out.println("----------------------------------");
        System.out.printf("[Input amount]		%s JPY\n", method2);
        System.out.println("[Change]                100 JPY     No change\n"
                + "                        10 JPY      Change");
        System.out.printf("[Return gate]           %s\n", returnGate);
        System.out.printf("[Items for sale]        1. Canned coffee         120 JPY     %s\n", status1);
        System.out.printf("                        2. Water PET bottle      100 JPY     %s\n", status2);
        System.out.printf("                        3. Sport drinks          150 JPY     %s\n", status3);
        System.out.println("[Outlet]                Canned coffee\n"
                + "                        Canned coffee\n"
                + "                        Water PET bottle");
        System.out.println("----------------------------------");
    }

    private static String SplitValue(String methodInput) {

        String value = methodInput;
        String method1, method2 = null;
        // check method input
        if (CheckInput(method) != true) {
            System.out.println("OK\n");
        } else {
            String[] valueInputs = value.split(" ");
            method1 = valueInputs[0];
            if (valueInputs.length > 1) {
                method2 = valueInputs[1];
            }
            switch (method1) {
                case "1":
                    CaseOne(method1, method2);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                default:
                    System.out.println("OK");
            }
        }
        return value;
    }

    public static void main(String[] args) {

        boolean loop = true;

        // Introduction input method
        System.out.println("Command (1)\n"
                + "	Command name     : Insert coins\n"
                + "	Command number   : 1\n"
                + "	Argument         : int, coin types (any of 10, 50, 100, 500)\n"
                + "For example: “1 500” (Insert 500 JPY coin)\n"
                + "\n"
                + "Command (2)\n"
                + "	Command name     : Choose item to purchase\n"
                + "	Command number   : 2\n"
                + "	Argument         : int, item types (any of item numbers)\n"
                + "For example: “2 1” (1: Choose Canned coffee)\n"
                + "\n"
                + "Command (3)\n"
                + "	Command name     : Get items\n"
                + "	Command number   : 3\n"
                + "	Argument         : None\n"
                + "For example: “3” (Get items)\n"
                + "\n"
                + "Command (4)\n"
                + "	Command name     : Return coins\n"
                + "	Command number   : 4\n"
                + "	Argument         : None\n"
                + "For example: “4” (Pull Return lever)\n"
                + "\n"
                + "Command (5)\n"
                + "	Command name     : Get returned coins\n"
                + "	Command number   : 5\n"
                + "	Argument         : None\n"
                + "For example: “5” (Get returned coins)\n");

        do {
            System.out.println("Input Method");
            System.out.println("Input [command number + space + arguments] into prompt");
            System.out.print("Your input: ");
            method = input.nextLine();

            // split method input value from user
            SplitValue(method);

        } while (loop = true);

    }

}
