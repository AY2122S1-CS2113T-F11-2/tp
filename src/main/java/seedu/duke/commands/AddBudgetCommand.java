package seedu.duke.commands;

//add EXPENDITURE_NAME COST DATE_TIME_OF_EXPENDITURE

import seedu.duke.ui.TextUi;

public class AddBudgetCommand extends AddCommand {

    public static final String MESSAGE_USAGE = ("Adds a budget record.\n"
            + "Parameters: b/ a/AMOUNT m/MONTH [y/YEAR]");

    private final double amount;
    private final int month;

    public AddBudgetCommand(double amount, int month) {
        this.amount = amount;
        this.month = month;
    }

    public void execute() {
        recordList.addBudget(amount, month);
        TextUi.showBudgetAddedMessage(amount);
    }

    public void execute(boolean isLoadingStorage) {
        recordList.addBudget(amount, month);
        TextUi.showBudgetAddedMessage(amount);
    }
}
