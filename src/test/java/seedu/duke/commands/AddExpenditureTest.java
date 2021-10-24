package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.AllRecordList;
import seedu.duke.data.RecordList;
import seedu.duke.data.records.Category;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddExpenditureTest {

    @Test
    void addBudget_rawCommand_expenditureList_sizeOf1() {
        String description = "JanuaryExpenditure";
        double spending = 20.00;
        LocalDate date = LocalDate.of(2021, 10, 8);
        int month = date.getMonthValue();
        Category category = Category.GENERAL;

        RecordList currentExpenditureList = new RecordList(month);
        currentExpenditureList.addExpenditure(description, spending, date, category, false);
        assertEquals(1, currentExpenditureList.getExpenditureListSize());
    }
}
