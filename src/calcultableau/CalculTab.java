package calcultableau;
import java.util.*;
// Somme des éléments d'un tableau d'entiers
public class CalculTab {
    public static void main(String[] args) {
        int tab[] = new int[50];
        int i;
        int n = 0;
        int Sum = 0;
        int moy;
// LA TAILLE DU TABLEAU
        do {
            System.out.println("Veuillez entrer la taille du tableau");
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
        } while (n > 50);
// REMPLISSAGE DE TABLEAU
        System.out.println("DEBUT PROGRAMME");
        for (i = 0; i < n; i++) {
            System.out.println("Veuillez entrer un nombre");
            Scanner sc1 = new Scanner(System.in);
            tab[i] = sc1.nextInt();
        }
// AFFICHAGE DE TABLEAU
        System.out.println("Les élements de tableau sont : ");
        for (i = 0; i < n; i++) {
            System.out.println(tab[i]);
        }
// AFFICHAGE DE SOMME
        for (i = 0; i < n; i++) {
            Sum += tab[i];
        }
        System.out.println("La somme des éléments est égale à " + Sum);

// AFFICHAGE DE MOYENNE
        moy = Sum/n;
        System.out.println("moyenne = " + moy);


// CALCUL DE MEDIANE
       // 1. Copier les n éléments dans un tableau de taille exacte
       int[] tabTrie = Arrays.copyOf(tab, n);
       // 2. Trier le tableau
       Arrays.sort(tabTrie);

       double mediane;
       if (n % 2 == 1) {
          // Nombre impair → élément du milieu
          mediane = tabTrie[n / 2];
       } else {
          // Nombre pair → moyenne des deux éléments du milieu
          mediane = (tabTrie[n / 2 - 1] + tabTrie[n / 2]) / 2.0;
       }
       System.out.println("La médiane est égale à " + mediane);

       System.out.println("FIN PROGRAMME");

    }
}