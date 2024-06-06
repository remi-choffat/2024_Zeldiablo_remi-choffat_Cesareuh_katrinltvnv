package gameLaby.laby;

public abstract class Deplacable extends Entite {
   private int x;
   private int y;
   private final int nb_mouvements;
   private String direction = "";

   /**
    * constructeur
    *
    * @param x  position selon x
    * @param y  position selon y
    * @param mv nombre de mouvements
    */
   public Deplacable(int x, int y, int mv) {
      super(x, y);
      this.nb_mouvements = mv;
   }

   /**
    * Change la direction du déplacement
    *
    * @param direction direction du déplacement
    */
   public void setDirection(String direction) {
      this.direction = direction;
   }

   /**
    * Renvoie la direction du déplacement
    *
    * @return direction du déplacement
    */
   public String getDirection() {
      return this.direction;
   }

   /**
    * deplace l'élément en fonction de sa direction et des obstacles
    */
   public void deplacer() {
      for (int i = 0; i < this.nb_mouvements; i++) {
         int[] suivante = Labyrinthe.getSuivant(this.getX(), this.getY(), this.direction);

         if (Labyrinthe.murs[suivante[0]][suivante[1]]) {
            return;
         }

         if (direction.isEmpty()) {
            return;
         }

         for (Entite d : Labyrinthe.entites) {
            if (d.etrePresent(suivante[0], suivante[1])) {
               this.collision(d);
               d.collision(this);
               return;
            }
         }
         this.setX(suivante[0]);
         this.setY(suivante[1]);
      }
   }

}
