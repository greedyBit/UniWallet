package model;

import exceptions.BudgetExceededException;
import interfaces.Alertable;

public class Budget implements Alertable {
    // les attributs
    private String category;
    private double limit;
    private String month;
    private double currentSpending;

    //Constructeur
    public Budget(String category, double limit, String month) {
        this.category = category;
        this.limit = limit;
        this.month = month;
        this.currentSpending = 0.0;
    }

    //Getters & Setters
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getLimit() { return limit; }
    public void setLimit(double limit) { this.limit = limit; }

    public double getCurrentSpending() { return currentSpending; }
    public void setCurrentSpending(double spending) { this.currentSpending = spending; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    //Exception si Budget a depasse la limite
    @Override
    public void checkAlert() throws BudgetExceededException {
        if (this.currentSpending > this.limit) {
            throw new BudgetExceededException(String.format(
                    "Le budget pour '%s' a depasse la limite par: %s",
                    category, this.currentSpending - this.limit));
        }
    }

    //Ajoute un montant au budget suivi.
    public void addSpent(double amount) {
        this.currentSpending += amount;
    }
    //Optionelle - Alias de getCurrentSpending()
    public double getSpent() {
        return currentSpending;
    }

    //Serialisation - commun entre les classes
    public String toCSV() {
        return String.format("%s,%.2f,%s,%.2f", category, limit, month, currentSpending);
    }
    public static Budget fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Ligne CSV Budget invalide : " + line);
        }

        String category = parts[0].trim();
        double limit = Double.parseDouble(parts[1].trim());
        String monthValue = parts[2].trim();
        double currentSpending = Double.parseDouble(parts[3].trim());

        String[] monthParts = monthValue.split("-");
        Budget budget;
        if (monthParts.length == 2) {
            int year = Integer.parseInt(monthParts[0].trim());
            String month = monthParts[1].trim();
            budget = new Budget(category, limit, month);
        } else {
            budget = new Budget(category, limit, monthValue);
        }

        budget.setCurrentSpending(currentSpending);
        return budget;
    }
}
