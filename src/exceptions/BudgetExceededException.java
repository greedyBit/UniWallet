package exceptions;

public class BudgetExceededException extends Exception {
    public BudgetExceededException(String message) { super(message); }
}