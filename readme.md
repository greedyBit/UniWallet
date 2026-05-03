\# Student Budget Tracker



\## 📋 Description



Une application Java en ligne de commande qui permet aux étudiants de gérer leurs dépenses quotidiennes et de suivre leurs budgets.



\*\*Fonctionnalités principales :\*\*

\- Ajouter, modifier et supprimer des dépenses

\- Catégoriser les dépenses (Nourriture, Transport, Études, Loisirs)

\- Définir des budgets par catégorie

\- Alertes lorsque le budget atteint 80%

\- Rechercher des dépenses par mot-clé

\- Générer des rapports mensuels

\- Sauvegarde automatique dans des fichiers CSV



\---



\## 👥 Répartition des tâches



| Membre | Responsabilité | Fichiers |

|--------|----------------|----------|

| \*\*Membre 1\*\* | Classes abstraites et héritage | `Expense.java`, `FoodExpense.java`, `TransportExpense.java`, `AcademicExpense.java`, `EntertainmentExpense.java` |

| \*\*Membre 2\*\* | Logique métier et collections | `ExpenseManager.java` |

| \*\*Membre 3\*\* | Persistance des données | `FileManager.java` |

| \*\*Membre 4\*\* | Interface utilisateur et rapports | `Main.java`, `ReportExporter.java` |



\---



\## 🗂️ Structure du projet

src/

├── model/ ← Membre 1

│ ├── Expense.java

│ ├── FoodExpense.java

│ ├── TransportExpense.java

│ ├── AcademicExpense.java

│ └── EntertainmentExpense.java

├── service/ ← Membre 2

│ └── ExpenseManager.java

├── persistence/ ← Membre 3

│ └── FileManager.java

├── export/ ← Membre 4

│ └── ReportExporter.java

├── interfaces/

│ ├── Alertable.java

│ └── Exportable.java

├── exceptions/

│ ├── InvalidAmountException.java

│ ├── BudgetExceededException.java

│ ├── ExpenseNotFoundException.java

│ └── FileIOException.java

└── Main.java ← Membre 4



text



\---



\## 🚀 Installation et exécution



\### Prérequis

\- Java 11 ou supérieur

\- Git



\### Cloner le projet



```bash

git clone https://github.com/votre-repo/student-budget-tracker.git

cd student-budget-tracker

Compiler

bash

javac -d bin src/\*\*/\*.java src/\*.java

Exécuter

bash

java -cp bin Main

💡 Utilisation

Au lancement, un menu s'affiche :



text

========================================

&#x20;  GESTIONNAIRE DE BUDGET ÉTUDIANT

========================================



1\. Ajouter une dépense

2\. Supprimer une dépense

3\. Modifier une dépense

4\. Afficher toutes les dépenses

5\. Rechercher une dépense

6\. Générer un rapport

7\. Gérer les budgets

8\. Quitter



Votre choix : \_

Exemples

Ajouter un repas :



text

Type de dépense (1: Nourriture, 2: Transport, 3: Études, 4: Loisirs): 1

Montant: 80

Description: Pizza Margherita

Date (YYYY-MM-DD): 2026-05-03

Type de repas (Breakfast/Lunch/Dinner/Snack): Dinner

Restaurant ? (true/false): true

Générer un rapport mensuel :



text

Mois (YYYY-MM): 2026-05



=== RAPPORT MAI 2026 ===

\[Food] Pizza - 80.00 MAD

\[Transport] Bus - 8.00 MAD

Total: 88.00 MAD

📊 Règles métier

Catégorie	Règle

Nourriture	Montant maximum : 500 MAD

Transport	Taxi max 300 MAD, Essence max 600 MAD

Études	Le nom de la matière est obligatoire

Loisirs	Montant maximum : 400 MAD

Budget	Alerte à 80% d'utilisation

📁 Fichiers de données

Les données sont automatiquement sauvegardées dans le dossier data/ :



Fichier	Contenu

expenses.csv	Toutes les dépenses enregistrées

budgets.csv	Budgets par catégorie

students.csv	Informations étudiant

🔧 Branches Git

Branche	Contenu

main	Version stable et complète

member-1	Classes abstraites (Expense, sous-classes)

member-2	Logique métier (ExpenseManager)

member-3	Persistance (FileManager)

member-4	Interface et rapports (Main, ReportExporter)

📝 État d'avancement

Structure du projet



Classes abstraites (Membre 1)



Logique métier (Membre 2)



Persistance (Membre 3)



Interface utilisateur (Membre 4)



Tests et intégration



👨‍💻 Auteurs

Projet réalisé dans le cadre du cours de Programmation Orientée Objet.



📄 Licence

Ce projet est à usage éducatif uniquement.



text



\---



\## Version courte (si tu préfères)



```markdown

\# Student Budget Tracker - Gestionnaire de budget étudiant



\## Installation

```bash

git clone <URL>

cd student-budget-tracker

javac -d bin src/\*\*/\*.java src/\*.java

java -cp bin Main

Membres

Membre 1 : Classes abstraites (Expense, FoodExpense, TransportExpense, AcademicExpense, EntertainmentExpense)



Membre 2 : Logique métier (ExpenseManager)



Membre 3 : Persistance (FileManager)



Membre 4 : Interface et rapports (Main, ReportExporter)



Fonctionnalités

✅ Gestion des dépenses (CRUD)



✅ Catégories : Nourriture, Transport, Études, Loisirs



✅ Budgets par catégorie avec alertes à 80%



✅ Recherche par mot-clé



✅ Rapports mensuels



✅ Sauvegarde CSV automatique



Règles métier

Nourriture : max 500 MAD



Transport : taxi max 300 MAD, essence max 600 MAD



Études : matière obligatoire



Loisirs : max 400 MAD



Branches

main → version finale



member-1 → classes abstraites



member-2 → ExpenseManager



member-3 → FileManager



member-4 → Main et ReportExporter

