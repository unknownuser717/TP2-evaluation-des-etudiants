package calcultableau;

import java.util.ArrayList;
import java.util.Collections;

public class CalculTab {

   private ArrayList<Integer> notes;

   public CalculTab() {
      this.notes = new ArrayList<>();
   }

   // Ajouter une note
   public void ajouterNote(int note) {
      notes.add(note);
   }

   // Nombre d'étudiants
   public int getNbEtudiants() {
      return notes.size();
   }

   // Calcul de la somme
   public int calculerSomme() {
      int sum = 0;
      for (int note : notes) {
         sum += note;
      }
      return sum;
   }

   // Calcul de la moyenne
   public double calculerMoyenne() {
      if (notes.isEmpty()) return 0;
      return (double) calculerSomme() / notes.size();
   }

   // Tri du tableau (ArrayList)
   public ArrayList<Integer> trier() {
      ArrayList<Integer> trie = new ArrayList<>(notes);
      Collections.sort(trie);
      return trie;
   }

   // Calcul de la médiane
   public double calculerMediane() {
      if (notes.isEmpty()) return 0;
      ArrayList<Integer> trie = trier();
      int n = trie.size();
      if (n % 2 == 1) {
         return trie.get(n / 2);
      } else {
         return (trie.get(n / 2 - 1) + trie.get(n / 2)) / 2.0;
      }
   }

   // Affichage des notes
   public String afficherNotes() {
      StringBuilder sb = new StringBuilder();
      sb.append("Notes saisies :\n");
      for (int i = 0; i < notes.size(); i++) {
         sb.append("  Étudiant ").append(i + 1)
                 .append(" : ").append(notes.get(i)).append("\n");
      }
      return sb.toString();
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("===== RESULTATS =====\n");
      sb.append("Nb étudiants : ").append(getNbEtudiants()).append("\n");
      sb.append(String.format("Moyenne      : %.2f%n", calculerMoyenne()));
      sb.append(String.format("Médiane      : %.1f%n", calculerMediane()));
      sb.append("=====================");
      return sb.toString();
   }
}