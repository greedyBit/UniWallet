package interfaces;

import exceptions.BudgetExceededException;

public interface Alertable {
    void checkAlert() throws BudgetExceededException;
}