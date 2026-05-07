package model;

import exceptions.InvalidAmountException;

public class FoodExpense extends Expense {
    // les attributs
    private String mealType;
    private boolean isRestaurant;
    private static double maxLimit = 500;

    //Constructeur
    public FoodExpense(int id, double amount, String date, String description,
                       String category, String mealType, boolean isRestaurant) {
        super(id, amount, date, description, category);
        this.mealType = mealType;
        this.isRestaurant = isRestaurant;
    }

    //Getters & Setters
    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }

    public boolean getRestaurant() { return isRestaurant; }
    public void setRestaurant(boolean isRestaurant) { this.isRestaurant = isRestaurant; }

    public static double getMaxLimit() {
        return maxLimit;
    }
    public static void setMaxLimit(double limit) {
        if (limit > 0) {
            maxLimit = limit;
            System.out.println("OK: nourriture limite a " + limit + " MAD");
        } else {
            System.out.println("Attention: limite est Invalide. maintenir " + maxLimit + " MAD");
        }
    }

    //Validation depense
    @Override
    public void validate() throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Le montant doit etre postive: " + amount);
        }

        if (mealType == null || mealType.trim().isEmpty()) {
            throw new InvalidAmountException("Veuillez specifiez le repas.");
        }

        if (amount > maxLimit) {
            throw new InvalidAmountException(
                String.format("les depenses du nourriture sont tres elves! %.2f MAD > limit of %.2f MAD", amount, maxLimit)
            );
        }
    }

    //Resume
    @Override
    public String getSummary() {
        String location = isRestaurant ? "Restaurant" : "Supermarche";
        return String.format(
            "[NOURRITURE] %s - %.2f MAD le %s (%s) - %s",
            description, amount, date, location, mealType
        );
    }

    //Serialisation - commun entre les classes
    @Override
    public String toCSV() {
        return String.format("%d,FOOD,%.2f,%s,%s,%s,%s,%b",
            id, amount, date, description, category, mealType, isRestaurant);
    }
}
