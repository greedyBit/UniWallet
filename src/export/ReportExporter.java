package export;

import exceptions.FileIOException;
import interfaces.Exportable;
import model.Expense;
import service.ExpenseManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Génère des rapports mensuels à partir du gestionnaire de dépenses.
 * Concept : composition, car le service réutilise ExpenseManager sans l’hériter.
 */
public class ReportExporter implements Exportable {

    private final ExpenseManager manager;

    /**
     * Injecte le gestionnaire de dépenses utilisé pour construire les rapports.
     * Concept : injection de dépendance via constructeur.
     */
    public ReportExporter(ExpenseManager manager) {
        this.manager = manager;
    }

    /**
     * Exporte toutes les dépenses du gestionnaire dans un fichier texte formaté.
     * Concept : implémentation d’une interface métier d’export.
     */
    @Override
    public void exportToFile(String path) throws FileIOException {
        File file = new File(path);
        ensureParentDirectory(file);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            writeReport(manager.getAllExpenses(), writer);
        } catch (IOException e) {
            throw new FileIOException("Impossible d’exporter le rapport vers : " + path, e);
        }
    }

    /**
     * Formate une dépense sur une ligne alignée.
     * Concept : String.format() pour un affichage tabulaire lisible.
     */
    public String formatRow(Expense e) {
        return String.format("%-5d %-12s %-15s %-25s %10.2f MAD",
                e.getId(), e.getDate(), e.getCategory(), e.getDescription(), e.getAmount());
    }

    /**
     * Génère un rapport limité à un mois précis dans un fichier dédié.
     * Concept : filtrage métier avant sérialisation du résultat.
     */
    public void generateMonthlyReport(int month, int year, String path) throws FileIOException {
        File file = new File(path);
        ensureParentDirectory(file);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            writeReport(manager.filterByMonth(month, year), writer);
        } catch (IOException e) {
            throw new FileIOException("Impossible de générer le rapport mensuel vers : " + path, e);
        }
    }

    /**
     * Affiche un rapport avec la même mise en page que l’export fichier.
     * Concept : réutilisation du même format pour la sortie console.
     */
    public void printToConsole(List<Expense> list) {
        PrintWriter writer = new PrintWriter(System.out);
        writeReport(list, writer);
        writer.flush();
    }

    /**
     * Écrit le corps du rapport avec en-tête, lignes et total.
     * Concept : factorisation d’un algorithme de rendu commun.
     */
    private void writeReport(List<Expense> expenses, PrintWriter writer) {
        List<Expense> safeExpenses = expenses == null ? new ArrayList<Expense>() : expenses;
        double total = 0.0;
        String separator = "--------------------------------------------------------------------------------";

        writer.println("===== RAPPORT MENSUEL =====");
        writer.println(String.format("%-5s %-12s %-15s %-25s %10s",
                "ID", "DATE", "CATEGORIE", "DESCRIPTION", "MONTANT"));
        writer.println(separator);

        for (Expense expense : safeExpenses) {
            writer.println(formatRow(expense));
            total += expense.getAmount();
        }

        writer.println(separator);
        writer.println(String.format("TOTAL: %.2f MAD", total));
    }

    /**
     * Crée le dossier parent si l’utilisateur cible un chemin imbriqué.
     * Concept : utilitaire I/O partagé par les méthodes d’export.
     */
    private void ensureParentDirectory(File file) {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }
}