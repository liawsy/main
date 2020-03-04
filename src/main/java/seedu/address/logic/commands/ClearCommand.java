package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ExpiryDateTracker;
import seedu.address.model.Model;

/**
 * Clears the expiry date tracker.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ExpiryDateTracker has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setExpiryDateTracker(new ExpiryDateTracker());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
