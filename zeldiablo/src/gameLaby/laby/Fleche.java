package gameLaby.laby;

/**
 * Représente une flèche qui peut être tirée par le joueur
 */
public class Fleche extends Deplacable implements Attaque {

   public boolean toRemove = false;

   /**
    * Constructeur
    *
    * @param x position selon x
    * @param y position selon y
    */
   public Fleche(int x, int y, String direction) {
      super(x, y, 1);
      this.setDirection(direction);
   }

   /**
    * Inflige des degats a un vivant
    *
    * @param v vivant a attaquer
    */
   @Override
   public void infligerDegats(Vivant v) {
      // Une flèche inflige 4 dégâts
      v.subirDegats(4, this);
   }

   /**
    * Collision avec une entité
    *
    * @param e entité en collision
    */
   @Override
   public void collision(Entite e) {
      if (e instanceof Vivant) {
         this.infligerDegats((Vivant) e);
      }
      if (!(e instanceof Escalier)) {
         this.toRemove = true;
      }
   }

   /**
    * Indique si la flèche doit être supprimée
    *
    * @return vrai si la flèche est en collision avec un mur ou si elle a déjà touché une entité
    */
   public boolean supprimer() {
      int[] suivante = Labyrinthe.getSuivant(getX(), getY(), getDirection());
      boolean mort = this.toRemove || (Labyrinthe.murs[suivante[0]][suivante[1]]);
      if (mort) {
         Labyrinthe.pj.peutLancerFleche = true;
      }
      return mort;
   }

}
