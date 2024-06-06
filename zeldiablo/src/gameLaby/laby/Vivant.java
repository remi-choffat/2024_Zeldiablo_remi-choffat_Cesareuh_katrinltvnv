package gameLaby.laby;

/**
 * Classe pour représenter un élément vivant dans un labyrinthe
 */
public abstract class Vivant extends Deplacable implements Attaque, Victime {

   /**
    * position dans un labyrinthe
    */
   int pv;

   /**
    * constructeur
    *
    * @param dx position selon x
    * @param dy position selon y
    * @param mv nombre de mouvements
    * @param pv nombre de points de vie
    */
   public Vivant(int dx, int dy, int mv, int pv) {
      super(dx, dy, mv);
      this.pv = pv;
   }

   /**
    * Subit des degats d'une source
    *
    * @param d degats subits
    */
   public void subirDegats(int d) {
      this.pv -= d;
   }

   /**
    * collision avec un deplacable
    *
    * @param d deplacable en collision
    */
   public void collision(Deplacable d) {
      if (d instanceof Vivant) {
         this.infligerDegats((Vivant) d);
      }
   }

   /**
    * Renvoie le nombre de points de vie
    *
    * @return nombre de points de vie
    */
   public int getPv() {
      return this.pv;
   }

}