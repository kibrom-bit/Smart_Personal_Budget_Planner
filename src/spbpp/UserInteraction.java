package spbpp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInteraction {
    private final Scanner scanner;

    public UserInteraction() {
        this.scanner = new Scanner(System.in);
    }

    // Constructor for testing (Excellent!)
    public UserInteraction(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getUserChoice(String prompt) {
        return getIntInput(prompt); // Reuse getIntInput
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim(); // Trim for robustness
    }

    public double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double input = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void close() {
        scanner.close();
    }
}