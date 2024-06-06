package gameLaby.laby;

public class Fleche extends Deplacable implements Attaque {

   public boolean toRemove = false;

   /**
    * constructeur
    *
    * @param x position selon x
    * @param y position selon y
    */
   public Fleche(int x, int y) {
      super(x, y, 1);
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
    * collision avec un deplacable
    *
    * @param d deplacable en collision
    */
   @Override
   public void collision(Deplacable d) {
      if (d instanceof Vivant) {
         this.infligerDegats((Vivant) d);
      }
      this.toRemove = true;
   }

   public boolean supprimer(){
      int [] suivante = Labyrinthe.getSuivant(getX(), getY(), getDirection());
      return (this.toRemove || (Labyrinthe.murs[suivante[0]][suivante[1]]));
   }
}
