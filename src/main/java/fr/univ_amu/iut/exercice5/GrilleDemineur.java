package fr.univ_amu.iut.exercice5;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercice 5 - Grille du démineur.
 *
 * <p>Prend en entrée une grille de caractères {@code ' '} et {@code '*'} (où {@code '*'} représente
 * une mine) et produit la même grille annotée : chaque case vide reçoit le nombre de mines dans ses
 * 8 cases voisines (ou reste un espace si aucune mine n'est adjacente).
 *
 * <p>Exemple :
 *
 * <pre>
 *     +-----+     +-----+
 *     | * * |     |1*3*1|
 *     |  *  |  →  |13*31|
 *     |  *  |     | 2*2 |
 *     |     |     | 111 |
 *     +-----+     +-----+
 * </pre>
 *
 * <p>Cet exercice mêle :
 *
 * <ul>
 *   <li>tableaux 2D irréguliers (chaque ligne est une {@link String})
 *   <li>gestion des bornes (cases au bord de la grille)
 *   <li>validation d'entrée (null, symboles, lignes de longueurs différentes)
 *   <li>{@code ApprovalTests} pour une grille de grande taille (voir les tests)
 * </ul>
 */
public class GrilleDemineur {

  private final List<String> grille;

  /**
   * Construit une grille à partir de sa représentation textuelle.
   *
   * @param grilleInitiale lignes de la grille
   * @throws IllegalArgumentException si la grille est {@code null}, contient un caractère autre que
   *     {@code ' '} ou {@code '*'}, ou si les lignes ont des longueurs différentes
   */
  public GrilleDemineur(List<String> grilleInitiale) {
    // TODO exercice 5 : valider l'entrée puis stocker la grille.
    this.grille = grilleInitiale == null ? List.of() : List.copyOf(grilleInitiale);

    if (grilleInitiale == null) {
      throw new IllegalArgumentException();
    }

    // Check all symbols
    int previousLength = 0;
    for (int line = 0; line < grille.size(); line++) {
      char[] str = grille.get(line).toCharArray();
      // Check unknown symbols
      for (int column = 0; column < str.length; column++) {
        if (str[column] != '*' && str[column] != ' ') {
          throw new IllegalArgumentException();
        }
      }
      // Check different lengths
      if (previousLength != 0) {
        if (previousLength != str.length) {
          throw new IllegalArgumentException();
        }
      }
      previousLength = str.length;
    }
  }

  private char compterMinesAdjacentes(int line, int column) {
    int amntMines = 0;

    // Left
    if ((column - 1 >= 0) && grille.get(line).toCharArray()[column - 1] == '*') {
      amntMines += 1;
    }
    // Right
    if ((column + 1 < grille.get(line).toCharArray().length)
        && grille.get(line).toCharArray()[column + 1] == '*') {
      amntMines += 1;
    }
    // Top
    if ((line - 1 >= 0) && grille.get(line - 1).toCharArray()[column] == '*') {
      amntMines += 1;
    }
    // Bottom
    if ((line + 1 < grille.size()) && grille.get(line + 1).toCharArray()[column] == '*') {
      amntMines += 1;
    }

    // Top-Left
    if ((line - 1 >= 0 && column - 1 >= 0)
        && grille.get(line - 1).toCharArray()[column - 1] == '*') {
      amntMines += 1;
    }
    // Top-Right
    if ((line - 1 >= 0 && column + 1 < grille.get(line).toCharArray().length)
        && grille.get(line - 1).toCharArray()[column + 1] == '*') {
      amntMines += 1;
    }
    // Bottom-Left
    if ((line + 1 < grille.size() && column - 1 >= 0)
        && grille.get(line + 1).toCharArray()[column - 1] == '*') {
      amntMines += 1;
    }
    // Bottom-Right
    if ((line + 1 < grille.size() && column + 1 < grille.get(line).toCharArray().length)
        && grille.get(line + 1).toCharArray()[column + 1] == '*') {
      amntMines += 1;
    }

    if (amntMines == 0) {
      return ' ';
    } else {
      return String.valueOf(amntMines).charAt(0);
    }
  }

  /**
   * Retourne la grille annotée : chaque case vide est remplacée par le nombre de mines adjacentes
   * (ou un espace si aucune), chaque mine reste un {@code '*'}.
   */
  public List<String> getRepresentationAnnotee() {
    List<String> resultat = new ArrayList<>(grille.size());
    // TODO exercice 5 : remplir resultat avec une ligne annotée par ligne d'entrée.
    //
    // Pour chaque case (ligne, col) :
    // - si c'est une mine ('*'), laisser '*'
    // - sinon compter les mines dans les 8 cases voisines (en gérant les bords)
    // - si le compte est > 0, écrire ce chiffre
    // - si le compte est 0, écrire un espace
    //

    if (grille == null) {
      throw new IllegalArgumentException();
    }

    for (int ligne = 0; ligne < grille.size(); ligne++) {
      char[] ligneChar = grille.get(ligne).toCharArray();
      char[] resulatLigne = new char[ligneChar.length];

      for (int column = 0; column < ligneChar.length; column++) {
        if (ligneChar[column] == '*') {
          // Ajout à la ligne
          resulatLigne[column] = '*';
        } else {
          // Ajout à la ligne
          resulatLigne[column] = compterMinesAdjacentes(ligne, column);
        }
      }

      // Ajout au tableau
      resultat.add(ligne, new String(resulatLigne));
    }

    // Astuce : une méthode privée compterMinesAdjacentes(int, int) facilite
    // la gestion des bords et rend le code testable.
    return resultat;
  }
}
