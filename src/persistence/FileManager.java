package persistence;

import exceptions.FileIOException;
import model.Budget;
import model.EntertainmentExpense;
import model.Expense;
import model.FoodExpense;
import model.OtherExpense;
import model.Student;
import model.TransportExpense;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {

    //Sauvegarde une liste de dépenses dans un fichier CSV.
    public void saveExpenses(List<Expense> expenses, String path) throws FileIOException {
        List<Expense> safeExpenses = expenses == null ? new ArrayList<Expense>() : expenses;
        File file = new File(path);
        ensureParentDirectory(file);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (Expense expense : safeExpenses) {
                writer.println(expense.toCSV());
            }
        } catch (IOException e) {
            throw new FileIOException("Impossible de sauvegarder les dépenses dans : " + path, e);
        }
    }

    //Charge une liste de dépenses depuis un fichier CSV.
    public List<Expense> loadExpenses(String path) throws FileIOException {
        List<Expense> expenses = new ArrayList<>();
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            return expenses;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                try {
                    String[] parts = line.split(",");
                    String type = parts[1].trim().toUpperCase();

                    switch (type) {
                        case "FOOD":
                            expenses.add(new FoodExpense(
                                    Integer.parseInt(parts[0].trim()),
                                    Double.parseDouble(parts[2].trim()),
                                    parts[3].trim(),
                                    parts[4].trim(),
                                    parts[5].trim(),
                                    parts[6].trim(),
                                    Boolean.parseBoolean(parts[7].trim())));
                            break;
                        case "TRANSPORT":
                            expenses.add(new TransportExpense(
                                    Integer.parseInt(parts[0].trim()),
                                    Double.parseDouble(parts[2].trim()),
                                    parts[3].trim(),
                                    parts[4].trim(),
                                    parts[5].trim(),
                                parts[6].trim()));
                            break;
                        case "OTHER":
                            expenses.add(new OtherExpense(
                                    Integer.parseInt(parts[0].trim()),
                                    Double.parseDouble(parts[2].trim()),
                                    parts[3].trim(),
                                    parts[4].trim(),
                                    parts[5].trim(),
                                    parts[6].trim()));
                            break;
                        case "ENTERTAINMENT":
                            expenses.add(new EntertainmentExpense(
                                    Integer.parseInt(parts[0].trim()),
                                    Double.parseDouble(parts[2].trim()),
                                    parts[3].trim(),
                                    parts[4].trim(),
                                    parts[5].trim(),
                                    parts[6].trim()));
                            break;
                        default:
                            throw new IllegalArgumentException("Type de dépense inconnu : " + type);
                    }
                } catch (RuntimeException ex) {
                    throw new FileIOException("Ligne CSV invalide dans : " + path + " -> " + line, ex);
                }
            }
        } catch (IOException e) {
            throw new FileIOException("Impossible de lire les dépenses depuis : " + path, e);
        }

        return expenses;
    }

    //Sauvegarde une carte de budgets dans un fichier CSV.
    public void saveBudgets(Map<String, Budget> budgets, String path) throws FileIOException {
        Map<String, Budget> safeBudgets = budgets == null ? new HashMap<String, Budget>() : budgets;
        File file = new File(path);
        ensureParentDirectory(file);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (Map.Entry<String, Budget> entry : safeBudgets.entrySet()) {
                writer.println(entry.getValue().toCSV());
            }
        } catch (IOException e) {
            throw new FileIOException("Impossible de sauvegarder les budgets dans : " + path, e);
        }
    }

    //Charge une carte de budgets depuis un fichier CSV.
    public Map<String, Budget> loadBudgets(String path) throws FileIOException {
        Map<String, Budget> budgets = new HashMap<>();
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            return budgets;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                try {
                    Budget budget = Budget.fromCSV(line);
                    budgets.put(budget.getCategory().toLowerCase(), budget);
                } catch (RuntimeException ex) {
                    throw new FileIOException("Ligne CSV invalide dans : " + path + " -> " + line, ex);
                }
            }
        } catch (IOException e) {
            throw new FileIOException("Impossible de lire les budgets depuis : " + path, e);
        }

        return budgets;
    }

    //Sauvegarde un étudiant dans un fichier CSV.
    public void saveStudent(Student student, String path) throws FileIOException {
        File file = new File(path);
        ensureParentDirectory(file);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            if (student != null) {
                writer.println(student.toCSV());
            }
        } catch (IOException e) {
            throw new FileIOException("Impossible de sauvegarder l’etudiant dans : " + path, e);
        }
    }

    //Charge un étudiant depuis un fichier CSV.
    public Student loadStudent(String path) throws FileIOException {
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                return null;
            }
            return Student.fromCSV(line.trim());
        } catch (IOException e) {
            throw new FileIOException("Impossible de lire l’etudiant depuis : " + path, e);
        } catch (RuntimeException ex) {
            throw new FileIOException("Ligne CSV étudiante invalide dans : " + path, ex);
        }
    }

    //Crée le dossier parent si necessaire avant l’ecriture.
    private void ensureParentDirectory(File file) {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }
}
