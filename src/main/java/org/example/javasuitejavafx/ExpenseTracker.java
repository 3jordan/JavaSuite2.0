package org.example.javasuitejavafx;

import java.util.ArrayList;
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = 0;
            double totalIncome = 0.0;
            System.out.println("Income (" + IncomeList.size() + ")");
            for (Income i : IncomeList) {
                System.out.println(n + "# " + i.toString());
                totalIncome += i.value;
                n++;
            }
            String totalFormatted = String.format("%.2f", totalIncome);
            System.out.println("Total: $" + totalFormatted);

            n = 0;
            double totalExpense = 0;
            System.out.println("Expenses (" + Expenses.size() + ")");
            for (Expense i : Expenses) { // this for loop prints each expense in list
                System.out.println(n + "# " + i.toString());
                totalExpense += i.value;
                n++;
            }
            double total = totalIncome - totalExpense;
            totalFormatted = String.format("%.2f", total);
            System.out.println("Total: $" + totalFormatted);

            System.out.println("[A]dd expense, [R]emove expense, [+] add income, [-] remove income, [Q]uit.");
            String newName;
            double newValue;
            switch (scanner.nextLine().toLowerCase()) {
                case "a":
                    System.out.println("Name of expense:");
                    newName = scanner.nextLine();
                    System.out.println("Value of expense:");
                    newValue = scanner.nextFloat();
                    scanner.nextLine();
                    Expense newExpense = new Expense(newName, newValue); // creation of expense
                    Expenses.add(newExpense); // append to list
                    break;
                case "r":
                    n = 0;
                    for (Object i : Expenses) {
                        System.out.println(n + "# " + i.toString());
                        n++;
                    }
                    try {
                        System.out.println("Please use index number to select item for deletion.");

                        int m = scanner.nextInt();
                        if (Expenses.get(m) != null) {
                            Expenses.remove(m);
                            break;
                        }
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.err.println("Index does not exist");
                        break;
                            }
                    scanner.nextLine();

                case "+":
                    System.out.println("Name of income:");
                    newName = scanner.nextLine();
                    System.out.println("Value of income:");
                    newValue = scanner.nextFloat();
                    scanner.nextLine();
                    Income newIncome = new Income(newName, newValue);
                    IncomeList.add(newIncome); // add to list
                    break;

                case "-":
                    n = 0;
                    for (Income i : IncomeList) {
                        System.out.println(n + "# " + i.toString());
                        n++;
                    }
                    try {
                        System.out.println("Please use index number to select item for deletion.");

                        int m = scanner.nextInt();
                        if (IncomeList.get(m) != null) {
                            IncomeList.remove(m);
                            break;
                        }
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.err.println("Index does not exist");
                        break;
                    }


                case "q":
                    System.exit(0);
                default:
            }
        }
    }
}
