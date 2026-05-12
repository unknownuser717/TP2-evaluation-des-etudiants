package test.java.calcultableau;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests unitaires de la classe {@link UtilisateurTab}.
 *
 * <p>Convention de nommage :
 * {@code givenContexte_whenMethode_thenComportementAttendu()}</p>
 *
 * <p>Structure de chaque test : Arrange / Act / Assert (AAA)</p>
 *
 * @author BUT Informatique 2A
 * @version 1.0
 */
@DisplayName("Tests de la classe UtilisateurTab")
class UtilisateurTabTest {

    // =========================================================
    // TESTS : Constructeur et validation email
    // =========================================================

    @Nested
    @DisplayName("Constructeur — email valide")
    class ConstructeurEmailValide {

        @Test
        @DisplayName("Email valide → objet créé sans exception")
        void givenEmailValide_whenConstructeur_thenObjetCreeCorrectement() {
            // Arrange + Act
            UtilisateurTab utilisateur = new UtilisateurTab("Alain", "Dupont", "alain.dupont@iut.fr");

            // Assert
            assertThat(utilisateur).isNotNull();
            assertThat(utilisateur.getPrenom()).isEqualTo("Alain");
            assertThat(utilisateur.getNom()).isEqualTo("Dupont");
            assertThat(utilisateur.getEmail()).isEqualTo("alain.dupont@iut.fr");
        }

        @Test
        @DisplayName("Date examen initialisée à aujourd'hui")
        void givenNouvelUtilisateur_whenGetDateExamen_thenRetourneDateDuJour() {
            // Arrange
            UtilisateurTab utilisateur = new UtilisateurTab("Alain", "Dupont", "alain.dupont@iut.fr");

            // Act
            LocalDate dateExamen = utilisateur.getDateExamen();

            // Assert
            assertThat(dateExamen).isEqualTo(LocalDate.now());
        }

        @ParameterizedTest(name = "Email valide : {0}")
        @DisplayName("Formats d'email valides acceptés")
        @ValueSource(strings = {
                "alain.dupont@iut.fr",
                "a@b.org",
                "prenom.nom@domaine.com",
                "test123@univ-lorraine.fr"
        })
        void givenEmailsValides_whenConstructeur_thenAucuneException(String email) {
            // Arrange + Act + Assert
            assertThatNoException()
                    .isThrownBy(() -> new UtilisateurTab("Alain", "Dupont", email));
        }
    }

    @Nested
    @DisplayName("Constructeur — email invalide")
    class ConstructeurEmailInvalide {

        @Test
        @DisplayName("Email null → IllegalArgumentException")
        void givenEmailNull_whenConstructeur_thenLeveIllegalArgumentException() {
            // Arrange + Act + Assert
            assertThatThrownBy(() -> new UtilisateurTab("Alain", "Dupont", null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("invalide");
        }

        @ParameterizedTest(name = "Email invalide : {0}")
        @DisplayName("Formats d'email invalides refusés")
        @ValueSource(strings = {
                "alain.dupont",
                "@iut.fr",
                "alain@",
                "alain dupont@iut.fr",
                ""
        })
        void givenEmailsInvalides_whenConstructeur_thenLeveIllegalArgumentException(String email) {
            // Arrange + Act + Assert
            assertThatThrownBy(() -> new UtilisateurTab("Alain", "Dupont", email))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("invalide");
        }
    }

    // =========================================================
    // TESTS : Getters
    // =========================================================

    @Nested
    @DisplayName("Getters")
    class Getters {

        @Test
        @DisplayName("getPrenom() retourne le prénom saisi")
        void givenUtilisateur_whenGetPrenom_thenRetournePrenomCorrect() {
            // Arrange
            UtilisateurTab utilisateur = new UtilisateurTab("Alain", "Dupont", "alain.dupont@iut.fr");

            // Act + Assert
            assertThat(utilisateur.getPrenom()).isEqualTo("Alain");
        }

        @Test
        @DisplayName("getNom() retourne le nom saisi")
        void givenUtilisateur_whenGetNom_thenRetourneNomCorrect() {
            // Arrange
            UtilisateurTab utilisateur = new UtilisateurTab("Alain", "Dupont", "alain.dupont@iut.fr");

            // Act + Assert
            assertThat(utilisateur.getNom()).isEqualTo("Dupont");
        }

        @Test
        @DisplayName("getEmail() retourne l'email saisi")
        void givenUtilisateur_whenGetEmail_thenRetourneEmailCorrect() {
            // Arrange
            UtilisateurTab utilisateur = new UtilisateurTab("Alain", "Dupont", "alain.dupont@iut.fr");

            // Act + Assert
            assertThat(utilisateur.getEmail()).isEqualTo("alain.dupont@iut.fr");
        }
    }

    // =========================================================
    // TESTS : toString()
    // =========================================================

    @Nested
    @DisplayName("toString()")
    class ToStringTest {

        @Test
        @DisplayName("toString() contient prénom, nom, email et date")
        void givenUtilisateur_whenToString_thenContientToutesLesInfos() {
            // Arrange
            UtilisateurTab utilisateur = new UtilisateurTab("Alain", "Dupont", "alain.dupont@iut.fr");

            // Act
            String resultat = utilisateur.toString();

            // Assert
            assertThat(resultat)
                    .contains("Alain")
                    .contains("Dupont")
                    .contains("alain.dupont@iut.fr")
                    .contains(LocalDate.now().toString());
        }
    }
}
