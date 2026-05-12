package calcultableau;

import java.time.LocalDate;

public class UtilisateurTab {

   private String prenom;
   private String nom;
   private String email;
   private LocalDate dateExamen;

   public UtilisateurTab(String prenom, String nom, String email) {
      this.prenom = prenom;
      this.nom = nom;
      this.dateExamen = LocalDate.now();

      if (!emailValide(email)) {
         throw new IllegalArgumentException("Adresse mail invalide : " + email);
      }
      this.email = email;
   }

   // Validation du format email
   private boolean emailValide(String email) {
      if (email == null) return false;
      String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
      return email.matches(regex);
   }

   // Getters
   public String getPrenom()         { return prenom; }
   public String getNom()            { return nom; }
   public String getEmail()          { return email; }
   public LocalDate getDateExamen()  { return dateExamen; }

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