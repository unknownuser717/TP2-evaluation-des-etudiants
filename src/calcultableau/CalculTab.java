package calcultableau;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe responsable du stockage et du traitement des notes des étudiants.
 *
 * <p>Permet d'ajouter des notes, de les trier, et de calculer
 * des statistiques telles que la somme, la moyenne et la médiane.</p>
 *
 * <p>Exemple d'utilisation :</p>
 * <pre>
 *     CalculTab calc = new CalculTab();
 *     calc.ajouterNote(10);
 *     calc.ajouterNote(14);
 *     double moyenne = calc.calculerMoyenne(); // 12.0
 * </pre>
 *
 * @author BUT Informatique 2A
 * @version 1.0
 */
public class CalculTab {

    /** Liste des notes des étudiants. */
    private ArrayList<Integer> notes;

    /**
     * Constructeur par défaut.
     * Initialise une liste de notes vide.
     */
    public CalculTab() {
        this.notes = new ArrayList<>();
    }

    /**
     * Ajoute une note à la liste des notes.
     *
     * @param note la note à ajouter (valeur entière, généralement entre 0 et 20)
     */
    public void ajouterNote(int note) {
        notes.add(note);
    }

    /**
     * Retourne le nombre d'étudiants notés,
     * c'est-à-dire le nombre de notes saisies.
     *
     * @return le nombre de notes dans la liste
     */
    public int getNbEtudiants() {
        return notes.size();
    }

    /**
     * Calcule la somme de toutes les notes.
     *
     * @return la somme des notes, ou {@code 0} si la liste est vide
     */
    public int calculerSomme() {
        int sum = 0;
        for (int note : notes) {
            sum += note;
        }
        return sum;
    }

    /**
     * Calcule la moyenne arithmétique des notes.
     *
     * <p>Retourne {@code 0.0} si aucune note n'a été saisie.</p>
     *
     * @return la moyenne des notes sous forme de {@code double},
     *         ou {@code 0.0} si la liste est vide
     */
    public double calculerMoyenne() {
        if (notes.isEmpty()) return 0;
        return (double) calculerSomme() / notes.size();
    }

    /**
     * Retourne une copie triée (ordre croissant) de la liste des notes.
     *
     * <p>La liste originale n'est pas modifiée.</p>
     *
     * @return un nouvel {@code ArrayList} contenant les notes triées
     */
    public ArrayList<Integer> trier() {
        ArrayList<Integer> trie = new ArrayList<>(notes);
        Collections.sort(trie);
        return trie;
    }

    /**
     * Calcule la médiane des notes.
     *
     * <p>La médiane est calculée sur la liste triée :</p>
     * <ul>
     *   <li>Si le nombre de notes est <b>impair</b> : valeur centrale</li>
     *   <li>Si le nombre de notes est <b>pair</b> : moyenne des deux valeurs centrales</li>
     * </ul>
     *
     * <p>Retourne {@code 0.0} si aucune note n'a été saisie.</p>
     *
     * @return la médiane sous forme de {@code double},
     *         ou {@code 0.0} si la liste est vide
     */
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

    /**
     * Retourne une chaîne de caractères listant toutes les notes saisies,
     * avec le numéro de l'étudiant correspondant.
     *
     * <p>Exemple de sortie :</p>
     * <pre>
     *     Notes saisies :
     *       Étudiant 1 : 12
     *       Étudiant 2 : 15
     * </pre>
     *
     * @return une {@code String} formatée avec toutes les notes
     */
    public String afficherNotes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Notes saisies :\n");
        for (int i = 0; i < notes.size(); i++) {
            sb.append("  Étudiant ").append(i + 1)
                    .append(" : ").append(notes.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Retourne un résumé complet des résultats du groupe :
     * nombre d'étudiants, moyenne et médiane.
     *
     * <p>Exemple de sortie :</p>
     * <pre>
     *     ===== RESULTATS =====
     *     Nb étudiants : 3
     *     Moyenne      : 12.00
     *     Médiane      : 12.0
     *     =====================
     * </pre>
     *
     * @return une {@code String} formatée avec les statistiques du groupe
     */
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