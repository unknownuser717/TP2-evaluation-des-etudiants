
package calcultableau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;



/**
 * Tests unitaires de la classe {@link CalculTab}.
 *
 * <p>Convention de nommage :
 * {@code givenContexte_whenMethode_thenComportementAttendu()}</p>
 *
 * <p>Structure de chaque test : Arrange / Act / Assert (AAA)</p>
 *
 * @author BUT Informatique 2A
 * @version 1.0
 */
@DisplayName("Tests de la classe CalculTab")
class CalculTabTest {

    /** Instance de CalculTab réinitialisée avant chaque test. */
    private CalculTab calcul;

    @BeforeEach
    void setUp() {
        calcul = new CalculTab();
    }

    // =========================================================
    // TESTS : ajouterNote() / getNbEtudiants()
    // =========================================================

    @Nested
    @DisplayName("ajouterNote() et getNbEtudiants()")
    class AjouterNoteEtNbEtudiants {

        @Test
        @DisplayName("Tableau vide → 0 étudiant")
        void givenTableauVide_whenGetNbEtudiants_thenRetourneZero() {
            // Arrange : calcul déjà vide (setUp)
            // Act
            int resultat = calcul.getNbEtudiants();
            // Assert
            assertThat(resultat).isZero();
        }

        @Test
        @DisplayName("Ajout d'une note → 1 étudiant")
        void givenUneNote_whenAjouterNote_thenNbEtudiantsEstUn() {
            // Arrange + Act
            calcul.ajouterNote(10);
            // Assert
            assertThat(calcul.getNbEtudiants()).isEqualTo(1);
        }

        @Test
        @DisplayName("Ajout de plusieurs notes → compte correct")
        void givenTroisNotes_whenAjouterNote_thenNbEtudiantsEstTrois() {
            // Arrange + Act
            calcul.ajouterNote(10);
            calcul.ajouterNote(12);
            calcul.ajouterNote(14);
            // Assert
            assertThat(calcul.getNbEtudiants()).isEqualTo(3);
        }
    }

    // =========================================================
    // TESTS : calculerSomme()
    // =========================================================

    @Nested
    @DisplayName("calculerSomme()")
    class CalculerSomme {

        @Test
        @DisplayName("Tableau vide → somme = 0")
        void givenTableauVide_whenCalculerSomme_thenRetourneZero() {
            // Arrange : calcul vide
            // Act
            int resultat = calcul.calculerSomme();
            // Assert
            assertThat(resultat).isZero();
        }

        @Test
        @DisplayName("Notes [10, 12, 14] → somme = 36")
        void givenTroisNotes_whenCalculerSomme_thenRetourneSommeCorrecte() {
            // Arrange
            calcul.ajouterNote(10);
            calcul.ajouterNote(12);
            calcul.ajouterNote(14);
            // Act
            int resultat = calcul.calculerSomme();
            // Assert
            assertThat(resultat).isEqualTo(36);
        }

        @Test
        @DisplayName("Note [0] → somme = 0")
        void givenNoteZero_whenCalculerSomme_thenRetourneZero() {
            // Arrange
            calcul.ajouterNote(0);
            // Act
            int resultat = calcul.calculerSomme();
            // Assert
            assertThat(resultat).isZero();
        }
    }

    // =========================================================
    // TESTS : calculerMoyenne()
    // =========================================================

    @Nested
    @DisplayName("calculerMoyenne()")
    class CalculerMoyenne {

        @Test
        @DisplayName("Tableau vide → moyenne = 0.0")
        void givenTableauVide_whenCalculerMoyenne_thenRetourneZero() {
            // Arrange : calcul vide
            // Act
            double resultat = calcul.calculerMoyenne();
            // Assert
            assertThat(resultat).isEqualTo(0.0);
        }

        @Test
        @DisplayName("Notes [10, 12, 14] → moyenne = 12.0")
        void givenTroisNotes_whenCalculerMoyenne_thenRetourneMoyenneCorrecte() {
            // Arrange
            calcul.ajouterNote(10);
            calcul.ajouterNote(12);
            calcul.ajouterNote(14);
            // Act
            double resultat = calcul.calculerMoyenne();
            // Assert
            assertThat(resultat).isEqualTo(12.0);
        }

        @Test
        @DisplayName("Note unique [20] → moyenne = 20.0")
        void givenNoteUnique_whenCalculerMoyenne_thenRetourneNoteElleMeme() {
            // Arrange
            calcul.ajouterNote(20);
            // Act
            double resultat = calcul.calculerMoyenne();
            // Assert
            assertThat(resultat).isEqualTo(20.0);
        }

        @ParameterizedTest(name = "Notes {0} → moyenne attendue {1}")
        @DisplayName("Moyenne paramétrée sur plusieurs cas")
        @MethodSource("calcultableau.CalculTabTest#fournirCasMoyenne")
        void givenNotesVariees_whenCalculerMoyenne_thenRetourneMoyenneAttendue(
                int[] notes, double moyenneAttendue) {
            // Arrange
            for (int note : notes) calcul.ajouterNote(note);
            // Act
            double resultat = calcul.calculerMoyenne();
            // Assert
            assertThat(resultat).isEqualTo(moyenneAttendue);
        }
    }

    // =========================================================
    // TESTS : trier()
    // =========================================================

    @Nested
    @DisplayName("trier()")
    class Trier {

