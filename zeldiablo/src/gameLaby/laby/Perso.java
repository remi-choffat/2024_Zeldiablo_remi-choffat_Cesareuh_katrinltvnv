package gameLaby.laby;

import java.util.Objects;

/**
 * Représente le personnage du jeu
 */
public class Perso extends Vivant implements Attaque {

   /**
    * constructeur
    *
    * @param dx position selon x
    * @param dy position selon y
    */
   public Perso(int dx, int dy) {
      super(dx, dy, 1, 10);
   }

   @Override
   public void infligerDegats(Vivant v) {
      v.subirDegats(1);
   }

   public void lancerFleche() {
      if (!Objects.equals(this.getDirection(), "")) {
         int[] pos = Labyrinthe.getSuivant(this.getX(), this.getY(), this.getDirection());
         Fleche f = new Fleche(pos[0], pos[1]);
         f.setDirection(this.getDirection());
      }
   }

}
