// Class representing the user's bank account
class BankAccount {
    private double balance;

    // Constructor to initialize account balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("\u2713 Amount deposited successfully: $" + amount);
        } else {
            System.out.println("\u26A0 Invalid deposit amount. Please try again.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("\u2713 Amount withdrawn successfully: $" + amount);
        } else if (amount > balance) {
            System.out.println("\u26A0 Insufficient balance. Transaction failed.");
        } else {
            System.out.println("\u26A0 Invalid withdrawal amount. Please try again.");
        }
    }

    // Method to check balance
    public double getBalance() {
        return balance;
    }
}

// Class representing the ATM machine
class ATM {
    private BankAccount account;

    // Constructor to initialize ATM with a user's bank account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Display menu options to the user
    public void showMenu() {
        System.out.println("Welcome to the ATM Machine");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }

    // Handle user input and actions
    public void handleInput(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Your current balance is: $" + account.getBalance());
                break;
            case 2:
                System.out.print("Enter the amount to deposit: ");
                double depositAmount = ATM.getInput();
                account.deposit(depositAmount);
                break;
            case 3:
                System.out.print("Enter the amount to withdraw: ");
                double withdrawAmount = ATM.getInput();
                account.withdraw(withdrawAmount);
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            default:
                System.out.println("\u26A0 Invalid choice. Please try again.");
        }
    }

    // Method to get user input (with error handling)
    public static double getInput() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        double amount = 0;
        try {
            amount = scanner.nextDouble();
        } catch (java.util.InputMismatchException e) {
            System.out.println("\u26A0 Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
        return amount;
    }
}

// Main class to run the program
public class ATMMachine {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        // Create a bank account with an initial balance
        BankAccount account = new BankAccount(500.0); // Initial balance is $500

        // Create an ATM instance
        ATM atm = new ATM(account);

        int choice;
        do {
            atm.showMenu();
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("\u26A0 Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
            choice = scanner.nextInt();

            atm.handleInput(choice);

        } while (choice != 4);

        scanner.close();
    }
}