        @Test
        @DisplayName("Notes désordonnées → liste triée croissante")
        void givenNotesDesordonnees_whenTrier_thenRetourneListeTriee() {
            // Arrange
            calcul.ajouterNote(15);
            calcul.ajouterNote(8);
            calcul.ajouterNote(12);
            // Act
            ArrayList<Integer> resultat = calcul.trier();
            // Assert
            assertThat(resultat).containsExactly(8, 12, 15);
        }

        @Test
        @DisplayName("Notes déjà triées → liste inchangée")
        void givenNotesDejaTriees_whenTrier_thenRetourneMemeListe() {
            // Arrange
            calcul.ajouterNote(5);
            calcul.ajouterNote(10);
            calcul.ajouterNote(20);
            // Act
            ArrayList<Integer> resultat = calcul.trier();
            // Assert
            assertThat(resultat).containsExactly(5, 10, 20);
        }

        @Test
        @DisplayName("trier() ne modifie pas la liste originale")
        void givenNotes_whenTrier_thenListeOriginaleInchangee() {
            // Arrange
            calcul.ajouterNote(15);
            calcul.ajouterNote(8);
            // Act
            calcul.trier();
            // Assert
            assertThat(calcul.getNbEtudiants()).isEqualTo(2);
            assertThat(calcul.calculerMoyenne()).isEqualTo(11.5);
        }
    }

    // =========================================================
    // TESTS : calculerMediane()
    // =========================================================

    @Nested
    @DisplayName("calculerMediane()")
    class CalculerMediane {

        @Test
        @DisplayName("Tableau vide → médiane = 0.0")
        void givenTableauVide_whenCalculerMediane_thenRetourneZero() {
            // Arrange : calcul vide
            // Act
            double resultat = calcul.calculerMediane();
            // Assert
            assertThat(resultat).isEqualTo(0.0);
        }

        @Test
        @DisplayName("Nombre impair de notes [8, 12, 15] → médiane = 12.0")
        void givenNombreImpairDeNotes_whenCalculerMediane_thenRetourneValeurCentrale() {
            // Arrange
            calcul.ajouterNote(15);
            calcul.ajouterNote(8);
            calcul.ajouterNote(12);
            // Act
            double resultat = calcul.calculerMediane();
            // Assert
            assertThat(resultat).isEqualTo(12.0);
        }

        @Test
        @DisplayName("Nombre pair de notes [10, 20] → médiane = 15.0")
        void givenNombrePairDeNotes_whenCalculerMediane_thenRetourneMoyenneDesDeuXCentrales() {
            // Arrange
            calcul.ajouterNote(10);
            calcul.ajouterNote(20);
            // Act
            double resultat = calcul.calculerMediane();
            // Assert
            assertThat(resultat).isEqualTo(15.0);
        }

        @Test
        @DisplayName("Note unique [14] → médiane = 14.0")
        void givenNoteUnique_whenCalculerMediane_thenRetourneNoteElleMeme() {
            // Arrange
            calcul.ajouterNote(14);
            // Act
            double resultat = calcul.calculerMediane();
            // Assert
            assertThat(resultat).isEqualTo(14.0);
        }

        @Test
        @DisplayName("Notes identiques [10, 10, 10] → médiane = 10.0")
        void givenNotesIdentiques_whenCalculerMediane_thenRetourneCetteValeur() {
            // Arrange
            calcul.ajouterNote(10);
            calcul.ajouterNote(10);
            calcul.ajouterNote(10);
            // Act
            double resultat = calcul.calculerMediane();
            // Assert
            assertThat(resultat).isEqualTo(10.0);
        }
    }

    // =========================================================
    // TESTS : afficherNotes() et toString()
    // =========================================================

    @Nested
    @DisplayName("afficherNotes() et toString()")
    class Affichage {

        @Test
        @DisplayName("afficherNotes() contient le numéro et la note de chaque étudiant")
        void givenDeuxNotes_whenAfficherNotes_thenContientEtudiantEtNote() {
            // Arrange
            calcul.ajouterNote(10);
            calcul.ajouterNote(18);
            // Act
            String resultat = calcul.afficherNotes();
            // Assert
            assertThat(resultat)
                    .contains("Étudiant 1")
                    .contains("10")
                    .contains("Étudiant 2")
                    .contains("18");
        }

        @Test
        @DisplayName("toString() contient le nombre d'étudiants, la moyenne et la médiane")
        void givenTroisNotes_whenToString_thenContientStatistiques() {
            // Arrange
            calcul.ajouterNote(10);
            calcul.ajouterNote(12);
            calcul.ajouterNote(14);
            // Act
            String resultat = calcul.toString();
            // Assert
            assertThat(resultat)
                    .contains("3")
                    .contains("12.00")
                    .contains("12.0");
        }
    }

    // =========================================================
    // FOURNISSEURS DE DONNÉES PARAMÉTRÉES
    // =========================================================

    /**
     * Fournit des cas de test paramétrés pour {@code calculerMoyenne()}.
     *
     * @return un flux d'arguments (notes[], moyenneAttendue)
     */
    static Stream<Arguments> fournirCasMoyenne() {
        return Stream.of(
                Arguments.of(new int[]{0, 20},      10.0),
                Arguments.of(new int[]{10, 10},      10.0),
                Arguments.of(new int[]{5, 10, 15},   10.0),
                Arguments.of(new int[]{0},            0.0),
                Arguments.of(new int[]{20, 20, 20},  20.0)
        );
    }
}
