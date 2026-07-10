import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * LibraryApp.java
 * Menu-driven entry point for the Library Management System.
 *
 * Demonstrates all five classes working together:
 *   Library (composition) owns Book objects and Member objects,
 *   where each Member is really a StudentMember or FacultyMember
 *   (inheritance), and returning a book triggers the correct
 *   calculateFine() at runtime (polymorphism).
 */
public class LibraryApp {

    private static final Scanner in = new Scanner(System.in);
    private static final Library library = new Library("City Central Library");
    private static int nextBookId   = 1;
    private static int nextMemberId = 1;

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println(" Welcome to " + library.getName());
        System.out.println("==============================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addBook();
                case 2 -> addMember();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> library.listBooks();
                case 6 -> library.listMembers();
                case 0 -> { running = false; System.out.println("\nGoodbye!"); }
                default -> System.out.println("  ! Invalid choice, try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n---------------- MENU ----------------");
        System.out.println(" 1. Add Book");
        System.out.println(" 2. Add Member (Student / Faculty)");
        System.out.println(" 3. Issue Book");
        System.out.println(" 4. Return Book");
        System.out.println(" 5. List Books");
        System.out.println(" 6. List Members");
        System.out.println(" 0. Exit");
        System.out.println("--------------------------------------");
    }

    private static void addBook() {
        String title  = readLine("Book title : ");
        String author = readLine("Author     : ");
        library.addBook(new Book(nextBookId++, title, author));
    }

    private static void addMember() {
        String name = readLine("Member name : ");
        String type = readLine("Type (S=Student, F=Faculty): ").trim().toUpperCase();
        Member member;
        if (type.startsWith("F")) {
            member = new FacultyMember(nextMemberId++, name);
        } else {
            member = new StudentMember(nextMemberId++, name);
        }
        library.addMember(member);
    }

    private static void issueBook() {
        int bookId   = readInt("Book id   : ");
        int memberId = readInt("Member id : ");
        library.issueBook(bookId, memberId);
    }

    private static void returnBook() {
        int bookId   = readInt("Book id   : ");
        int memberId = readInt("Member id : ");
        int daysLate = readInt("Days late : ");
        library.returnBook(bookId, memberId, daysLate);
    }

    // ---------------- input helpers ----------------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = in.nextInt();
                in.nextLine();   // consume newline
                return value;
            } catch (InputMismatchException e) {
                in.nextLine();
                System.out.println("  ! Please enter a whole number.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return in.nextLine();
    }
}
