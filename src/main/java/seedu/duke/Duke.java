package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.data.AllRecordList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.textfiletools.WriteToTextFile;
import seedu.duke.ui.TextUi;

import java.io.IOException;


public class Duke {
    private final TextUi textUi;
    private final Parser parser;
    private final AllRecordList recordList;
    private final String recordListDirectory = "";

    public Duke() {
        recordList = new AllRecordList();
        textUi = new TextUi();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        TextUi.showWelcomeMessage();
        boolean isExit = false;
        Storage budgetStorage = new Storage();
        budgetStorage.makeStorageTextFile(recordListDirectory);
        recordList.storageDirectory = budgetStorage.loadStorage(recordList, recordListDirectory);

        while (!isExit) {
            try {
                String userInput = textUi.getUserInput();
                Command command = parser.parseCommand(userInput);
                command.setRecordList(recordList);
                command.execute(false);
                isExit = command.isExit();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error! Your inputs are missing or incorrect!");
                TextUi.printDivider();
            }
        }
    }
}