# Student Budget Tracker

Student Budget Tracker is a console-based Java application for tracking student spending, managing monthly budgets, and exporting monthly reports. The project was built as a four-member OOP assignment and combines model classes, shared contracts, business logic, persistence, and reporting.

## What It Does

- Record expenses with an amount, date, description, and category.
- Manage monthly budgets by category.
- Track current spending and raise alerts when a budget is exceeded.
- Search, filter, and sort expenses.
- Save and reload project data from CSV files.
- Export a formatted monthly report.

## Team Contributions

| Member | Contribution |
| --- | --- |
| Member 1 | Built the expense model layer: the abstract `Expense` class and the category-specific expense types with validation, summaries, and CSV serialization. |
| Member 2 | Added the shared contracts and rules: `Alertable`, `Exportable`, `Budget`, and the custom exception classes. |
| Member 3 | Implemented the business layer: `ExpenseManager`, search/filter/sort logic, budget tracking, and the `Student` model. |
| Member 4 | Added persistence and reporting with `FileManager` and `ReportExporter`. |

## Architecture

```
src/
	exceptions/
	interfaces/
	model/
	service/
	persistence/
	export/
```

### Main Classes

- `model/Expense.java` - abstract base class for all expenses.
- `model/*Expense.java` - concrete expense types for student spending categories.
- `model/Budget.java` - monthly category budget and alert handling.
- `model/Student.java` - student profile and monthly income.
- `service/ExpenseManager.java` - core CRUD, search, sort, filter, and budget management.
- `persistence/FileManager.java` - CSV load/save for expenses, budgets, and student data.
- `export/ReportExporter.java` - formatted monthly report generation.

## Object-Oriented Concepts

- Abstraction: `Expense` and the interface contracts hide implementation details.
- Inheritance: category-specific expense classes extend `Expense`.
- Polymorphism: the service layer works with the base `Expense` type.
- Interfaces: `Alertable` and `Exportable` define shared behavior.
- Collections: `List`, `Map`, `ArrayList`, and `HashMap` manage data efficiently.
- Exceptions: custom exceptions make validation and file errors explicit.

## File Format

- Expenses, budgets, and student information are serialized as CSV.
- Each model class provides `toCSV()` and matching `fromCSV()` helpers where needed.
- Dates use the `YYYY-MM-DD` format.

## UML Diagrams

- Class diagram: `diagramme_de_classe.png`
- State transition diagram: `diagramme_etat_transition.png`

## Build

```bash
javac -d out src/**/*.java
```

If you are running the console menu from your own launcher class, execute that class from the compiled output directory.
