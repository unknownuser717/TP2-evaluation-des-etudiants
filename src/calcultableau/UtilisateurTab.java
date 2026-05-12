package calcultableau;

import java.time.LocalDate;

/**
 * Représente un enseignant utilisateur de l'application CalculTableau.
 *
 * <p>Stocke les informations personnelles de l'enseignant (prénom, nom, email)
 * ainsi que la date de l'examen, initialisée automatiquement à la date du jour.</p>
 *
 * <p>L'adresse email est validée à la construction : si le format est invalide,
 * une {@link IllegalArgumentException} est levée.</p>
 *
 * <p>Exemple d'utilisation :</p>
 * <pre>
 *     UtilisateurTab utilisateur = new UtilisateurTab("Alain", "Dupont", "alain.dupont@iut.fr");
 *     System.out.println(utilisateur.getEmail()); // alain.dupont@iut.fr
 * </pre>
 *
 * @author BUT Informatique 2A
 * @version 1.0
 */
public class UtilisateurTab {

    /** Prénom de l'enseignant. */
    private String prenom;

    /** Nom de famille de l'enseignant. */
    private String nom;

    /** Adresse email de l'enseignant, validée à la construction. */
    private String email;

    /** Date de l'examen, initialisée automatiquement à la date du jour. */
    private LocalDate dateExamen;

    /**
     * Construit un utilisateur avec ses informations personnelles.
     *
     * <p>La date de l'examen est automatiquement définie à la date du jour ({@link LocalDate#now()}).</p>
     *
     * @param prenom le prénom de l'enseignant (non {@code null})
     * @param nom    le nom de famille de l'enseignant (non {@code null})
     * @param email  l'adresse email de l'enseignant — doit respecter le format
     *               {@code nom@domaine.ext}
     * @throws IllegalArgumentException si l'adresse email est {@code null} ou mal formatée
     */
    public UtilisateurTab(String prenom, String nom, String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateExamen = LocalDate.now();

        if (!emailValide(email)) {
            throw new IllegalArgumentException("Adresse mail invalide : " + email);
        }
        this.email = email;
    }

    /**
     * Vérifie que l'adresse email respecte un format standard.
     *
     * <p>Le format attendu est : {@code caractères@domaine.extension}
     * où l'extension fait au minimum 2 caractères.</p>
     *
     * <p>Exemples valides : {@code alain.dupont@iut.fr}, {@code a.b@c.org}</p>
     * <p>Exemples invalides : {@code alain.dupont}, {@code @iut.fr}, {@code null}</p>
     *
     * @param email l'adresse email à valider
     * @return {@code true} si le format est valide, {@code false} sinon
     */
    private boolean emailValide(String email) {
        if (email == null) return false;
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    /**
     * Retourne le prénom de l'enseignant.
     *
     * @return le prénom
     */
    public String getPrenom() { return prenom; }

    /**
     * Retourne le nom de famille de l'enseignant.
     *
     * @return le nom
     */
    public String getNom() { return nom; }

    /**
     * Retourne l'adresse email de l'enseignant.
     *
     * @return l'email validé
     */
    public String getEmail() { return email; }

    /**
     * Retourne la date de l'examen.
     *
     * <p>Cette date est définie automatiquement à la date du jour
     * lors de la création de l'objet.</p>
     *
     * @return la date de l'examen sous forme de {@link LocalDate}
     */
    public LocalDate getDateExamen() { return dateExamen; }

    /**
     * Retourne une représentation textuelle de l'utilisateur,
     * avec ses informations personnelles et la date de l'examen.
     *
     * <p>Exemple de sortie :</p>
     * <pre>
     *     Prénom      : Alain
     *     Nom         : Dupont
     *     Email       : alain.dupont@iut.fr
     *     Date examen : 2026-05-12
     * </pre>
     *
     * @return une {@code String} formatée avec les informations de l'enseignant
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prénom      : ").append(prenom).append("\n");
        sb.append("Nom         : ").append(nom).append("\n");
        sb.append("Email       : ").append(email).append("\n");
        sb.append("Date examen : ").append(dateExamen);
        return sb.toString();
    }
}