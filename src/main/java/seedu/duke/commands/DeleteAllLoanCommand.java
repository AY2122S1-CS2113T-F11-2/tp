package seedu.duke.commands;

import seedu.duke.ui.TextUi;

public class DeleteAllLoanCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "Delete all loan record.\n"
            + "Parameters: l/ m/MONTH";
    public final int month;

    public DeleteAllLoanCommand(int month) {
        this.month = month;
    }

    /**
     * LocalDate.now().getMonthValue() is being used as a placeholder
     */
    @Override
    public void execute(boolean isLoadingStorage) {
        int sizeBeforeDeletion = recordList.getLoanListSize(month);
        for (int i = 1; i <= sizeBeforeDeletion; i++) {
            recordList.deleteLoan(1, month);
        }
        TextUi.showAllLoanDeletedMessage();
    }
}
