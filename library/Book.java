/**
 * Book.java
 * Represents a single library book.
 *
 * OOP concept: ENCAPSULATION
 *   - The availability status and all fields are private.
 *   - The outside world can only change availability through the
 *     controlled issueBook()/returnBook() methods, never directly.
 */
public class Book {
    private final int bookId;
    private final String title;
    private final String author;
    private boolean available;   // protected via private access + controlled methods

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;   // a new book starts on the shelf
    }

    // ---- Read-only accessors (getters) ----
    public int getBookId()      { return bookId; }
    public String getTitle()    { return title; }
    public String getAuthor()   { return author; }
    public boolean isAvailable() { return available; }

    /**
     * Marks the book as issued. Returns false if it was already out,
     * so availability can never go into an invalid state.
     */
    public boolean markIssued() {
        if (!available) {
            return false;
        }
        available = false;
        return true;
    }

    /** Marks the book as returned/available again. */
    public boolean markReturned() {
        if (available) {
            return false;   // it was never issued
        }
        available = true;
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%d] \"%s\" by %s  (%s)",
                bookId, title, author, available ? "Available" : "Issued");
    }
}
