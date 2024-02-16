package org.example.javasuitejavafx;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Income {
    String name; // name of the income
    double value; // value of the income
    Income(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        String valueFormatted = String.format("%.2f", value);
        return name + ": $" + valueFormatted;
    }

}

class Expense {
    String name; // name of the expense
    double value; // price of the expense
    Expense(String name, double value) {
        this.name = name;
        this.value = value;
        // constructor method
    }
    @Override
    public String toString() {
        String valueFormatted = String.format("%.2f", value);
        return name + ": $" + valueFormatted;
    }


}

public class ExpenseTracker {
    static ArrayList<Expense> Expenses = new ArrayList<>(); // list to hold expenses
    static ArrayList<Income> IncomeList = new ArrayList<>(); // list to hold income

    static void addToExpenses(String name, double value) {
        Expense newExpense = new Expense(name, value);
        Expenses.add(newExpense);
    }

    static void addToIncomeList(String name, double value) {
        Income newIncome = new Income(name, value);
        IncomeList.add(newIncome);
    }

    static void removeFromExpenses(int index) {
        try {
            Expenses.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Index out of range.");
        }
    }

    static void removeFromIncomeList(int index) {
        try {
            IncomeList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Index out of range");
        }
    }

    static ArrayList<String> showIncome() {
        /* this will return an arrayList and you can use a for-loop to apply each item
         the last item in the list is the total */
        ArrayList<String> list = new ArrayList<>();
        int n = 1;
        double total = 0;
        for (Income i : IncomeList) {
            String formattedValue = String.format("%.2f", i.value);
            list.add(n + ": " + i.name + ": +$" + formattedValue);
            n++;
            total += i.value;
        }
        String totalFormatted = String.format("%.2f", total);
        list.add(totalFormatted); // index[-1] to get the total of all income
        return list;
    }

    static ArrayList<String> showExpenses() {
        /* this will return an arrayList and you can use a for-loop to apply each item
         the last item in the list is the total */
        ArrayList<String> list = new ArrayList<>();
        int n = 1;
        double total = 0;
        for (Expense i : Expenses) {
            String formattedValue = String.format("%.2f", i.value);
            list.add(n + ": " + i.name + ": -$" + formattedValue);
            n++;
            total += i.value;
        }
        String totalFormatted = String.format("%.2f", total);
        list.add(totalFormatted); // index[-1] to get the total of all expenses
        return list;
    }

    static String calculateBalanceAfterExpenses() {
        double total = 0;
        for (Income i : IncomeList) {
            total += i.value;
        }
        for (Expense i : Expenses) {
            total -= i.value;
        }
        return String.format("%.2f", total);
    }

    static ArrayList<String> calculatePercentages() {
        ArrayList<String> list = new ArrayList<>();
        list.add("-- Income --");
        double incomeTotal = 0;
        double expenseTotal = 0;
        for (Income i : IncomeList) {
            incomeTotal += i.value;
        }
        for (Income i : IncomeList) {
            double percentage = i.value / incomeTotal * 100;
            String percentageFormatted = String.format("%.0f", percentage);
            list.add(i.name + ": " + "(" + percentageFormatted + "%)");
        }

        list.add("-- Expenses --");
        for (Expense i : Expenses) {
            expenseTotal += i.value;
        }
        for (Expense i : Expenses) {
            double percentage = i.value / expenseTotal * 100;
            String percentageFormatted = String.format("%.0f", percentage);
            list.add(i.name + ": " + "(-" + percentageFormatted + "%)");
        }
        return list;
    }





    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            for (String i : showExpenses()) {
                System.out.println(i);
            }
            for (String i : showIncome()) {
                System.out.println(i);
            }
            System.out.println(calculateBalanceAfterExpenses());

            System.out.println("[A]dd expense, [R]emove expense, [+] add income, [-] remove income, [Q]uit.");
            String newName;
            double newValue;
            switch (scanner.nextLine().toLowerCase()) {
                case "a":
                    System.out.println("Name of expense:");
                    newName = scanner.nextLine();
                    System.out.println("Value of expense:");
                    try {
                        newValue = scanner.nextFloat();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.err.println("Could not assign value to expense " + e.getCause());
                        break;
                    }
                    addToExpenses(newName, newValue);
                    break;
                case "r":
                        removeFromExpenses(scanner.nextInt());
                        scanner.nextLine();

                case "+":
                    System.out.println("Name of income:");
                    newName = scanner.nextLine();
                    System.out.println("Value of income:");
                    try {
                        newValue = scanner.nextFloat();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.err.println("Could not assign value to income " + e.getCause());
                        break;
                    }

                    addToIncomeList(newName, newValue);

                    break;

                case "-":
                    removeFromIncomeList(scanner.nextInt());
                    scanner.nextLine();

                case "c":
                    for (String i : calculatePercentages()) {
                        System.out.println(i);
                    }

                case "q":
                    System.exit(0);
                default:
            }
        }
    }
}
