package CalculTab;

import java.util.Arrays;
import java.util.Scanner;

public class CalculTab {

    public static void main(String[] args) {

        int[] tab = new int[50];

        int n;
        double somme = 0;
        double moyenne;
        double mediane;

        Scanner sc = new Scanner(System.in);

        // Taille tableau
        do {
            System.out.println("Veuillez entrer la taille du tableau (max 50)");
            n = sc.nextInt();

        } while (n <= 0 || n > 50);

        System.out.println("**** DEBUT PROGRAMME ****");

        // Remplissage
        for (int i = 0; i < n; i++) {

            System.out.println("Veuillez entrer une note : ");
            tab[i] = sc.nextInt();
        }

        // Affichage
        System.out.println("Les éléments du tableau sont :");

        for (int i = 0; i < n; i++) {
            System.out.println(tab[i]);
        }

        // Somme
        for (int i = 0; i < n; i++) {
            somme += tab[i];
        }

        // Moyenne
        moyenne = somme / n;

        // Médiane
        int[] copie = Arrays.copyOf(tab, n);
        Arrays.sort(copie);

        if (n % 2 == 0) {
            mediane = (copie[n / 2 - 1] + copie[n / 2]) / 2.0;
        } else {
            mediane = copie[n / 2];
        }

        // Informations enseignant
        String prenom = "Alain";
        String nom = "Dupont";
        String email = "alain.dupont@iut.fr";
        String dateExamen = "12/05/2026";

        // Résultats
        System.out.println("\n===== RESULTATS =====");

        System.out.println("Prénom : " + prenom);
        System.out.println("Nom : " + nom);
        System.out.println("Email : " + email);
        System.out.println("Date examen : " + dateExamen);

        System.out.println("Nombre d'étudiants : " + n);
        System.out.println("Moyenne : " + moyenne);
        System.out.println("Médiane : " + mediane);

        System.out.println("**** FIN PROGRAMME ****");




        sc.close();
    }
}