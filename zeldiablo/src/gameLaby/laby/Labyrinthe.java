package gameLaby.laby;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Représente un labyrinthe avec
 * <ul>
 *    <li> des éléments déplaçables (personnage, monstres...) </li>
 *    <li> des éléments non déplaçables (escaliers) </li>
 *    <li> des murs </li>
 * </ul>
 */
public class Labyrinthe {

   /**
    * Constantes char
    */
   public static final char MUR = 'X';
   public static final char PJ = 'P';
   public static final char VIDE = '.';
   public static final char MONSTRE = 'M';
   public static final char ESCALIER = 'E';

   /**
    * Constantes actions possibles
    */
   public static final String HAUT = "Haut";
   public static final String BAS = "Bas";
   public static final String GAUCHE = "Gauche";
   public static final String DROITE = "Droite";

   /**
    * les murs du labyrinthe
    */
   public static boolean[][] murs;

   /**
    * Personnage
    */
   public static Perso pj;

   /**
    * Liste des éléments Déplaçables
    */
   public static ArrayList<Entite> entites = new ArrayList<>();


   /**
    * Liste de tous les niveaux
    * contient tous les niveaux de labyrinthe
    */
   public static ArrayList<Labyrinthe> allLevels = new ArrayList<>();

   /**
    * Labyrinthe actuel
    */
   public static Labyrinthe currentLabyrinthe;


   /**
    * retourne la case suivante selon une action
    *
    * @param x      case depart
    * @param y      case depart
    * @param action action effectuee
    * @return case suivante
    */
   public static int[] getSuivant(int x, int y, String action) {
      switch (action) {
         case HAUT:
            // on monte une ligne
            y--;
            break;
         case BAS:
            // on descend une ligne
            y++;
            break;
         case DROITE:
            // on augmente colonne
            x++;
            break;
         case GAUCHE:
            // on diminue colonne
            x--;
            break;
      }
      return new int[]{x, y};
   }

   /**
    * charge le labyrinthe
    *
    * @param nom nom du fichier de labyrinthe
    * @return labyrinthe cree
    * @throws IOException probleme a la lecture / ouverture
    */
   public Labyrinthe(String nom) throws IOException {

      pj = new Perso(1, 1);
      murs = GenerationLaby.creer();

   }

   /**
    * change le niveau
    *
    * @param levelIndex index du niveau
    * @throws IllegalArgumentException si le niveau n'existe pas
    * @throws IOException              si probleme a la lecture du fichier
    */
   public static void changerNiveau(int levelIndex) throws IllegalArgumentException, IOException {
      murs = GenerationLaby.creer();
      System.out.println("Passage au niveau " + levelIndex);
   }

   /**
    * deplace le personnage en fonction de l'action.
    * gere la collision avec les murs
    */
   public static void updateLaby() {

      // Liste pour stocker les éléments à supprimer
      ArrayList<Entite> toRemove = new ArrayList<>();

      // Déplace tous les objets déplaçables
      for (Entite e : entites) {
         if (e instanceof Deplacable) {
            ((Deplacable) e).deplacer();
         }
         // Si un être vivant n'a plus de point de vie, on l'ajoute à la liste des éléments à supprimer
         if (e.supprimer()) {
            toRemove.add(e);
         }
      }

      // Supprime les éléments de la liste deplacables qui sont morts
      entites.removeAll(toRemove);

      // Si tous les monstres sont morts, on l'affiche
      int nbMonstres = 0;
      for (Entite d : entites) {
         if (d instanceof Monstre) {
            nbMonstres++;
         }
      }
      if (nbMonstres == 0) {
         for (Entite d : entites) {
            if (d instanceof Escalier) {
               ((Escalier) d).debloque = true;
            }
         }
      }
   }


   // ##################################
   // GETTER
   // ##################################

   /**
    * return taille selon Y
    *
    * @return taille selon Y
    */
   public int getLengthY() {
      return murs[0].length;
   }

   /**
    * return taille selon X
    *
    * @return taille selon X
    */
   public int getLengthX() {
      return murs.length;
   }

   /**
    * return mur en (i,j)
    *
    * @param x coordonnee x
    * @param y coordonnee y
    * @return mur en (i,j)
    */
   public boolean getMur(int x, int y) {
      // utilise le tableau de boolean
      return murs[x][y];
   }
}
