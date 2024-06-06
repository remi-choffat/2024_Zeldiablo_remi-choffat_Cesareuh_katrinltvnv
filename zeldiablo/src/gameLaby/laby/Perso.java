package gameLaby.laby;

/**
 * Représente le personnage du jeu
 */
public class Perso extends Vivant {

   /**
    * constructeur
    *
    * @param dx position selon x
    * @param dy position selon y
    */
   public Perso(int dx, int dy) {
      super(dx, dy, 1, 10);
   }

   /**
    * inflige des dégats à un vivant
    *
    * @param v vivant à attaquer
    */
   @Override
   public void infligerDegats(Vivant v) {
      v.subirDegats(1);
   }

   /**
    * lance une flèche
    */
   public void lancerFleche() {
      // Si le personnage a une direction
      if (!this.getDirection().equals("")) {
         int[] pos = Labyrinthe.getSuivant(this.getX(), this.getY(), this.getDirection());
         // Si la case suivante n'est pas un mur
         if (!Labyrinthe.murs[pos[0]][pos[1]]) {
            Fleche f = new Fleche(pos[0], pos[1]);
            f.setDirection(this.getDirection());
         }
      }
   }

}
