package gameLaby.laby;

import java.io.IOException;

/**
 * Class Escalier
 */
public class Escalier extends Entite {

   // l'indice de niveau
   private int inxLevel;

   //si l'escalier monte(true) ou descends(false)
   public boolean montant;

   /**
    * constructeur
    *
    * @param x         position selon x
    * @param y         position selon y
    * @param dinxLevel index du niveau auquel
    * @param montant   si l'escalier monte
    */
   public Escalier(int x, int y, int dinxLevel, boolean montant) {
      super(x, y);
      this.inxLevel = dinxLevel;
      this.montant = montant;
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
    * gère la collision avec un autre élément
    *
    * @param d élément avec lequel il y a collision
    */
   @Override
   public void collision(Entite d) {
      if (d instanceof Deplacable deplacableEntity) {
         NextLevel(deplacableEntity);
      }
   }

   /**
    * Fait passer l'entite deplacable donnee au niveau suivant
    * Si l'escalier monte, l'entite passe au niveau suivant (inxLevel + 1)
    * Si l'escalier descend, l'entite passe au niveau precedent (inxLevel - 1).
    *
    * @param deplacableEntity entite a deplacer au niveau suivant
    */
   private void NextLevel(Deplacable deplacableEntity) {

      int newLevel = this.montant ? this.inxLevel + 1 : this.inxLevel - 1;

      // Charger le nouveau niveau
      Labyrinthe newLabyrinthe = null;
      try {
         newLabyrinthe = new Labyrinthe("labySimple/laby" + newLevel + ".txt");
      } catch (IOException e) {
         System.out.println("Erreur lors du chargement du niveau " + newLevel);
      }
      newLabyrinthe = Labyrinthe.loadLevel(newLevel);

      // met a jour la position de l'entite deplacable aux coordonnees correspondantes dans le nouveau niveau
      deplacableEntity.setX(this.getX());
      deplacableEntity.setY(this.getY());

      // met a jour le labyrinthe actuel
      Labyrinthe.currentLabyrinthe = newLabyrinthe;
   }

}
