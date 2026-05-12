# Scénario de l'application CalculTableau

## Contexte

Alain Dupont est un enseignant en informatique (alain.dupont@iut.fr).
Il utilise cette application pour saisir les notes de ses étudiants, puis obtenir
automatiquement la moyenne et la médiane du groupe.

---

## Acteurs

| Acteur         | Rôle                                                      |
|----------------|-----------------------------------------------------------|
| Alain Dupont   | Enseignant, utilisateur principal de l'application        |
| L'application  | Calcule et affiche les statistiques des notes saisies     |

---

## Scénario principal (pas à pas)

### Étape 1 — Lancement du programme
- L'enseignant lance le programme via la classe `Principale`.
- Le programme affiche un message de bienvenue :
  ```
  **** DEBUT PROGRAMME ****
  ```

### Étape 2 — Saisie des informations de l'enseignant
- Le programme demande à l'enseignant de saisir :
    - Son **prénom** (ex : `Alain`)
    - Son **nom** (ex : `Dupont`)
    - Son **adresse email** (ex : `alain.dupont@iut.fr`)
    - La **date de l'examen** (ex : `12/05/2026`)
- La classe `UtilisateurTab` stocke ces informations.
- ⚠️ Si l'adresse email n'est pas au bon format, le programme affiche une erreur
  et redemande la saisie.

### Étape 3 — Saisie de la taille du tableau
- Le programme demande le **nombre d'étudiants** à noter.
- La valeur doit être un entier strictement positif.
- ⚠️ Si la valeur est invalide (négative ou nulle), le programme redemande la saisie.

### Étape 4 — Saisie des notes
- Pour chaque étudiant (de 1 à n), le programme demande une note.
- Les notes sont stockées dans un `ArrayList<Integer>` dans la classe `CalculTab`.
- ⚠️ Les notes doivent être comprises entre 0 et 20.

### Étape 5 — Calcul des statistiques
La classe `CalculTab` effectue les calculs suivants :

- **Tri** du tableau de notes (ordre croissant)
- **Moyenne** : somme des notes / nombre d'étudiants
- **Médiane** :
    - Si nombre d'étudiants **impair** → note centrale
    - Si nombre d'étudiants **pair** → moyenne des deux notes centrales

### Étape 6 — Affichage des résultats
Le programme affiche dans la console :

```
Enseignant   : Alain Dupont (alain.dupont@iut.fr)
Date examen  : 12/05/2026
Nb étudiants : 5
Notes        : [8, 10, 12, 14, 16]
Moyenne      : 12.0
Médiane      : 12.0
**** FIN PROGRAMME ****
```

---

## Scénarios alternatifs (cas d'erreur)

| Situation                        | Comportement attendu                              |
|----------------------------------|---------------------------------------------------|
| Email mal formaté                | Message d'erreur + nouvelle saisie demandée       |
| Taille du tableau ≤ 0            | Message d'erreur + nouvelle saisie demandée       |
| Note hors de [0, 20]             | Message d'erreur + nouvelle saisie demandée       |
| Tableau vide (aucune note)       | Moyenne et médiane affichées à 0.0                |

---

## Structure des classes

```
calcultableau/
├── Principale.java       → point d'entrée, gère les interactions console
├── CalculTab.java        → stocke les notes, calcule moyenne/médiane/tri
└── UtilisateurTab.java   → stocke les infos de l'enseignant, valide l'email
```

---

## Diagramme de séquence simplifié

```
Enseignant          Principale          UtilisateurTab       CalculTab
    |                    |                     |                  |
    |--- Lance prog. --->|                     |                  |
    |<-- Saisir infos ---|                     |                  |
    |--- Prénom/Nom/Email/Date -------------->|                  |
    |                    |<-- Valide email ----|                  |
    |<-- Saisir nb étudiants -----------------|                  |
    |--- n étudiants --->|                     |                  |
    |<-- Saisir notes ---|                                        |
    |--- notes[] ------->|---------------------------------------->|
    |                    |                                        |-- tri()
    |                    |                                        |-- moyenne()
    |                    |                                        |-- mediane()
    |<-- Affichage résultats -----------------------------------------|
```

---

## Exigences de qualité

- Nommage **clair et lisible** (camelCase, PascalCase)
- **Javadoc** sur toutes les classes et méthodes publiques
- **Tests unitaires** JUnit 5 + AssertJ (couverture ≥ 50%)
- **Commits Git** réguliers et bien nommés