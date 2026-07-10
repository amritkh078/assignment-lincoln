/**
 * Member.java
 * Base class for every kind of library member.
 *
 * OOP concepts:
 *   - ABSTRACTION: declared abstract so no generic "Member" can exist;
 *     only concrete Student/Faculty members do.
 *   - INHERITANCE: StudentMember and FacultyMember extend this class.
 *   - POLYMORPHISM: calculateFine() is abstract, forcing each subtype
 *     to supply its own fine policy.
 *   - ENCAPSULATION: fields are private with protected getters.
 */
public abstract class Member {
    private final int memberId;
    private final String name;

    protected Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public int getMemberId() { return memberId; }
    public String getName()  { return name; }

    /**
     * Fine policy differs per member type -> implemented by subclasses.
     * @param daysLate number of days the book is overdue
     * @return the fine amount in currency units
     */
    public abstract double calculateFine(int daysLate);

    /** Human-readable member category, overridden by subclasses. */
    public abstract String getMemberType();

    @Override
    public String toString() {
        return String.format("[%d] %s (%s)", memberId, name, getMemberType());
    }
}
