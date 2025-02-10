package spbpp;

import java.util.Scanner;

public class UserInteraction {
    private final Scanner scanner;

    public UserInteraction() {
        this.scanner = new Scanner(System.in);
    }

    // Constructor for testing purposes
    public UserInteraction(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getUserChoice(String prompt) {
        System.out.print(prompt);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void close() {
        scanner.close();
    }
}