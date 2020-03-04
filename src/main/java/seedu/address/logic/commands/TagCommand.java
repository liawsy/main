package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.tag.Tag;



/**
 * Adds more tag(s) to or clear tag(s) of item identified using its displayed index from the expiry date tracker.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Tags the item identified by the index number used in the displayed item list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "|#TAG... (if missing, item will be cleared of its tags)\n"
            + "Example: " + COMMAND_WORD + "|1|#Food";

    public static final String MESSAGE_TAG_ITEM_SUCCESS = "Tagged item: %1$s";
    public static final String MESSAGE_TAG_CLEAR_ITEM_SUCCESS = "Cleared tags of item: %1$s";

    private final Index index;
    private final TagItemDescriptor tagItemDescriptor;

    public TagCommand(Index index, TagItemDescriptor tagItemDescriptor) {
        this.index = index;
        this.tagItemDescriptor = new TagItemDescriptor(tagItemDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> lastShownList = model.getFilteredItemList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item itemToTag = lastShownList.get(index.getZeroBased());
        Item taggedItem = createTaggedItem(itemToTag, tagItemDescriptor);

        model.setItem(itemToTag, taggedItem);
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        if (this.tagItemDescriptor.isClear) {
            return new CommandResult(String.format(MESSAGE_TAG_CLEAR_ITEM_SUCCESS, taggedItem));
        }
        return new CommandResult(String.format(MESSAGE_TAG_ITEM_SUCCESS, taggedItem));

    }
    /**
     * Creates and returns a {@code Item} with the details of {@code itemToTag}
     * edited with {@code tagItemDescriptor}.
     */
    private static Item createTaggedItem(Item itemToTag, TagItemDescriptor tagItemDescriptor) {
        assert itemToTag != null;
        Set<Tag> updatedTags = updateTags(itemToTag, tagItemDescriptor);
        if (updatedTags == null) {
            return new Item(itemToTag.getName(), itemToTag.getExpiryDate());
        } else {
            return new Item(itemToTag.getName(), itemToTag.getExpiryDate(), updatedTags);
        }
    }

    /**
     * Returns an updated set of tags.
     *
     * @param itemToTag Item to be tagged.
     * @param tagItemDescriptor Descriptor that specifies additional tags to be added on or tags to be cleared.
     * @return Set containing updated tags.
     */
    private static Set<Tag> updateTags(Item itemToTag, TagItemDescriptor tagItemDescriptor) {
        if (tagItemDescriptor.isClear) {
            return null;
        }
        Set<Tag> set = new HashSet<>();
        set.addAll(itemToTag.getTags());
        set.addAll(tagItemDescriptor.getTags());
        return set;
    }

    /**
     * Stores the tags to edit the item with.
     */
    public static class TagItemDescriptor {
        private Set<Tag> tags;
        private boolean isClear = false;

        public TagItemDescriptor() {}

        public TagItemDescriptor(TagItemDescriptor toCopy) {
            if (toCopy.tags.isEmpty()) {
                isClear = true;
            }
            setTags(toCopy.tags);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        public Set<Tag>getTags() {
            return (tags != null) ? Collections.unmodifiableSet(tags) : null;
        }
    }

}
