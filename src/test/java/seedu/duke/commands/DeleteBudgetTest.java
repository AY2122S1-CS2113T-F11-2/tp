package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.AllRecordList;
import seedu.duke.data.RecordList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteBudgetTest {

    @Test
    void deleteBudget_budgetAmount_0() {
        int thisMonth = LocalDate.now().getMonthValue();

        RecordList currentBudgetList = new RecordList(thisMonth);
        currentBudgetList.addBudget(08.00, thisMonth, false);
        currentBudgetList.deleteBudget();
        assertEquals(0.00, currentBudgetList.getBudget().getAmount());
    }
}
