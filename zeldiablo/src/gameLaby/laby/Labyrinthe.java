package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Représente un labyrinthe avec
 * <ul>
 *    <li> des éléments déplaçables (personnage, monstres...) </li>
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
   public static ArrayList<Deplacable> deplacables = new ArrayList<>();

   /**
    * retourne la case suivante selon une action
    *
    * @param x      case depart
    * @param y      case depart
    * @param action action effectuee
    * @return case suivante
    */
   static int[] getSuivant(int x, int y, String action) {
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
         default:
            throw new Error("Action inconnue : " + action);
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
      // ouverture fichier
      FileReader fichier = new FileReader(nom);
      BufferedReader bfRead = new BufferedReader(fichier);

      int nbLignes, nbColonnes;
      // lecture nblignes
      nbLignes = Integer.parseInt(bfRead.readLine());
      // lecture nbcolonnes
      nbColonnes = Integer.parseInt(bfRead.readLine());

      // creation labyrinthe vide
      murs = new boolean[nbColonnes][nbLignes];
      deplacables = new ArrayList<>();

      // lecture des cases
      String ligne = bfRead.readLine();

      // stocke les indices courants
      int numeroLigne = 0;

      // parcourt le fichier
      while (ligne != null) {

         // parcours de la ligne
         for (int colonne = 0; colonne < ligne.length(); colonne++) {
            char c = ligne.charAt(colonne);
            switch (c) {
               case MUR:
                  murs[colonne][numeroLigne] = true;
                  break;
               case VIDE:
                  murs[colonne][numeroLigne] = false;
                  break;
               case PJ:
                  // pas de mur
                  murs[colonne][numeroLigne] = false;
                  // ajoute PJ
                  pj = new Perso(colonne, numeroLigne);
                  break;
               case MONSTRE:
                  // pas de mur
                  murs[colonne][numeroLigne] = false;
                  // ajoute monstre
                  new Monstre(colonne, numeroLigne);
                  break;

               default:
                  throw new Error("Caractère inconnu : " + c);
            }
         }

         // lecture
         ligne = bfRead.readLine();
         numeroLigne++;
      }

      // ferme fichier
      bfRead.close();
   }


   /**
    * deplace le personnage en fonction de l'action.
    * gere la collision avec les murs
    *
    * @param action une des actions possibles
    */
   public void deplacerPerso(String action) {
      // Change la direction du personnage
      pj.setDirection(action);

      // Liste pour stocker les éléments à supprimer
      ArrayList<Deplacable> toRemove = new ArrayList<>();

      // Déplace tous les objets déplaçables
      for (Deplacable d : deplacables) {
         d.deplacer();
         // Si un être vivant n'a plus de point de vie, on l'ajoute à la liste des éléments à supprimer
         if (d instanceof Vivant v) {
            // On affiche en console les points de vie des êtres vivants
            System.out.println("Pv " + d.getClass().getSimpleName() + " : " + v.getPv());
         }

         if(d.supprimer()){
            toRemove.add(d);
         }
      }

      // Supprime les éléments de la liste deplacables qui sont morts
      deplacables.removeAll(toRemove);

      // Si le personnage n'a plus de point de vie, on arrête le jeu
      if (pj.getPv() <= 0) {
         System.out.println("Game Over");
         System.exit(0);
      }
   }

   /**
    * jamais fini
    *
    * @return fin du jeu
    */
   public boolean etreFini() {
      return false;
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
