package gameLaby.laby;

import java.io.IOException;

/**
 * Représente un Escalier
 */
public class Escalier extends Entite {

   /**
    * Indice du niveau
    */
   private final int inxLevel;

   /**
    * Indique si l'escalier est debloqué
    */
   public boolean debloque = false;

   /**
    * constructeur
    *
    * @param x         position selon x
    * @param y         position selon y
    * @param dinxLevel index du niveau auquel
    */
   public Escalier(int x, int y, int dinxLevel) {
      super(x, y);
      this.inxLevel = dinxLevel;
   }

   /**
    * Indique si l'entité doit être supprimée
    *
    * @return true si l'entité doit être supprimée
    */
   @Override
   public boolean supprimer() {
      return false;
   }

   /**
    * gère la collision avec le personnage
    * fait monter de niveau
    *
    * @param d élément avec lequel il y a collision
    */
   @Override
   public void collision(Entite d) {
      if (d instanceof Perso && debloque) {
         int newLevel = this.inxLevel + 1;
         try {
            Labyrinthe.changerNiveau(newLevel);
         } catch (IOException e) {
            System.out.println("Erreur lors du changement du niveau " + newLevel);
         }
      }
   }

}
