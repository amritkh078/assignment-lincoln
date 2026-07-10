import java.util.ArrayList;
import java.util.List;

/**
 * Library.java
 * The aggregate that owns and coordinates books and members.
 *
 * OOP concepts:
 *   - COMPOSITION / AGGREGATION: a Library "has-a" collection of Books
 *     and Members, and a record of active loans.
 *   - ENCAPSULATION: the internal lists are private; all access goes
 *     through public methods that keep the data consistent.
 *   - POLYMORPHISM: returnBook() calls member.calculateFine() without
 *     knowing (or caring) whether it is a Student or Faculty member.
 */
public class Library {
    private final String name;
    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Loan> loans = new ArrayList<>();

    public Library(String name) {
        this.name = name;
    }

    /** Small private helper record linking a book to the member holding it. */
    private static class Loan {
        final int bookId;
        final int memberId;
        Loan(int bookId, int memberId) {
            this.bookId = bookId;
            this.memberId = memberId;
        }
    }

    // ---------------- Book / Member management ----------------

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added book:   " + book);
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Added member: " + member);
    }

    public Book findBook(int bookId) {
        for (Book b : books) if (b.getBookId() == bookId) return b;
        return null;
    }

    public Member findMember(int memberId) {
        for (Member m : members) if (m.getMemberId() == memberId) return m;
        return null;
    }

    public List<Book> getBooks()     { return List.copyOf(books); }
    public List<Member> getMembers() { return List.copyOf(members); }

    // ---------------- Transactions ----------------

    /** Issue a book to a member. Returns true on success. */
    public boolean issueBook(int bookId, int memberId) {
        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null)   { System.out.println("  ! No book with id "   + bookId);   return false; }
        if (member == null) { System.out.println("  ! No member with id " + memberId); return false; }

        if (!book.markIssued()) {
            System.out.println("  ! Book already issued: " + book.getTitle());
            return false;
        }
        loans.add(new Loan(bookId, memberId));
        System.out.printf("  > Issued \"%s\" to %s%n", book.getTitle(), member.getName());
        return true;
    }

    /**
     * Return a book. Uses polymorphism: the fine is computed by the
     * member's own calculateFine() implementation.
     * @return the fine charged (0.0 if on time / not found)
     */
    public double returnBook(int bookId, int memberId, int daysLate) {
        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null || member == null) {
            System.out.println("  ! Invalid book or member id");
            return 0.0;
        }

        Loan match = null;
        for (Loan l : loans) {
            if (l.bookId == bookId && l.memberId == memberId) { match = l; break; }
        }
        if (match == null) {
            System.out.println("  ! No active loan of this book by this member");
            return 0.0;
        }

        book.markReturned();
        loans.remove(match);

        double fine = member.calculateFine(daysLate);   // <-- polymorphic call
        System.out.printf("  < %s returned \"%s\" (%d day(s) late) -> fine = %.2f%n",
                member.getName(), book.getTitle(), Math.max(daysLate, 0), fine);
        return fine;
    }

    // ---------------- Reporting ----------------

    public void listBooks() {
        System.out.println("\n--- Books in " + name + " ---");
        if (books.isEmpty()) { System.out.println("  (none)"); return; }
        for (Book b : books) System.out.println("  " + b);
    }

    public void listMembers() {
        System.out.println("\n--- Members of " + name + " ---");
        if (members.isEmpty()) { System.out.println("  (none)"); return; }
        for (Member m : members) System.out.println("  " + m);
    }

    public String getName() { return name; }
}
