package seedu.duke.data;

//import seedu.duke.data.records.Budget;
import seedu.duke.data.records.Budget;
import seedu.duke.data.records.Expenditure;
import seedu.duke.data.records.Record;

//import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

public class RecordList {
    public static int numberOfRecords = 0;
    private Budget budget;
    private boolean hasBudget;
    private final ArrayList<Expenditure> expenditureRecords;

    public RecordList() {
        budget = new Budget(0.00);
        hasBudget = false;
        expenditureRecords = new ArrayList<>();
    }

    public void addBudget(double spendingLimit) {
        budget.clearAmount();
        budget.setAmount(spendingLimit);
        if (!hasBudget) {
            hasBudget = true;
        }
    }

    public void addExpenditure(String description, double spending) {
        expenditureRecords.add(new Expenditure(description, spending));
        numberOfRecords += 1;
    }

    public void deleteBudget() {
        budget.clearAmount();
        hasBudget = false;
    }

    public void deleteExpenditure(int index) {
        expenditureRecords.remove(index - 1);
        numberOfRecords -= 1;
    }

    public ArrayList<Expenditure> getExpenditureRecords() {
        return expenditureRecords;
    }

    public Budget getBudget() {
        return budget;
    }

    public int getExpenditureListSize() {
        return expenditureRecords.size();
    }

    //    public RecordList getExpenditureList(int startMonth, int endMonth) {
    //        RecordList allExpenditure = null;
    //        for (Record a : allRecords) {
    //            if (a.getType().equals("Expenditure") && a.getMonth() <= endMonth && a.getMonth() >= startMonth) {
    //                allExpenditure.addExpenditure(a.getDescription(), a.getAmount(), a.getDate());
    //            }
    //        }
    //        return allExpenditure;
    //    }
    public Expenditure getExpenditure(int index) {
        return expenditureRecords.get(index);
    }


    public void printRecord(int i) {
    }

    public void addBudgetList(String description, double spendingLimit, int month) {
    }
}
