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
    static int coins10 = 9;
    static int coins100 = 4;
    static int tempCoins = 0;
    static int coins;

    public static void main(String[] args) {

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

        System.out.println("----------------------------------");
        System.out.println("[Input amount]		0 JPY\n");
        System.out.println("[Change]                100 JPY     No change");
        System.out.println("                        10 JPY      Change");
        System.out.println("[Return gate]           empty");
        System.out.println("[Items for sale]        1. Canned coffee         120 JPY     ");
        System.out.println("                        2. Water PET bottle      100 JPY     Sold out");
        System.out.println("                        3. Sport drinks          150 JPY     ");
        System.out.println("[Outlet]                empty");
        System.out.println("----------------------------------");

        while (true) {
            System.out.println("Input Method");
            System.out.println("Input [command number + space + arguments] into prompt");
            System.out.print("Your input: ");
            method = input.nextLine();

            // split method input value from user
            SplitValue(method);

        }
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
                    CaseOne(method2);
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

        // check argument
        if (valueInputs.length > 1) {
            String method2 = valueInputs[1];
            if (method1.equalsIgnoreCase("1")) {
                if (coins10 <= 0) {
                    if (method2.equalsIgnoreCase("50")
                            || method2.equalsIgnoreCase("100")
                            || method2.equalsIgnoreCase("500")) {
                        valid = false;
                    }
                } else if (coins100 <= 0) {
                    if (method2.equalsIgnoreCase("500")) {
                        valid = false;
                    }
                } else {
                    if (method2.equalsIgnoreCase("10")
                            || method2.equalsIgnoreCase("50")
                            || method2.equalsIgnoreCase("100")
                            || method2.equalsIgnoreCase("500")) {
                        valid = true;
                    }
                }
            } else if (method1.equalsIgnoreCase("2")) {
                if (method2.equalsIgnoreCase("1")
                        || method2.equalsIgnoreCase("2")
                        || method2.equalsIgnoreCase("3")) {
                    valid = true;
                } else {
                    valid = false;
                }
            }
        }
        return valid;
    }

    private static void CaseOne(String method2) {
        String returnGate = "empty";
        String change100 = "No change";
        String change10 = "Change";
        String status1 = " ";
        String status2 = " ";
        String status3 = " ";
        String outlet = "empty";
        int cannedCoffee = 3; // stock canned coffe
        int waterPetBottle = 0; // stock water pet bottle
        int sportDrinks = 5; // stock sport drinks

        // check available item to purchase
        coins = Integer.parseInt(method2);
        VendingMachine.tempCoins = tempCoins + coins;
        int allCoins = tempCoins;
        // canned coffe
        if (cannedCoffee > 0) {
            if (allCoins < 120) {
                status1 = " ";
            } else {
                status1 = "Available for purchase";
            }
        } else {
            if (allCoins < 120 || allCoins >= 120) {
                status1 = "Sold out";
            }
        }
        // water pet bottle
        if (waterPetBottle > 0) {
            if (allCoins < 100) {
                status2 = " ";
            } else {
                status2 = "Available for purchase";
            }
        } else {
            if (allCoins < 100 || allCoins >= 100) {
                status2 = "Sold out";
            }
        }
        // sport drinks
        if (sportDrinks > 0) {
            if (allCoins < 150) {
                status3 = " ";
            } else {
                status3 = "Available for purchase";
            }
        } else {
            if (allCoins < 150 || allCoins >= 150) {
                status3 = "Sold out";
            }
        }

        System.out.println("----------------------------------");
        System.out.printf("[Input amount]		%s JPY\n", allCoins);
        System.out.printf("[Change]                100 JPY     %s\n", change100);
        System.out.printf("                        10 JPY      %s\n", change10);
        System.out.printf("[Return gate]           %s\n", returnGate);
        System.out.printf("[Items for sale]        1. Canned coffee         120 JPY     %s\n", status1);
        System.out.printf("                        2. Water PET bottle      100 JPY     %s\n", status2);
        System.out.printf("                        3. Sport drinks          150 JPY     %s\n", status3);
        System.out.printf("[Outlet]                %s\n", outlet);
        System.out.println("----------------------------------");
    }
}
