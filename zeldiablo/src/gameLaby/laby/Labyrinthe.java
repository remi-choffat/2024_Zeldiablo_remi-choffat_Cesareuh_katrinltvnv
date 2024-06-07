package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
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
      entites = new ArrayList<>();

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
               case ESCALIER:
                  murs[colonne][numeroLigne] = false;
                  new Escalier(colonne, numeroLigne, allLevels.size());
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

      allLevels.add(this);

   }

   /**
    * change le niveau
    *
    * @param levelIndex index du niveau
    * @throws IllegalArgumentException si le niveau n'existe pas
    * @throws IOException              si probleme a la lecture du fichier
    */
   public static void changerNiveau(int levelIndex) throws IllegalArgumentException, IOException {
      new Labyrinthe("labySimple/laby" + levelIndex + ".txt");
      if (levelIndex < 0 || levelIndex >= allLevels.size()) {
         throw new IllegalArgumentException("Le niveau " + levelIndex + " n'existe pas");
      }
      Perso persoNiveauActuel = pj;
      currentLabyrinthe = allLevels.get(levelIndex);
      entites.add(persoNiveauActuel);
      pj = persoNiveauActuel;
      while (currentLabyrinthe.getMur(pj.getX(), pj.getY())) {
         // Si la position de départ est un mur, trouve une nouvelle position
         pj.setX((pj.getX() + 1) % currentLabyrinthe.getLengthX());
         pj.setY((pj.getY() + 1) % currentLabyrinthe.getLengthY());
      }
      System.out.println("Passage au niveau " + levelIndex);
   }

   /**
    * deplace le personnage en fonction de l'action.
    * gere la collision avec les murs
    */
   public void updateLaby() {

      // Liste pour stocker les éléments à supprimer
      ArrayList<Entite> toRemove = new ArrayList<>();

      // Déplace tous les objets déplaçables
      for (Entite d : entites) {
         if (d instanceof Deplacable) {
            ((Deplacable) d).deplacer();
            // Si un être vivant n'a plus de point de vie, on l'ajoute à la liste des éléments à supprimer
            if (d.supprimer()) {
               toRemove.add(d);
            }
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
