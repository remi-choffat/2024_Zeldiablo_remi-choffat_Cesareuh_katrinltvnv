package gameLaby.laby;

/**
 * Représente une flèche qui peut être tirée par le joueur
 */
public class Fleche extends Deplacable implements Attaque {

   public boolean toRemove = false;

   /**
    * constructeur
    *
    * @param x position selon x
    * @param y position selon y
    */
   public Fleche(int x, int y, String direction) {
      super(x, y, 1);
      this.setDirection(direction);
   }

   /**
    * inflige des degats a un vivant
    *
    * @param v vivant a attaquer
    */
   @Override
   public void infligerDegats(Vivant v) {
      // Une flèche inflige 2 dégâts
      v.subirDegats(2);
   }

   /**
    * collision avec une entité
    *
    * @param d entité en collision
    */
   @Override
   public void collision(Entite d) {
      if (d instanceof Vivant) {
         this.infligerDegats((Vivant) d);
      }
      this.toRemove = true;
   }

   public boolean supprimer() {
      int[] suivante = Labyrinthe.getSuivant(getX(), getY(), getDirection());
      boolean mort = this.toRemove || (Labyrinthe.murs[suivante[0]][suivante[1]]);
      if (mort) {
         Labyrinthe.pj.peutLancerFleche = true;
      }
      return mort;
   }

}
