/**
 * FacultyMember.java
 * A faculty library member.
 *
 * OOP concepts:
 *   - INHERITANCE: "is-a" Member.
 *   - POLYMORPHISM (method overriding): a different calculateFine() rule
 *     from StudentMember, proving the same call behaves differently.
 *
 * Fine policy: faculty get a grace period of a few days and a lower
 * daily rate, reflecting longer permitted loan durations.
 */
public class FacultyMember extends Member {
    private static final int    GRACE_DAYS   = 5;
    private static final double RATE_PER_DAY = 1.0;

    public FacultyMember(int memberId, String name) {
        super(memberId, name);
    }

    @Override
    public double calculateFine(int daysLate) {
        int chargeableDays = daysLate - GRACE_DAYS;
        if (chargeableDays <= 0) return 0.0;   // within grace period
        return chargeableDays * RATE_PER_DAY;
    }

    @Override
    public String getMemberType() {
        return "Faculty";
    }
}
