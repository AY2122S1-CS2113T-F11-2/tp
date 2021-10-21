# Developer Guide

## Table of Content
* [Acknowledgements](#acknowledgements)
* [Getting started](#getting-started)
* [Design](#design)
  * [Architecture](#architecture)
* [Implementation](#implementation)
* [Product scope](#product-scope)
  * [Target user profile](#Target-user-profile)
  * [Value proposition](#value-proposition)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossory)
* [Instructions for manual testing](#Instructions-for-manual-testing)

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

## Design 

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## Implementation
###Delete feature
The delete feature collaborates with other classes like Parser, RecordList, etc. Basically it contains two usages which are deletion of Budget and deletion of Expenditure.

When user keys delete b/ m/MONTH , The Parser class will analyse the whole command, and extract “b/” and “MONTH”. Then the class DeleteBudgetCommand will execute the deletion by using recordList.deleteBudget(MONTH).

Similarly, we have our Parser to parse the commands for deletion of expenditures:
* ```delete e/ m/MONTH``` — If the value at the position after ‘e/’ is “”, we use for loop to delete all expenditures within a month.
* ```delete e/INDEX m/MONTH``` — If the value at the position after ‘e/’ is an integer, we use DeleteSingleExpenditureCommand to delete this specific expenditure in this specific month.
* ```delete e/INDEX-INDEX m/MONTH``` — If the value at the position after ‘e/’ is a range of integers, we firstly use split[] to extract the starting and ending integers, and then we use DeleteMultipleExpenditureCommand to delete the expenditures in this range in this specific month. (also by using for loop)

<br/>

All the delete command classes (DeleteAllExpenditureCommand, DeleteSingleExpenditureCommand, DeleteMultipleExpenditureCommand, and DeleteBudgetCommand) extend from DeleteCommand class, and DeleteCommand class extends from Command class. Inside each command class, we have a constructor and an override function called execute to execute the specific deletion task assigned to them.

<br/>

Given below is an example usage scenario and how the delete feature behaves at each step.

<br/>

**Step 1**. The user launches the application for the first time.
<br/> **Step 2**. The user adds a budget and some expenditures to the current month.
<br/> **Step 3**. The user finds that the budget is a bit insufficient, thus the user wants to delete some of the expenditures.
<br/> **Step 4**. After consideration, the user decides to delete expenditure 3-5, so he keys ```delete e/3-5 m/10```
<br/> **Step 5**. The Parser starts to parse the command, it extracts starting index 3, ending index 5, and month 10. As this is a range of expenditures to be deleted, the Parser class calls DeleteMultipleExpenditureCommand to work. By using a for loop, the 3 expenditures are successfully deleted and prints out a showMultipleExpenditureDeletedMessage from TextUi class.


###Edit feature

The edit mechanism is facilitated by AllRecordList which extends from RecordList. It implements the following operations:
* ```AllRecordList#editBudget()``` — Edits a budget to the record list.
* ```AllRecordList#editExpenditure()``` — Edits an expenditure to the record list.
* ```AllRecordList#editLoan()``` —Edits a loan record to the record list.

<br/>

These operations are exposed in the ```EditBudgetCommand```, ```EditExpenditureCommand``` and ```EditLoanCommand``` classes, which extend from the ```Command``` subclass as ```EditBudget#execute()```, ```EditExpenditure#execute()``` and ```EditLoan#execute()``` respectively.

<br/>

Given below is an example usage scenario and how the ```edit``` mechanism behaves at each step.

<br/>

**Step 1**. The user launches the application for the first time. The ```AllRecordList``` will be initialized with the initial record list state.
<br/> **Step 2**. The user executes add …  to ```add``` a new record into the record list. The add command also calls Storage#saveToStorage(), causing a modified record list state to be saved into the storage file.
<br/> **Step 3**. The user now realises that there was a mistake in the record added, and decides to edit the record by executing the ```edit``` command. The edit command will call ```AllRecordList#edit…``` based on the record type.


### Listing

The list feature works with Parser, Recordlist and TextUi, and allows users to see the listing of the budget and expenditures in either a specific month or every month.

When user keys list ```m/Month```, The Parser class will analyse the whole command, extract “m/” and “MONTH”, and analyse whether MONTH is an integer or “all”. The boolean isListAll will be assigned as true if the value after “m/” is “all”. Then the class ListRecordsCommand will execute the listing by using TextUi.showRecordsListView(recordList, MONTH, isListAll)).

Similarly, we have our Parser to parse the commands for listing of expenditures
* ```list m/Month``` — If the value at the position after ‘m/’ is an integer, we use hashtable to list the budget and all expenditures of a  specific month.
* ```list m/all``` — If the value at the position after ‘m/’ is “all”, we use for loop to list all the budget and expenditures of every month.

Given below is an example usage scenario and how the list feature behaves at each step.

**Step 1**. The user launches the application for the first time.
<br/> **Step 2**. The user adds a budget and some expenditures to the current month.
<br/> **Step 3**. The user forgets what the budget and expenditures added, thus the user wants to see the budget and the list of the expenditures.
<br/> **Step 4**. After consideration, the user decides to see the budget and list of May, so he keys ```list m/5```
<br/> **Step 5**. The Parser starts to parse the command, it extracts month 5. As this is a specific month to be listed, the Parser class calls ListRecordsCommand to work. By finding May, the expenditures list and budget of May are successfully found and prints out a showRecordsListView from TextUi class.


### Storage 

The storage stores the exact add command of budget and expenditures into the text file containing 
in the current ArrayList. The reason it is implemented in this manner is that we could reuse
code that have been written for adding of budget and expenditures directly when loading from storage.

Everytime a deleted, edit or add command is called, the txt file is automatically wiped and re-written 
from the ArrayList to ensure that data is saved at every step. 

The yearly Records is stored in the form of <year>.txt. Each year contains all the monthly budget as
well as all the expenditure tied to that month.

## Product scope

### Target user profile

- has a need to manage a significant number of expenditures
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

### Value proposition

Value proposition: allow users to record and track expenses more conveniently,
and thereby encourage them to have greater control over their finances.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|student|record/add my outgoing and incoming expenses|track my expenses, see how I spent my money and I can also learn how to plan and manage my money wisely.|
|v1.0|financially handicapped|see my spendings over a period of time|won’t overspend.|
|v1.0|user|delete my spending records|remove unwanted or incorrect records.|
|v1.0|user|set a budget target|see whether my spendings exceeds my budget.|
|v1.0|financially strained student|view my budget target|know the limit to not overspend.|
|v1.0|user|delete my budget target|discard incorrect budget entries.|
|v1.2|new user|see a list of available commands so that I know how to use the APP|like a help list similar to cmd’s “help” command|
|v1.2|user|record down the money I have lent out to the correspondents|take note of who to collect money from.|
|v1.2|user|see the amount of money I have loaned out and to whom I have loaned it to |track who owe me money.|
|v2.0|analytical person|see the specifics on the statistics of when I spent too much on what exactly|make an amendment to my habits. / As a user trying to cut down on my expenses, I want to see the category(s) which I have spent the most money on in the past 3/6months so that I can cut down on those expenses.|
|v2.0|budget minded person|see the stats (mean, sd, median… of my yearly expenditure) of my overall expenditure and overall net income|know when to cut down on expenses|
|v2.0|person who always overspends|a warning on overspending|be more mindful and be reminded not to overspent my money.|
|v2.0|user|categorize my spendings|see what I spend most money on|
|v2.0|user|analyze my spendings with a chart|get my spendings components in a more direct and easier way|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
