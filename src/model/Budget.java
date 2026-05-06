package model;

import exceptions.BudgetExceededException;
import interfaces.Alertable;

/**
 * Représente un budget mensuel par catégorie.
 * Concept : encapsulation d’un état métier simple derrière des accesseurs.
 */
public class Budget implements Alertable {
    private String category;
    private double limit;
    private String month;
    private double currentSpending;

    /**
     * Construit un budget à partir du mois et de l’année.
     * Concept : constructeur orienté objet avec formatage de chaîne Java.
     */
    public Budget(String category, double limit, int month, int year) {
        this.category = category;
        this.limit = limit;
        this.month = String.format("%04d-%02d", year, month);
        this.currentSpending = 0.0;
    }

    /**
     * Construit un budget à partir d’un mois déjà formaté.
     * Concept : surcharge de constructeur pour compatibilité ascendante.
     */
    public Budget(String category, double limit, String month) {
        this.category = category;
        this.limit = limit;
        this.month = month;
        this.currentSpending = 0.0;
    }

    /**
     * Retourne la catégorie du budget.
     * Concept : encapsulation via accesseur.
     */
    public String getCategory() { return category; }

    /**
     * Modifie la catégorie du budget.
     * Concept : encapsulation via mutateur.
     */
    public void setCategory(String category) { this.category = category; }

    /**
     * Retourne la limite maximale autorisée.
     * Concept : encapsulation via accesseur.
     */
    public double getLimit() { return limit; }

    /**
     * Modifie la limite du budget.
     * Concept : encapsulation via mutateur.
     */
    public void setLimit(double limit) { this.limit = limit; }

    /**
     * Retourne le montant déjà dépensé.
     * Concept : accesseur métier pour compatibilité avec Member 3.
     */
    public double getCurrentSpending() { return currentSpending; }

    /**
     * Modifie le montant déjà dépensé.
     * Concept : mutateur de l’état interne.
     */
    public void setCurrentSpending(double spending) { this.currentSpending = spending; }

    /**
     * Retourne le mois du budget au format YYYY-MM.
     * Concept : encapsulation d’une donnée temporelle sous forme de chaîne.
     */
    public String getMonth() { return month; }

    /**
     * Modifie le mois du budget.
     * Concept : mutateur simple pour la sérialisation CSV.
     */
    public void setMonth(String month) { this.month = month; }

    /**
     * Vérifie si le budget a dépassé sa limite.
     * Concept : implémentation d’un contrat d’interface avec exception contrôlée.
     */
    @Override
    public void checkAlert() throws BudgetExceededException {
        if (this.currentSpending > this.limit) {
            throw new BudgetExceededException(String.format(
                    "Le budget pour '%s' a depasse la limite par: %s",
                    category, this.currentSpending - this.limit));
        }
    }

    /**
     * Ajoute un montant au budget suivi.
     * Concept : méthode métier utilisée par Member 3 via le nom addSpent().
     */
    public void addSpent(double amount) {
        this.currentSpending += amount;
    }

    /**
     * Alias de compatibilité pour l’ancienne nomenclature de Member 2.
     * Concept : adaptation d’API sans casser le code existant.
     */
    public void addExpense(double amount) throws BudgetExceededException {
        addSpent(amount);
        checkAlert();
    }

    /**
     * Alias de compatibilité pour le solde courant du budget.
     * Concept : redondance contrôlée pour interopérabilité entre membres.
     */
    public double getSpent() {
        return currentSpending;
    }

    /**
     * Sérialise le budget en ligne CSV.
     * Concept : conversion objet→texte pour la persistance.
     */
    public String toCSV() {
        return String.format("%s,%.2f,%s,%.2f", category, limit, month, currentSpending);
    }

    /**
     * Reconstruit un budget à partir d’une ligne CSV.
     * Concept : méthode fabrique statique pour la désérialisation.
     */
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
            int month = Integer.parseInt(monthParts[1].trim());
            budget = new Budget(category, limit, month, year);
        } else {
            budget = new Budget(category, limit, monthValue);
        }

        budget.setCurrentSpending(currentSpending);
        return budget;
    }
}