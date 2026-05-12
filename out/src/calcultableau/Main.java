package calcultableau;

import java.io.*;
import java.util.Scanner;

/**
 * Classe principale de l'application CalculTableau.
 *
 * <p>Point d'entrée du programme. Orchestre les interactions avec l'utilisateur :
 * saisie des informations de l'enseignant, saisie des notes des étudiants,
 * affichage des statistiques et sauvegarde des résultats dans un fichier texte.</p>
 *
 * <p>Déroulement du programme :</p>
 * <ol>
 *   <li>Création de l'utilisateur {@link UtilisateurTab} avec validation de l'email</li>
 *   <li>Saisie du nombre d'étudiants (entre 1 et 50)</li>
 *   <li>Saisie des notes via {@link CalculTab}</li>
 *   <li>Affichage des résultats (notes, moyenne, médiane)</li>
 *   <li>Sauvegarde des résultats dans le fichier {@code resultats.txt}</li>
 * </ol>
 *
 * @author BUT Informatique 2A
 * @version 1.0
 * @see CalculTab
 * @see UtilisateurTab
 */
public class Main {

    /**
     * Méthode principale — point d'entrée de l'application.
     *
     * <p>Gère la saisie des données, le calcul des statistiques
     * et la sauvegarde des résultats dans le fichier {@code resultats.txt}.
     * Chaque exécution <b>ajoute</b> un nouveau rapport à la suite du fichier
     * (mode {@code append}).</p>
     *
     * <p>Contraintes de saisie :</p>
     * <ul>
     *   <li>Le nombre d'étudiants doit être compris entre 1 et 50</li>
     *   <li>L'email de l'enseignant doit respecter le format standard</li>
     * </ul>
     *
     * @param args arguments de la ligne de commande (non utilisés)
     * @throws IOException si une erreur survient lors de l'écriture dans {@code resultats.txt}
     */
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        // CREATION DE L'UTILISATEUR
        UtilisateurTab utilisateur;
        try {
            utilisateur = new UtilisateurTab("Alain", "Dupont", "alain.dupont@iut.fr");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
            return;
        }

        // CREATION DU CALCULTAB
        CalculTab calcul = new CalculTab();

        // SAISIE DU NOMBRE D'ETUDIANTS
        int n = 0;
        do {
            System.out.println("Veuillez entrer le nombre d'étudiants (1-50) :");
            n = sc.nextInt();
        } while (n <= 0 || n > 50);

        // SAISIE DES NOTES
        System.out.println("DEBUT DE SAISIE DES NOTES");
        for (int i = 0; i < n; i++) {
            System.out.println("Note de l'étudiant " + (i + 1) + " :");
            calcul.ajouterNote(sc.nextInt());
        }

        // AFFICHAGE
        System.out.println("\n" + calcul.afficherNotes());
        System.out.println(calcul.toString());

        // SAUVEGARDE DANS UN FICHIER
        FileWriter fw = new FileWriter("resultats.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("===== RAPPORT DE NOTES =====\n");
        bw.write(utilisateur.toString()); bw.newLine();
        bw.write("Nb étudiants : " + calcul.getNbEtudiants()); bw.newLine();
        bw.write(String.format("Moyenne      : %.2f", calcul.calculerMoyenne())); bw.newLine();
        bw.write(String.format("Médiane      : %.1f", calcul.calculerMediane())); bw.newLine();
        bw.write("============================\n\n");
        bw.close();

        System.out.println("\nRésultats sauvegardés dans 'resultats.txt' ✓");
        System.out.println("FIN PROGRAMME");
    }
}