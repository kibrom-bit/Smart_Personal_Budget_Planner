package spbpp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static  UserInteraction userInteraction = new UserInteraction();
    private static String loggedInUser ;
    private static String selectedYear, selectedMonth;
    public static UserManager userManager = new UserManager();
    public static final BudgetManager budgetManager = new BudgetManager();
    public static final ExpenseManager expenseManager = new ExpenseManager();
    public static final SavingsManager savingsManager = new SavingsManager();

 public Main(UserManager userManager, UserInteraction userInteraction) {
    this.userManager = userManager;
    this.userInteraction = userInteraction;
}
   public static void main(String[] args) {
        while (true) {
            userInteraction.print("\n1. Login\n2. Create Account\n3. Exit");
            int choice = userInteraction.getUserChoice("Choose an option: ");
            switch (choice) {
                case 1 -> login();
                case 2 -> createAccount();
                case 3 -> {
                    userInteraction.print("Exiting...");
                    return;
                }
                default -> userInteraction.print("Invalid choice!");
            }
        }
    }

    public static boolean login() {
        String username = userInteraction.getUserInput("Enter username: ");
        String password = userInteraction.getUserInput("Enter password: ");

        if (userManager.authenticate(username, password)) {
            loggedInUser  = username;
            userInteraction.print("Login successful!");
            selectYearMonth();
            return true;
        } else {
            userInteraction.print("Invalid username or password.");
            return false;
        }
    }

    public static void createAccount() {
        String username = userInteraction.getUserInput("Enter new username: ");
        String password = userInteraction.getUserInput("Enter new password: ");
        
        try {
            userManager.createAccount(username, password);
            userInteraction.print("Account created successfully!");
        } catch (IllegalArgumentException e) {
            userInteraction.print("Error: " + e.getMessage());
        }
    }

    public static void selectYearMonth() {
        while (true) {
            try {
                System.out.print("Enter year (e.g., 2024): ");
                selectedYear = scanner.nextLine().trim();
                System.out.print("Enter month (e.g., January): ");
                selectedMonth = scanner.nextLine().trim().toLowerCase();
                
                if (isValidYear(selectedYear) && isValidMonth(selectedMonth)) {
                    break;
                }
                System.out.println("Invalid input! Please try again.");
            } catch (Exception e) {
                System.out.println("Error processing input: " + e.getMessage());
            }
        }

        budgetManager.loadBudget(selectedYear, selectedMonth);
        savingsManager.loadSavingsGoal(selectedYear, selectedMonth);
        dashboard();
    }

    private static boolean isValidYear(String year) {
        return year.matches("20\\d{2}");
    }

    private static boolean isValidMonth(String month) {
        return List.of(
            "january", "february", "march", "april", "may", "june",
            "july", "august", "september", "october", "november", "december"
        ).contains(month);
    }

    public static void dashboard() {
        while (true) {
            System.out.println("\n=========== Dashboard ===========");
            System.out.println("Month: " + getMonthName(selectedMonth));
            System.out.println("Year: " + selectedYear);
            System.out.println("Budget: $" + budgetManager.getMonthlyBudget());
            System.out.println("Total Expenses: $" + expenseManager.getTotalExpenses(selectedYear, selectedMonth));
            System.out.println("Savings Goal: $" + savingsManager.getSavingsGoal(selectedYear, selectedMonth));
            System.out.println("\nAvailable Options:");
            System.out.println("1. Add Expense");
            System.out.println("2. Edit Expense");
            System.out.println("3. View Expenses");
            System.out.println("4. Set Budget");
            System.out.println("5. Set Savings Goal");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addExpense();
                    case 2 -> editExpense();
                    case 3 -> viewExpenses();
                    case 4 -> setBudget();
                    case 5 -> setSavingsGoal();
                    case 6 -> {
                        System.out.println("Logging out...");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please select 1-6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }
     public static void addExpense() {
        if (budgetManager.monthlyBudget == 0) {
            System.out.println("You must set a budget first!");
            return;
        }

        try {
            System.out.print("Enter expense amount: ");
            int amount = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter category: ");
            String category = scanner.nextLine().trim().toLowerCase();

            System.out.print("Enter date: ");
            String dateStr = scanner.nextLine().trim();

            LocalDate date = DateUtils.parseDate(dateStr); // Try parsing the date

            String expenseData = date + "," + amount + "," + category;
            expenseManager.addExpense(selectedYear, selectedMonth, expenseData);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount! Please enter numbers only.");
            scanner.nextLine();
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse date. Please enter a valid date.");
        }
    }

    public static void editExpense() {
        List<String> expenses = expenseManager.loadExpenses(selectedYear, selectedMonth);
        if (expenses.isEmpty()) {
            System.out.println("No expenses to edit!");
            return;
        }

        System.out.println("\nSelect expense to edit:");
        for (int i = 0; i < expenses.size(); i++) {
            String[] parts = expenses.get(i).split(",");
            System.out.printf("%d. %s - %s - $%s\n",
                    i + 1, parts[0], parts[2], parts[1]);
        }

        try {
            System.out.print("Enter expense number to edit: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();

            if (index < 0 || index >= expenses.size()) {
                throw new IndexOutOfBoundsException();
            }

            String oldExpense = expenses.get(index);
            System.out.println("Editing expense: " + oldExpense);

            System.out.print("New amount (press enter to keep current): ");
            String amountStr = scanner.nextLine().trim();
            int amount = amountStr.isEmpty() ? Integer.parseInt(oldExpense.split(",")[1]) : Integer.parseInt(amountStr);

            System.out.print("New category (press enter to keep current): ");
            String category = scanner.nextLine().trim().toLowerCase();
            if (category.isEmpty()) category = oldExpense.split(",")[2];

            System.out.print("New date (press enter to keep current): ");
            String dateStr = scanner.nextLine().trim();
            if (dateStr.isEmpty()) dateStr = oldExpense.split(",")[0];
            else dateStr = DateUtils.parseDate(dateStr).toString(); // Parse the new date

            String newExpenseData = dateStr + "," + amount + "," + category;
            expenseManager.editExpense(selectedYear, selectedMonth, expenses, index, newExpenseData);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Invalid input! Please enter valid numbers.");
            scanner.nextLine();
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse date. Please enter a valid date.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid expense number!");
        }
    }

    public static void viewExpenses() {
        List<String> expenses = expenseManager.loadExpenses(selectedYear, selectedMonth);
        System.out.println("\n==== Expenses ====");
        for (String expense : expenses) {
            String[] parts = expense.split(",");
            System.out.printf("Date: %s, Amount: $%s, Category: %s\n", parts[0], parts[1], parts[2]);
        }
    }

    public static void setBudget() {
        try {
            System.out.print("Enter monthly budget amount: ");
            int budget = scanner.nextInt();
            scanner.nextLine();
            budgetManager.setBudget(selectedYear, selectedMonth, budget);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount! Please enter numbers only.");
            scanner.nextLine();
        }
    }

    public static void setSavingsGoal() {
        try {
            System.out.print("Enter savings goal amount: ");
            int goal = scanner.nextInt();
            scanner.nextLine();
            savingsManager.setSavingsGoal(selectedYear, selectedMonth, goal);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount! Please enter numbers only.");
            scanner.nextLine();
        }
    }

    // Remaining helper methods (addExpense, editExpense, viewExpenses, etc.)
    // ... [Previous implementation remains mostly unchanged] ...
    
    private static String getMonthName(String month) {
        return switch (month) {
            case "january" -> "January";
            case "february" -> "February";
            case "march" -> "March";
            case "april" -> "April";
            case "may" -> "May";
            case "june" -> "June";
            case "july" -> "July";
            case "august" -> "August";
            case "september" -> "September";
            case "october" -> "October";
            case "november" -> "November";
            case "december" -> "December";
            default -> "Unknown Month";
        };
    }
      public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}