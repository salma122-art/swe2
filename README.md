# swe2
Personal Finance Management System

Desktop application developed using JavaFX and Object-Oriented Programming (OOP).

Team Information
Course: CS251 – Intro to Software Engineering
Project: Personal Finance Management System
Technology: Java + JavaFX
Architecture: MVC Architecture
Persistence: JSON File Storage
Project Description

This project is a desktop finance management system that allows users to:

Register and Login
Add Income and Expenses
Manage Transactions
Edit/Delete Transactions
Track Budgets
Generate Reports
Search and Filter Transactions
Export Reports to TXT/JSON
Receive Notifications and Budget Alerts
Use Dark Mode UI

The system follows OOP principles and persists all data using JSON files.

When the application starts again, all previously saved data is loaded automatically.

Technologies Used
Programming Language
Java 17
UI Framework
JavaFX
Storage
JSON Files
IDE

Recommended IDEs:

IntelliJ IDEA
VS Code
Eclipse
Java Version

Required:

Java 17 or higher

Check installed version:

java --version
JavaFX Setup

The project uses JavaFX.

Required JavaFX SDK:

JavaFX SDK 17+
OOP Concepts Used

The project applies Object-Oriented Programming concepts:

Encapsulation

Used in model classes through private fields and getters/setters.

Example:

private double amount;

public double getAmount() {
    return amount;
}
Inheritance

Income and Expense inherit from Transaction.

Example:

public class Income extends Transaction
public class Expense extends Transaction
Polymorphism

Used when handling transactions through parent class references.

Example:

List<Transaction> transactions

Objects may be:

Income
Expense
Abstraction

Controllers and services separate logic from UI.

Examples:

FinanceController
ReportService
BudgetService
MVC Architecture

The system follows MVC Architecture.

Model

Contains business data.

Examples:

User
Transaction
Budget
View

JavaFX UI screens.

Examples:

LoginScreen
DashboardScreen
ReportScreen
Controller

Handles application logic and communication.

Examples:

FinanceController
AuthController
How Data Persistence Works

The system saves data into JSON files inside:

data/

Examples:

transactions_history.json
user_profile.json

When the application starts:

JSON files are loaded
Data is restored automatically

Handled بواسطة:

JsonHandler.java
How Screens Are Connected
Login Flow
LoginScreen
    ↓
DashboardScreen
Register Flow
RegisterScreen
    ↓
LoginScreen
Add Transaction Flow
DashboardScreen
    ↓
AddTransactionScreen
    ↓
FinanceController
    ↓
JsonHandler
How Controllers Are Connected

AppContext stores shared controllers globally.

Example:

AppContext.financeController

This allows all screens to access the same data.

Search and Filter Feature

Implemented in Dashboard.

Allows searching transactions by:

Transaction ID
Type:
Income
Expense
Export Feature

Reports can be exported as:

TXT
JSON

Used for saving financial reports externally.

Dark Mode

Dark mode button changes application theme using CSS.

Example:

scene.getStylesheets().add(...)
Toast Notifications

Custom popup notifications replace normal alerts.

Examples:

Success Notification
Error Notification
Budget Warning Notification

Implemented inside:

NotificationService.java
Budget Alerts

When expense exceeds budget limit:

Warning popup appears automatically

Handled by:

BudgetService.java
NotificationService.java
FinanceController.java
How To Run The Project
Step 1

Open project in IDE.

Step 2

Add JavaFX SDK to project libraries.

Step 3

Set VM Options:

--module-path "PATH_TO_JAVAFX/lib" --add-modules javafx.controls,javafx.fxml

Replace:

PATH_TO_JAVAFX

with your JavaFX SDK location.

Example:

C:\javafx-sdk-21\lib
Step 4

Run:

Main.java
Generating JavaDoc Documentation

The project uses JavaDoc comments.

Example:

/**
 * Add transaction to the system.
 */
public boolean addTransaction(...)
Generate JavaDoc in IntelliJ
Steps
Open:
Tools
→ Generate JavaDoc
Choose output folder:
docs/
Press Generate
Generated Files

JavaDoc HTML pages are generated inside:

docs/

Examples:

index.html
allclasses-index.html

Open:

index.html

inside browser to view documentation.

GitHub Usage

The project uses GitHub for version control.

Each team member should:

Create commits regularly
Push changes frequently
Use meaningful commit messages

Examples:

Added budget alerts
Implemented report export
Fixed login validation
Unit Tests

Optional but recommended.

Suggested tests:

FinanceControllerTest

Tests:

Add transaction
Delete transaction
Update transaction
BudgetServiceTest

Tests:

Budget exceeded
Remaining amount
Budget updates
Extra Features (Bonus)

Implemented bonus features:

✔ Dark Mode
✔ Search & Filter
✔ Export TXT/JSON
✔ Toast Notifications
✔ Budget Alerts
✔ Confirmation Before Delete

Common Errors
JavaFX Runtime Missing

Fix:

Add JavaFX SDK
Add VM options
JSON Files Not Loading

Ensure:

data/

folder exists.

Notes
No .jar file included as requested.
All data is stored locally using JSON.
Application fully supports persistent storage.
End of README
Download JavaFX:

JavaFX Official Website
