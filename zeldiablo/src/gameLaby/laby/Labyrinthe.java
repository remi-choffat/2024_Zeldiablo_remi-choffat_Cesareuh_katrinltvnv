package gameLaby.laby;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
    * Indique si le niveau doit être changé
    */
   private static boolean niveauAChanger = false;

   /**
    * Prochain niveau
    */
   private static int prochainNiveau;


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
      placerEntites(1);

   }


   /**
    * change le niveau
    *
    * @param levelIndex index du niveau
    * @throws IllegalArgumentException si le niveau n'existe pas
    * @throws IOException              si probleme a la lecture du fichier
    */
   public static void changerNiveau(int levelIndex) throws IllegalArgumentException, IOException {
      niveauAChanger = true;
      prochainNiveau = levelIndex;
   }


   /**
    * Place les entites aléatoirement dans le labyrinthe
    *
    * @param levelIndex index du niveau
    */
   public static void placerEntites(int levelIndex) {

      // Nombre de monstres à ajouter
      int nbMonstres = levelIndex;

      // Liste des positions possibles
      ArrayList<int[]> positionsPossibles = new ArrayList<>();
      for (int x = 0; x < murs.length; x++) {
         for (int y = 0; y < murs[0].length; y++) {
            // Si la case n'est pas un mur et n'est pas la position du joueur
            // on ajoute la position à la liste des positions possibles
            if (!murs[x][y] && !(x == pj.getX() && y == pj.getY())) {
               positionsPossibles.add(new int[]{x, y});
            }
         }
      }

      // Mélange la liste des positions possibles
      Collections.shuffle(positionsPossibles);

      // Ajoute les monstres
      for (int i = 0; i < nbMonstres; i++) {
         int[] pos = positionsPossibles.remove(0);
         entites.add(new Monstre(pos[0], pos[1]));
      }

      // Ajoute l'escalier de sortie
      int[] posEscalier = positionsPossibles.remove(0);
      entites.add(new Escalier(posEscalier[0], posEscalier[1], levelIndex));
   }


   /**
    * Met à jour le labyrinthe
    */
   public static void updateLaby() {

      // Liste pour stocker les éléments à supprimer
      ArrayList<Entite> toRemove = new ArrayList<>();

      // Déplace ou supprime les éléments
      for (Entite e : entites) {
         // Si l'élément est déplaçable, on le déplace
         if (e instanceof Deplacable) {
            ((Deplacable) e).deplacer();
         }
         // Si élément doit être supprimé, on l'ajoute à la liste des éléments à supprimer
         if (e.supprimer()) {
            toRemove.add(e);
         }
      }

      // Supprime les éléments qui sont morts de la liste des entités
      entites.removeAll(toRemove);

      // Si tous les monstres sont morts, on débloque l'escalier de sortie
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

      // Change le niveau si nécessaire (joueur sur l'escalier débloqué)
      if (niveauAChanger) {
         // Génère un nouveau labyrinthe
         murs = GenerationLaby.creer();
         // Place les entités dans le labyrinthe
         placerEntites(prochainNiveau);
         // Ajoute 4 points de vie au personnage (dans la limite de ses points de vie max)
         pj.addPv(4);
         // Ajoute 5 points au personnage
         pj.addPoints(5, "Passage au niveau " + prochainNiveau);
         niveauAChanger = false;
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
