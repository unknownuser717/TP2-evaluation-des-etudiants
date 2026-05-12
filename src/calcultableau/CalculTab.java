package calcultableau;
import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class CalculTab {
   public static void main(String[] args) throws IOException {

      // IDENTIFIANTS ENSEIGNANT
      String prenom = "Alain";
      String nom = "Dupont";
      String email = "alain.dupont@iut.fr";
      LocalDate dateExamen = LocalDate.now();

      int tab[] = new int[50];
      int i;
      int n = 0;
      int Sum = 0;

      // LA TAILLE DU TABLEAU
      Scanner sc = new Scanner(System.in);
      do {
         System.out.println("Veuillez entrer le nombre d'étudiants (max 50) :");
         n = sc.nextInt();
      } while (n <= 0 || n > 50);

      // SAISIE DES NOTES
      System.out.println("DEBUT DE SAISIE DES NOTES");
      for (i = 0; i < n; i++) {
         System.out.println("Note de l'étudiant " + (i + 1) + " :");
         tab[i] = sc.nextInt();
      }

      // AFFICHAGE DES NOTES
      System.out.println("\nLes notes saisies sont : ");
      for (i = 0; i < n; i++) {
         System.out.println("  Étudiant " + (i + 1) + " : " + tab[i]);
      }

      // CALCUL DE LA SOMME
      for (i = 0; i < n; i++) {
         Sum += tab[i];
      }

      // CALCUL DE LA MOYENNE
      double moyenne = (double) Sum / n;

      // CALCUL DE LA MEDIANE
      int[] tabTrie = Arrays.copyOf(tab, n);
      Arrays.sort(tabTrie);

      double mediane;
      if (n % 2 == 1) {
         mediane = tabTrie[n / 2];
      } else {
         mediane = (tabTrie[n / 2 - 1] + tabTrie[n / 2]) / 2.0;
      }

      // AFFICHAGE DES RESULTATS
      System.out.println("\n===== RESULTATS =====");
      System.out.println("Nombre d'étudiants : " + n);
      System.out.printf("Moyenne            : %.2f%n", moyenne);
      System.out.printf("Médiane            : %.1f%n", mediane);
      System.out.println("=====================");

      // SAUVEGARDE DANS UN FICHIER
      FileWriter fw = new FileWriter("resultats.txt", true); // true = append
      BufferedWriter bw = new BufferedWriter(fw);

      bw.write("===== RAPPORT DE NOTES =====");
      bw.newLine();
      bw.write("Prénom       : " + prenom);
      bw.newLine();
      bw.write("Nom          : " + nom);
      bw.newLine();
      bw.write("Email        : " + email);
      bw.newLine();
      bw.write("Date examen  : " + dateExamen);
      bw.newLine();
      bw.write("Nb étudiants : " + n);
      bw.newLine();
      bw.write(String.format("Moyenne      : %.2f", moyenne));
      bw.newLine();
      bw.write(String.format("Médiane      : %.1f", mediane));
      bw.newLine();
      bw.write("============================");
      bw.newLine();
      bw.newLine();

      bw.close();
      System.out.println("\nRésultats sauvegardés dans 'resultats.txt' ✓");
      System.out.println("FIN PROGRAMME");
   }
}