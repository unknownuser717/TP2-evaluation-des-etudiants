package calcultableau;

import java.io.*;
import java.util.Scanner;

public class Main {

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