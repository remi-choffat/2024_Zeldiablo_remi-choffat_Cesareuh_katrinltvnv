package gameLaby.laby;

public abstract class Deplacable {
   private int x;
   private int y;
   private final int nb_mouvements;
   private String direction = "";
   private boolean toucherMur = false;

   /**
    * constructeur
    *
    * @param x  position selon x
    * @param y  position selon y
    * @param mv nombre de mouvements
    */
   public Deplacable(int x, int y, int mv) {
      this.x = x;
      this.y = y;
      this.nb_mouvements = mv;
      Labyrinthe.deplacables.add(this);
   }

   /**
    * permet de savoir si l'élément est présent en x,y
    *
    * @param dx position testee
    * @param dy position testee
    * @return true si quelqu'un est bien en (dx,dy)
    */
   public boolean etrePresent(int dx, int dy) {
      return (this.x == dx && this.y == dy);
   }
   public abstract boolean supprimer();

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
         int[] suivante = Labyrinthe.getSuivant(this.x, this.y, this.direction);

         if (Labyrinthe.murs[suivante[0]][suivante[1]]) {
            this.toucherMur = true;
            return;
         }

         if (direction.isEmpty()) {
            return;
         }

         for (Deplacable d : Labyrinthe.deplacables) {
            if (d.etrePresent(suivante[0], suivante[1])) {
               this.collision(d);
               d.collision(this);
               return;
            }
         }
         this.x = suivante[0];
         this.y = suivante[1];
         this.toucherMur = false;
      }
   }


   ;

   /**
    * gère la collision avec un autre élément
    *
    * @param d élément avec lequel il y a collision
    */
   public abstract void collision(Deplacable d);

   /**
    * @return position x dans un labyrinthe
    */
   public int getX() {
      // getter
      return this.x;
   }


   /**
    * @return position y dans un labyrinthe
    */
   public int getY() {
      //getter
      return this.y;
   }
}
