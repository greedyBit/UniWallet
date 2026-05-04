package model;

import exceptions.BudgetExceededException;
import interfaces.Alertable;

public class Budget implements Alertable {
    private String category;
    private double limit;
    private String month;
    private double currentSpending;

    //constructeur
    public Budget(String category,double limit,String month){
        this.category=category;
        this.limit=limit;
        this.month=month;
    }

    //sets & gets
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getLimit() { return limit; }
    public void setLimit(double limit) { this.limit = limit; }

    public double getCurrentSpending() { return currentSpending; }
    public void setCurrentSpending(double spending) { this.currentSpending = spending; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    //gestion des expectations
    @Override
    public void checkAlert() throws BudgetExceededException {
        if (this.currentSpending > this.limit) {
            throw new BudgetExceededException(String.format("Le budget pour '%s' a depasse la limite par: %s", category, this.currentSpending - this.limit));
        }
    }

    //methodes personalisees
    public void addExpense(double amount) throws BudgetExceededException {
        this.currentSpending += amount;
        checkAlert();
    }
}