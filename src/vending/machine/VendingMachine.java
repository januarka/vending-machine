/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending.machine;

import java.util.ArrayList;
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
    static int tempReturn100 = 0;
    static int tempReturn10 = 0;
    static int tempCoins = 0;
    static int coins;
    static String returnGate = "empty";
    static String change100 = "No change";
    static String change10 = "Change";
    static String status1 = " ";
    static String status2 = " ";
    static String status3 = " ";
    static ArrayList<String> outlet = new ArrayList<>();
    static int cannedCoffee = 3; // stock canned coffe
    static int waterPetBottle = 0; // stock water pet bottle
    static int sportDrinks = 5; // stock sport drinks
    static boolean buy = false;
    static boolean change = false;

    public static void main(String[] args) {

        // Introduction input method
        VendingMachine.outlet.add("empty");
        System.out.println("----------------------------------");
        System.out.printf("[Input amount]		%s JPY\n", VendingMachine.tempCoins);
        System.out.printf("[Change]                100 JPY     %s\n", VendingMachine.change100);
        System.out.printf("                        10 JPY      %s\n", VendingMachine.change10);
        System.out.printf("[Return gate]           %s\n", VendingMachine.returnGate);
        System.out.printf("[Items for sale]        1. Canned coffee         120 JPY     %s\n", VendingMachine.status1);
        System.out.printf("                        2. Water PET bottle      100 JPY     %s\n", VendingMachine.status2);
        System.out.printf("                        3. Sport drinks          150 JPY     %s\n", VendingMachine.status3);
        System.out.print("[Outlet]");
        for (String part : VendingMachine.outlet) {
            System.out.println("                " + part);
        }
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
                    InsertCoins(method2);
                    break;
                case "2":
                    ChooseItem(method2);
                    break;
                case "3":
                    GetItems();
                    break;
                case "4":
                    ReturnGate();
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
            } else {
                valid = false;
            }
        } else {
            if (method1.equalsIgnoreCase("3") || method1.equalsIgnoreCase("4") || method1.equalsIgnoreCase("5")) {
                valid = true;
            } else {
                valid = false;
            }
        }
        return valid;
    }

    private static void AvailableItem(int tempCoins) {
        // canned coffe
        if (cannedCoffee > 0) {
            if (tempCoins < 120) {
                VendingMachine.status1 = " ";
            } else {
                VendingMachine.status1 = "Available for purchase";
            }
        } else {
            if (tempCoins < 120 || tempCoins >= 120) {
                VendingMachine.status1 = "Sold out";
            }
        }
        // water pet bottle
        if (waterPetBottle > 0) {
            if (tempCoins < 100) {
                VendingMachine.status2 = " ";
            } else {
                VendingMachine.status2 = "Available for purchase";
            }
        } else {
            if (tempCoins < 100 || tempCoins >= 100) {
                VendingMachine.status2 = "Sold out";
            }
        }
        // sport drinks
        if (sportDrinks > 0) {
            if (tempCoins < 150) {
                VendingMachine.status3 = " ";
            } else {
                VendingMachine.status3 = "Available for purchase";
            }
        } else {
            if (tempCoins < 150 || tempCoins >= 150) {
                VendingMachine.status3 = "Sold out";
            }
        }
    }

    private static void InsertCoins(String method2) {
        
        if(VendingMachine.change == true){
            System.out.println("OK\n");
            return;
        }
        // check available item to purchase
        VendingMachine.coins = Integer.parseInt(method2);
        VendingMachine.tempCoins = VendingMachine.tempCoins + VendingMachine.coins;

        // check available item
        AvailableItem(VendingMachine.tempCoins);

        System.out.println("----------------------------------");
        System.out.printf("[Input amount]		%s JPY\n", VendingMachine.tempCoins);
        System.out.printf("[Change]                100 JPY     %s\n", VendingMachine.change100);
        System.out.printf("                        10 JPY      %s\n", VendingMachine.change10);
        System.out.printf("[Return gate]           %s\n", VendingMachine.returnGate);
        System.out.printf("[Items for sale]        1. Canned coffee         120 JPY     %s\n", VendingMachine.status1);
        System.out.printf("                        2. Water PET bottle      100 JPY     %s\n", VendingMachine.status2);
        System.out.printf("                        3. Sport drinks          150 JPY     %s\n", VendingMachine.status3);
        if (VendingMachine.outlet.size() < 2) {
            System.out.println("[Outlet]                empty");
        } else {
            System.out.print("[Outlet]");
            for (int i = 1; i < VendingMachine.outlet.size(); i++) {
                System.out.println("                " + VendingMachine.outlet.get(i));
            }
        }
        System.out.println("----------------------------------");
    }

    private static void ChooseItem(String method2) {
        String itemSelected = method2;
        // select canned coffee
        if (itemSelected.equals("1")) {
            if (VendingMachine.status1.equalsIgnoreCase("Available for purchase")) {
                if (VendingMachine.tempCoins < 120) {
                    System.out.println("Input Method");
                    System.out.println("Input [command number + space + arguments] into prompt");
                    System.out.print("Your input: ");
                    method = input.nextLine();
                    // split method input value from user
                    SplitValue(method);
                    return;
                } else {
                    VendingMachine.tempCoins = VendingMachine.tempCoins - 120;
                    VendingMachine.cannedCoffee = VendingMachine.cannedCoffee - 1;
                    VendingMachine.outlet.add("Canned coffee");
                    VendingMachine.buy = true;
                    // check available item
                    AvailableItem(VendingMachine.tempCoins);
                }
            } else {
                System.out.println("OK\n");
                return;
            }
        } else if (itemSelected.equals("2")) {
            if (VendingMachine.status2.equalsIgnoreCase("Available for purchase")) {
                if (VendingMachine.tempCoins < 100) {
                    System.out.println("Input Method");
                    System.out.println("Input [command number + space + arguments] into prompt");
                    System.out.print("Your input: ");
                    method = input.nextLine();
                    // split method input value from user
                    SplitValue(method);
                    return;
                } else {
                    VendingMachine.tempCoins = VendingMachine.tempCoins - 100;
                    VendingMachine.waterPetBottle = VendingMachine.waterPetBottle - 1;
                    VendingMachine.outlet.add("Water PET bottle");
                    VendingMachine.buy = true;
                    // check available item
                    AvailableItem(VendingMachine.tempCoins);
                }
            } else {
                System.out.println("OK\n");
                return;
            }
        } else if (itemSelected.equals("3")) {
            if (VendingMachine.status3.equalsIgnoreCase("Available for purchase")) {
                if (VendingMachine.tempCoins < 150) {
                    System.out.println("Input Method");
                    System.out.println("Input [command number + space + arguments] into prompt");
                    System.out.print("Your input: ");
                    method = input.nextLine();
                    // split method input value from user
                    SplitValue(method);
                    return;
                } else {
                    VendingMachine.tempCoins = VendingMachine.tempCoins - 150;
                    VendingMachine.sportDrinks = VendingMachine.sportDrinks - 1;
                    VendingMachine.outlet.add("Sport drinks");
                    VendingMachine.buy = true;
                    // check available item
                    AvailableItem(VendingMachine.tempCoins);
                }
            } else {
                System.out.println("OK\n");
                return;
            }
        }
        System.out.println("----------------------------------");
        System.out.printf("[Input amount]		%s JPY\n", VendingMachine.tempCoins);
        System.out.printf("[Change]                100 JPY     %s\n", VendingMachine.change100);
        System.out.printf("                        10 JPY      %s\n", VendingMachine.change10);
        System.out.printf("[Return gate]           %s\n", VendingMachine.returnGate);
        System.out.printf("[Items for sale]        1. Canned coffee         120 JPY     %s\n", VendingMachine.status1);
        System.out.printf("                        2. Water PET bottle      100 JPY     %s\n", VendingMachine.status2);
        System.out.printf("                        3. Sport drinks          150 JPY     %s\n", VendingMachine.status3);
        if (VendingMachine.outlet.size() < 2) {
            System.out.println("[Outlet]                 empty");
        } else {
            System.out.print("[Outlet]");
            for (int i = 1; i < VendingMachine.outlet.size(); i++) {
                System.out.println("                " + VendingMachine.outlet.get(i));
            }
        }
        System.out.println("----------------------------------");
    }

    private static void GetItems() {

        if (VendingMachine.outlet.size() < 2) {
            System.out.println("OK\n");
            return;
        }
        
        // delete existing item
        if (VendingMachine.outlet.size() > 1) {
            for (int i = 1; i <= VendingMachine.outlet.size(); i++) {
                VendingMachine.outlet.remove(i);
            }
        } else {

        }

        System.out.println("----------------------------------");
        System.out.printf("[Input amount]		%s JPY\n", VendingMachine.tempCoins);
        System.out.printf("[Change]                100 JPY     %s\n", VendingMachine.change100);
        System.out.printf("                        10 JPY      %s\n", VendingMachine.change10);
        System.out.printf("[Return gate]           %s\n", VendingMachine.returnGate);
        System.out.printf("[Items for sale]        1. Canned coffee         120 JPY     %s\n", VendingMachine.status1);
        System.out.printf("                        2. Water PET bottle      100 JPY     %s\n", VendingMachine.status2);
        System.out.printf("                        3. Sport drinks          150 JPY     %s\n", VendingMachine.status3);
        if (VendingMachine.outlet.size() < 2) {
            System.out.println("[Outlet]                empty");
        } else {
            System.out.print("[Outlet]");
            for (int i = 1; i < VendingMachine.outlet.size(); i++) {
                System.out.println("                " + VendingMachine.outlet.get(i));
            }
        }
        System.out.println("----------------------------------");
    }

    private static void ReturnGate() {
        if(VendingMachine.change == true){
            System.out.println("OK\n");
            return;
        }
        // if no item has been purchased
        if (VendingMachine.outlet.size() < 2 && VendingMachine.buy == false) {
            // initialize temp return gate
            int returnGate = VendingMachine.tempCoins;

            // initialize temp coins after return gate
            VendingMachine.tempCoins = 0;
            
            // initialize change 100 and 10
            VendingMachine.change100 = "No change";
            VendingMachine.change10 = "No change";

            // check status item
            AvailableItem(VendingMachine.tempCoins);

            System.out.println("----------------------------------");
            System.out.printf("[Input amount]		%s JPY\n", VendingMachine.tempCoins);
            System.out.printf("[Change]                100 JPY     %s\n", VendingMachine.change100);
            System.out.printf("                        10 JPY      %s\n", VendingMachine.change10);
            System.out.printf("[Return gate]           %s JPY\n", returnGate);
            System.out.printf("[Items for sale]        1. Canned coffee         120 JPY     %s\n", VendingMachine.status1);
            System.out.printf("                        2. Water PET bottle      100 JPY     %s\n", VendingMachine.status2);
            System.out.printf("                        3. Sport drinks          150 JPY     %s\n", VendingMachine.status3);
            if (VendingMachine.outlet.size() < 2) {
                System.out.println("[Outlet]                empty");
            } else {
                System.out.print("[Outlet]");
                for (int i = 1; i < VendingMachine.outlet.size(); i++) {
                    System.out.println("                " + VendingMachine.outlet.get(i));
                }
            }
            System.out.println("----------------------------------");
            VendingMachine.change = true;
        } else {
            // initialize modulus temp coins with 100
            int a = VendingMachine.tempCoins % 100;
            VendingMachine.tempReturn100 = VendingMachine.tempCoins / 100;
            // initialize modulus a with 10
            int b = a % 10;
            VendingMachine.tempReturn10 = a / 10;
            if (VendingMachine.tempCoins > 0) {
                if (a != 0) {
                    VendingMachine.tempReturn100 = VendingMachine.tempCoins / 100;
                    if (b == 0) {
                        VendingMachine.tempReturn10 = a / 10;
                    }
                } else {
                    VendingMachine.tempReturn100 = VendingMachine.tempCoins / 100;
                }
                VendingMachine.tempCoins = 0;
            } else {
                VendingMachine.returnGate = "empty";
            }
            
            // check change coins 100
            if (VendingMachine.tempReturn100 > 0){
                VendingMachine.change100 = "Change";
            } else{
                VendingMachine.change100 = "No change";
            }
            
            // check change coins 10
            if (VendingMachine.tempReturn10 > 0){
                VendingMachine.change10 = "Change";
            } else{
                VendingMachine.change10 = "No change";
            }
                
            // check status item
            AvailableItem(VendingMachine.tempCoins);

            System.out.println("----------------------------------");
            System.out.printf("[Input amount]		%s JPY\n", VendingMachine.tempCoins);
            System.out.printf("[Change]                100 JPY     %s\n", VendingMachine.change100);
            System.out.printf("                        10 JPY      %s\n", VendingMachine.change10);
            System.out.print("[Return gate]");
            if (VendingMachine.tempReturn100 > 0) {
                for (int i = 0; i < VendingMachine.tempReturn100; i++) {
                    if (i == 0) {
                        System.out.println("           100 JPY");
                    } else {
                        System.out.println("                        100 JPY");
                    }
                    VendingMachine.coins100--;
                }
            }
            if (VendingMachine.tempReturn10 > 0) {
                for (int i = 0; i < VendingMachine.tempReturn10; i++) {
                    if (i == 0 && VendingMachine.tempReturn100 < 1) {
                        System.out.println("           10 JPY");
                    } else {
                        System.out.println("                        10 JPY");
                    }
                    VendingMachine.coins10--;
                }
            }
            System.out.printf("[Items for sale]        1. Canned coffee         120 JPY     %s\n", VendingMachine.status1);
            System.out.printf("                        2. Water PET bottle      100 JPY     %s\n", VendingMachine.status2);
            System.out.printf("                        3. Sport drinks          150 JPY     %s\n", VendingMachine.status3);
            if (VendingMachine.outlet.size() < 2) {
                System.out.println("[Outlet]                empty");
            } else {
                System.out.print("[Outlet]");
                for (int i = 1; i < VendingMachine.outlet.size(); i++) {
                    System.out.println("                " + VendingMachine.outlet.get(i));
                }
            }
            System.out.println("----------------------------------");
            VendingMachine.change = true;
        }
    }
}
