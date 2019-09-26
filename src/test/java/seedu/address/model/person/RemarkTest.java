package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemarkTest {

    @Test
    public void equals() {
        Remark testRemark = new Remark("test");
        // same object -> returns true
        assertTrue(testRemark.equals(testRemark));
        // same values -> returns true
        assertTrue(testRemark.equals(new Remark("test")));
        // different types -> returns false
        assertFalse(testRemark.equals(55));
        // null -> returns false
        assertFalse(testRemark.equals(null));
        // different remarks -> returns false
        assertFalse(testRemark.equals(new Remark("boo")));
    }
}
