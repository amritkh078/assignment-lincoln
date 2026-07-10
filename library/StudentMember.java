/**
 * StudentMember.java
 * A student library member.
 *
 * OOP concepts:
 *   - INHERITANCE: "is-a" Member.
 *   - POLYMORPHISM (method overriding): its own calculateFine() rule.
 *
 * Fine policy: students pay a higher daily rate but the fine is capped,
 * because students borrow on a tighter schedule.
 */
public class StudentMember extends Member {
    private static final double RATE_PER_DAY = 2.0;
    private static final double MAX_FINE     = 50.0;

    public StudentMember(int memberId, String name) {
        super(memberId, name);
    }

    @Override
    public double calculateFine(int daysLate) {
        if (daysLate <= 0) return 0.0;
        double fine = daysLate * RATE_PER_DAY;
        return Math.min(fine, MAX_FINE);   // capped
    }

    @Override
    public String getMemberType() {
        return "Student";
    }
}
