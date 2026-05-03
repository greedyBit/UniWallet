\# 💸 Student Budget Tracker



A Java console application that helps students track daily expenses by category, set monthly budgets, receive alerts when nearing spending limits, and export monthly reports — built as a university OOP project.



\---



\## 📌 Problem Statement



Students often lose track of their spending throughout the month and overspend without realizing it until it's too late. This app provides a simple, structured way to log expenses by category, monitor budgets in real time, and review spending habits through monthly reports.



\---



\## ✨ Features



\- \*\*Register / login\*\* as a student user

\- \*\*Add, edit, and delete expenses\*\* across categories (Food, Transport, Academic, Entertainment)

\- \*\*Set monthly budgets\*\* per category

\- \*\*Real-time alerts\*\* when spending approaches or exceeds a budget limit

\- \*\*Search expenses\*\* by keyword (case-insensitive, matches title or description)

\- \*\*Sort and filter\*\* expenses by date or category

\- \*\*Export monthly reports\*\* to a formatted `.csv` file

\- \*\*Persist all data\*\* to CSV files — loaded on startup, saved on exit



\---



\## 🏗️ Project Structure



```

src/

├── model/

│   ├── Expense.java              ← abstract base class

│   ├── FoodExpense.java

│   ├── TransportExpense.java

│   ├── AcademicExpense.java

│   ├── EntertainmentExpense.java

│   ├── Budget.java               ← implements Alertable

│   └── Student.java

├── interfaces/

│   ├── Alertable.java

│   └── Exportable.java

├── exceptions/

│   ├── InvalidAmountException.java

│   ├── BudgetExceededException.java

│   ├── ExpenseNotFoundException.java

│   └── FileIOException.java

├── service/

│   └── ExpenseManager.java

├── persistence/

│   └── FileManager.java

├── export/

│   └── ReportExporter.java       ← implements Exportable

└── Main.java                     ← console menu loop

```



\---



\## 🧠 OOP Concepts Used



| Concept | Where it's applied |

|---|---|

| \*\*Abstract class\*\* | `Expense` — abstract with `getSummary()` and `validate()` |

| \*\*Inheritance \& Polymorphism\*\* | `FoodExpense`, `TransportExpense`, etc. extend `Expense` and override `getSummary()` |

| \*\*Interfaces\*\* | `Alertable` (implemented by `Budget`), `Exportable` (implemented by `ReportExporter`) |

| \*\*Custom Exceptions\*\* | `InvalidAmountException`, `BudgetExceededException`, `ExpenseNotFoundException`, `FileIOException` |

| \*\*Collections\*\* | `ArrayList<Expense>` for all expenses, `HashMap<String, Budget>` keyed by category |

| \*\*File Persistence\*\* | `FileManager` reads/writes `expenses.csv`, `budgets.csv`, `students.csv` via `BufferedReader`/`PrintWriter` |

| \*\*String Handling\*\* | `split(",")`, `trim()`, `toLowerCase()`, `contains()`, `String.format()` used in parsing, search, and report formatting |



\---



\## 📁 Data Files



| File | Contents |

|---|---|

| `expenses.csv` | `id, type, amount, date, description, category, ...` |

| `budgets.csv` | `category, limit, month` |

| `students.csv` | `id, name, email` |



All entities implement a `toCSV()` method for serialization. On startup, `FileManager.loadExpenses()` reconstructs the correct subtype from the `type` column.



\---



\## 👥 Team Split (5 members)



| Member | Responsibility |

|---|---|

| \*\*Member 1\*\* | `model/` — `Expense` (abstract), all 4 subclasses, `Student.java` |

| \*\*Member 2\*\* | `interfaces/` + `exceptions/` — `Alertable`, `Exportable`, and all 4 custom exceptions |

| \*\*Member 3\*\* | `service/ExpenseManager.java` + `Budget.java` — CRUD logic, sorting, search, budget tracking |

| \*\*Member 4\*\* | `persistence/FileManager.java` + `export/ReportExporter.java` — CSV read/write and report formatting |

| \*\*Member 5\*\* | `Main.java` — console menu loop, Scanner input, try/catch wiring, integration |



> \*\*Build order:\*\* Members 1 \& 2 start in parallel → Member 3 follows → Member 4 follows → Member 5 integrates last.



\---



\## 🤝 Team Contracts



Before writing code, agree on these shared contracts (document in `CONTRACTS.md`):



1\. \*\*CSV column order\*\* for `expenses.csv` — so `toCSV()` matches the parser

2\. \*\*Exact exception class names\*\* — so `throws` clauses match across members

3\. \*\*`ExpenseManager` method signatures\*\* — so `Main.java` calls compile correctly



\---



\## 🚀 How to Run



1\. Clone the repository

2\. Compile all `.java` files:

&#x20;  ```bash

&#x20;  javac -d out src/\*\*/\*.java src/Main.java

&#x20;  ```

3\. Run the app:

&#x20;  ```bash

&#x20;  java -cp out Main

&#x20;  ```

4\. Follow the console menu to register, add expenses, set budgets, and export reports.



> Data is automatically loaded from CSV files on startup and saved on exit.



\---



\## 📋 Requirements



\- Java 11 or higher

\- No external libraries — standard Java only



\---



\## 📄 License



This project was developed as part of a university Java OOP course. Free to use and adapt for educational purposes.

