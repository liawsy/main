package seedu.address.model.item;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Item}'s {@code ExpiryDate} falls within the date given.
 */
public class ExpiringSoonPredicate implements Predicate<Item> {

    private String checkDate;

    public ExpiringSoonPredicate(String checkDate) {
        this.checkDate = checkDate;
    }

    private LocalDate toDate() {
        LocalDate current = LocalDate.now();
        return current.plusDays(Long.parseLong(checkDate));
    }

    @Override
    public boolean test(Item item) {
        return item.isExpiring(this.toDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpiringSoonPredicate // instanceof handles nulls
                && checkDate.equals(((ExpiringSoonPredicate) other).checkDate)); // state check
    }
}
