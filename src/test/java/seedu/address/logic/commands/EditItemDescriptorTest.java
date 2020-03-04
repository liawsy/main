package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_KIWI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DRINK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditItemDescriptor;
import seedu.address.testutil.EditItemDescriptorBuilder;

public class EditItemDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditItemDescriptor descriptorWithSameValues = new EditItemDescriptor(DESC_KIWI);
        assertTrue(DESC_KIWI.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_KIWI.equals(DESC_KIWI));

        // null -> returns false
        assertFalse(DESC_KIWI.equals(null));

        // different types -> returns false
        assertFalse(DESC_KIWI.equals(5));

        // different values -> returns false
        assertFalse(DESC_KIWI.equals(DESC_APPLE));

        // different name -> returns false
        EditItemDescriptor editedAmy = new EditItemDescriptorBuilder(DESC_KIWI).withName(VALID_NAME_APPLE).build();
        assertFalse(DESC_KIWI.equals(editedAmy));

        // different expiry date -> returns false
        editedAmy = new EditItemDescriptorBuilder(DESC_KIWI).withExpiryDate("10/10/2019").build();
        assertFalse(DESC_KIWI.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditItemDescriptorBuilder(DESC_KIWI).withTags(VALID_TAG_DRINK).build();
        assertFalse(DESC_KIWI.equals(editedAmy));
    }
}
