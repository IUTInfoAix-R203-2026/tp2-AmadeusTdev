package fr.univ_amu.iut.exercice3;

/**
 * Exercice 3 - Convertisseur de chiffres romains en nombres arabes.
 *
 * <p>Règles :
 *
 * <ul>
 *   <li>Les symboles valides sont {@code I=1, V=5, X=10, L=50, C=100, D=500, M=1000}
 *   <li>Lus de gauche à droite, les symboles s'additionnent : {@code VI = 5 + 1 = 6}
 *   <li>Un symbole placé avant un symbole de valeur supérieure se soustrait : {@code IV = 5 - 1 =
 *       4}
 *   <li>Les seules soustractions valides sont :
 *       <ul>
 *         <li>I avant V ou X ({@code IV = 4}, {@code IX = 9})
 *         <li>X avant L ou C ({@code XL = 40}, {@code XC = 90})
 *         <li>C avant D ou M ({@code CD = 400}, {@code CM = 900})
 *       </ul>
 *       Toute autre soustraction doit lever {@link IllegalArgumentException}.
 * </ul>
 *
 * <p>Conseils TDD : commencez par gérer uniquement {@code I}, puis {@code II} / {@code III} (fake
 * it), puis {@code V} (triangulation), puis {@code VI} (addition de deux symboles différents), puis
 * {@code IV} (introduction de la soustraction). À ce moment-là, <b>extrayez une méthode</b> pour
 * calculer la valeur d'un symbole - vous en aurez besoin pour les symboles suivants.
 */
public class ConvertisseurDeNombreRomain {

  /**
   * Convertit une chaîne de chiffres romains en valeur entière.
   *
   * @param chiffreRomain chaîne composée de symboles romains (par exemple {@code "XLIX"})
   * @return la valeur entière correspondante
   * @throws IllegalArgumentException si la chaîne contient un symbole invalide ou une soustraction
   *     interdite
   */
  public int enNombreArabe(String chiffreRomain) {
    int total = 0;
    // TODO exercice 3 : remplir total en parcourant la chaîne.
    //
    // Activez les tests un par un. Commencez par "I" = 1 (fake it en
    // retournant 1 en dur), puis "II" = 2 et "III" = 3 (boucle de comptage
    // d'occurrences de I), puis "V" = 5 (switch sur le symbole).
    //

    // Listes des symboles et valeurs
    char[] charList = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
    int[] valeurList = {1, 5, 10, 50, 100, 500, 1000};

    // On boucle pour chaque lettres
    char[] chiffres = chiffreRomain.toCharArray();
    for (int i = 0; i < chiffreRomain.length(); i++) {
      // Calcule de la valeur du symbole
      int currentChar = chiffres[i];
      int val = 0;

      for (int pos = 0; pos < charList.length; pos++) {
        if (charList[pos] == currentChar) {
          val = valeurList[pos];
        }
      }

      // Valeur négative
      if (i + 1 < chiffreRomain.length()) {
        char nextChar = chiffres[i + 1];
        int nextVal = 0;

        // Calcule de la valeur du symbole
        for (int pos = 0; pos < charList.length; pos++) {
          if (charList[pos] == nextChar) {
            nextVal = valeurList[pos];
          }
        }

        // Chiffre inexistant
        if (val == 0) {
          throw new IllegalArgumentException();
        }
        // Negatif
        if (val < nextVal) {
          if ((currentChar == 'I' && (nextChar == 'V' || nextChar == 'X'))
              || (currentChar == 'X' && (nextChar == 'L' || nextChar == 'C'))
              || (currentChar == 'C' && (nextChar == 'D' || nextChar == 'M'))) {
            val = -val;
          } else {
            // Combinaison invalide
            throw new IllegalArgumentException();
          }
        }
      }
      // Chiffre inexistant
      if (val == 0) {
        throw new IllegalArgumentException();
      }

      // Ajout
      total += val;
    }
    // Quand vous arrivez à "IV" = 4 : extrayez une méthode valeurDe(char)
    // pour factoriser, puis ajoutez la logique de soustraction.
    //
    // Pour les exceptions : une soustraction est valide seulement pour
    // I avant V/X, X avant L/C, C avant D/M. Tout le reste est invalide.
    return total;
  }
}
