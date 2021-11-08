package seedu.budgettracker.logic.commands;

import seedu.budgettracker.ui.TextUi;

public class DeleteAllLoanCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete all loan records.\n"
            + "Parameters: -l m/MONTH\n";
    public final int month;

    public DeleteAllLoanCommand(int month) {
        this.month = month;
    }

    @Override
    public void execute() {
        int sizeBeforeDeletion = allRecordList.getLoanListSize(month);
        for (int i = 1; i <= sizeBeforeDeletion; i++) {
            allRecordList.deleteLoan(1, month);
        }
        TextUi.showAllLoanDeletedMessage();
    }
}
