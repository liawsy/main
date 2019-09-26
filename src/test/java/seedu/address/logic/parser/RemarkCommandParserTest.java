package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.person.Remark;

import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

public class RemarkCommandParserTest {

    private RemarkCommandParser parser = new RemarkCommandParser();
    private final Remark remark = new Remark("some remark");

    @Test
    public void parse_specifiedIndex_success() {

        //non empty remark
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + remark;
        RemarkCommand command = new RemarkCommand(INDEX_FIRST_PERSON, remark);
        assertParseSuccess(parser, userInput, command);

        //empty remark
        userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK;
        command = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(""));
        assertParseSuccess(parser, userInput, command);
    }

}
