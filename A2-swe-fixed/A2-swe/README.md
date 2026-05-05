# Personal Finance Management System

A small JavaFX desktop app for tracking personal income, expenses, and budgets.
Data is persisted as JSON in the `data/` directory.

## Requirements

- JDK 21 (or newer)
- Maven 3.9+

## Project layout

```
src/main/java/com/example/finance/
├── Main.java                 # JavaFX entry point
├── controllers/              # Business logic + service locator
├── factories/                # TransactionFactory
├── models/                   # User, Transaction, Income, Expense, Budget, Category, ...
├── repositories/             # JSON-backed CRUD
├── ui/                       # JavaFX screens
└── utils/                    # JsonHandler
data/                         # Runtime JSON files (created on demand)
```

## Build and run

```bash
mvn clean compile
mvn javafx:run
```

To run the test suite:

```bash
mvn test
```

## Demo credentials

- Email: `ahmed@gmail.com`
- Password: anything (demo auth checks email only)

## Notes

- This is a coursework project. Authentication is intentionally trivial.
- Storing user data as plaintext JSON is fine for a demo, not for production.
- `Transaction` is abstract; persisting mixed `Income`/`Expense` lists with
  Gson requires a custom `RuntimeTypeAdapterFactory` if you ever need full
  round-trip deserialization. The repository layer is structured so that
  upgrade path is straightforward.
