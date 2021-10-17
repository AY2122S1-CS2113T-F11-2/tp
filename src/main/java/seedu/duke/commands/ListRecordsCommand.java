package seedu.duke.commands;

import seedu.duke.ui.TextUi;

public class ListRecordsCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_DESC = "Displays all records as a list with index numbers.";
    public static int month;
    public static boolean isListAll = false;

    public ListRecordsCommand(int listOption) {
        this.month = listOption;
        this.isListAll = false;
    }

    public ListRecordsCommand() {
        this.isListAll = true;
    }

    @Override
    public void execute(boolean isLoadingStorage) {
        TextUi.showRecordsListView(recordList, this.month, isListAll);
    }
}
