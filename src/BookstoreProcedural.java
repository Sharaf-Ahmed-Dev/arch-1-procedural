import java.util.ArrayList;
import java.util.Scanner;

public class BookstoreProcedural {

    static ArrayList<String> inventoryTitles = new ArrayList<>();
    static ArrayList<String> inventoryAuthors = new ArrayList<>();
    static ArrayList<Double> inventoryPrices = new ArrayList<>();

    static String currentCustomerName = "";
    static ArrayList<String> purchasedTitles = new ArrayList<>();
    static ArrayList<Double> purchasedPrices = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    // Main
    public static void main(String[] args) {

        initializeInventory();

        System.out.println("======================================================================");
        System.out.println("      WELCOME TO THE BOOKSTORE MANAGEMENT SYSTEM           ");
        System.out.println("======================================================================");

        //Customer identification
        System.out.print("\nPlease enter the customer's name: ");
        currentCustomerName = scanner.nextLine().trim();

        while (currentCustomerName.isEmpty()) {
            System.out.print("Oops! Customer name is missing. Please enter it: ");
            currentCustomerName = scanner.nextLine().trim();
        }

        purchaseBooks();

        //Bill Calculation & Invoice Generation
        if (!purchasedTitles.isEmpty()) {
            //static method for bill calculation
            double grandTotal = calculateTotal();

            //static method for invoice formatting and display
            generateInvoice(grandTotal);
        } else {
            System.out.println("\n[NOTICE] No books were selected for purchase. Checkout has been cancelled.");
        }

        System.out.println("\n======================================================================");
        System.out.println("Thank you for visiting our bookstore. We wish you a great reading journey. Goodbye!");
        System.out.println("======================================================================");

        scanner.close();
    }

    static void initializeInventory() {
        // Fiction & Classics
        inventoryTitles.add("To Kill a Mockingbird");
        inventoryAuthors.add("Harper Lee");
        inventoryPrices.add(18.99);

        inventoryTitles.add("1984");
        inventoryAuthors.add("George Orwell");
        inventoryPrices.add(15.50);

        inventoryTitles.add("The Great Gatsby");
        inventoryAuthors.add("F. Scott Fitzgerald");
        inventoryPrices.add(12.75);

        inventoryTitles.add("Dune");
        inventoryAuthors.add("Frank Herbert");
        inventoryPrices.add(22.00);

        // Self-Help & Personal Development
        inventoryTitles.add("Atomic Habits");
        inventoryAuthors.add("James Clear");
        inventoryPrices.add(27.00);

        inventoryTitles.add("The Psychology of Money");
        inventoryAuthors.add("Morgan Housel");
        inventoryPrices.add(20.50);

        inventoryTitles.add("Thinking, Fast and Slow");
        inventoryAuthors.add("Daniel Kahneman");
        inventoryPrices.add(24.99);

        // History & Popular Science
        inventoryTitles.add("Sapiens: A Brief History of Humankind");
        inventoryAuthors.add("Yuval Noah Harari");
        inventoryPrices.add(28.50);

        inventoryTitles.add("Astrophysics for People in a Hurry");
        inventoryAuthors.add("Neil deGrasse Tyson");
        inventoryPrices.add(16.80);

        inventoryTitles.add("Educated");
        inventoryAuthors.add("Tara Westover");
        inventoryPrices.add(19.99);

        // Biography & Business
        inventoryTitles.add("Steve Jobs");
        inventoryAuthors.add("Walter Isaacson");
        inventoryPrices.add(32.00);

        inventoryTitles.add("Shoe Dog");
        inventoryAuthors.add("Phil Knight");
        inventoryPrices.add(21.40);

        // Software & Technology
        inventoryTitles.add("Clean Code");
        inventoryAuthors.add("Robert C. Martin");
        inventoryPrices.add(45.99);

        inventoryTitles.add("Design Patterns");
        inventoryAuthors.add("Erich Gamma");
        inventoryPrices.add(54.50);

        inventoryTitles.add("Head First Java");
        inventoryAuthors.add("Kathy Sierra");
        inventoryPrices.add(42.80);
    }


     // Displays all available books in the inventory in a formatted table.
    static void displayBooks() {
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("                        AVAILABLE BOOK COLLECTION                      ");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-5s | %-32s | %-20s | %-8s\n", "ID", "Book Title", "Author", "Price ($)");
        System.out.println("----------------------------------------------------------------------");

        for (int i = 0; i < inventoryTitles.size(); i++) {
            System.out.printf("%-5d | %-32s | %-20s | $%-7.2f\n",
                    (i + 1),
                    inventoryTitles.get(i),
                    inventoryAuthors.get(i),
                    inventoryPrices.get(i));
        }
        System.out.println("----------------------------------------------------------------------");
    }

    static void purchaseBooks() {
        boolean keepShopping = true;

        while (keepShopping) {
            displayBooks();
            System.out.print("\nEnter the Book ID you wish to purchase (1 - " + inventoryTitles.size()
                    + ") or enter '0' when your book selection is complete: ");

            if (scanner.hasNextInt()) {
                int selection = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                if (selection == 0) {
                    keepShopping = false;
                } else if (selection > 0 && selection <= inventoryTitles.size()) {
                    int index = selection - 1;


                    String selectedTitle = inventoryTitles.get(index);
                    double selectedPrice = inventoryPrices.get(index);

                    purchasedTitles.add(selectedTitle);
                    purchasedPrices.add(selectedPrice);

                    System.out.println("--> SUCCESS: Added \"" + selectedTitle + "\" ($"
                            + String.format("%.2f", selectedPrice) + ") to cart.");
                } else {
                    System.out.println("[ERROR] Invalid selection! Please enter a number between 1 and "
                            + inventoryTitles.size() + ".");
                }
            } else {
                System.out.println("[ERROR] Book selection failed. Enter a valid ID from the available list.");
                scanner.nextLine(); // Clear invalid token buffer
            }
        }
    }

    static double calculateTotal() {
        double total = 0.0;
        for (double price : purchasedPrices) {
            total += price;
        }
        return total;
    }

    static void generateInvoice(double grandTotal) {
        System.out.println("\n======================================================================");
        System.out.println("                        BOOKSTORE TRANSACTION INVOICE                    ");
        System.out.println("======================================================================");
        System.out.println(" Customer Name : " + currentCustomerName);
        System.out.println(" Transaction ID: BK-TXN-" + (1000 + (int) (Math.random() * 9000)));
        System.out.println("----------------------------------------------------------------------");
        System.out.println(" PURCHASED ITEMS:");
        System.out.printf(" %-5s | %-45s | %-8s\n", "#", "Book Title", "Price ($)");
        System.out.println("----------------------------------------------------------------------");

        for (int i = 0; i < purchasedTitles.size(); i++) {
            System.out.printf(" %-5d | %-45s | $%-7.2f\n",
                    (i + 1),
                    purchasedTitles.get(i),
                    purchasedPrices.get(i));
        }

        System.out.println("----------------------------------------------------------------------");
        System.out.printf(" TOTAL PURCHASE AMOUNT:                                    $%.2f\n", grandTotal);
        System.out.println("======================================================================");
    }
}