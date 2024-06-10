package gameLaby.laby;

/**
 * Représente un élément déplaçable
 */
public abstract class Deplacable extends Entite {

   private final int nb_mouvements;
   private String direction = "";

   private double speed;
   private double movementCounter = 0;

   /**
    * constructeur
    *
    * @param x  position selon x
    * @param y  position selon y
    * @param mv nombre de mouvements
    * @param speed vitesse de deplacement
    */
   public Deplacable(int x, int y, int mv, double speed) {
      super(x, y);
      this.nb_mouvements = mv;
      this.speed = speed;
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
    * Déplace l'élément en fonction de sa direction et des obstacles
    * @param deltaTime temps écoulé depuis la dernière mise à jour
    */
   public void deplacer(double deltaTime) {
      movementCounter += speed * deltaTime;
      if (movementCounter >= 1) {
         for (int i = 0; i < this.nb_mouvements; i++) {
            int[] avant = {this.getX(), this.getY()};
            int[] suivante = Labyrinthe.getSuivant(this.getX(), this.getY(), this.direction);

            if (Labyrinthe.murs[suivante[0]][suivante[1]]) {
               return;
            }

            if (direction.isEmpty()) {
               return;
            }

            // On déplace l'élément
            this.setX(suivante[0]);
            this.setY(suivante[1]);

            for (Entite d : Labyrinthe.entites) {
               if (d.etrePresent(suivante[0], suivante[1]) && d != this) {
                  this.collision(d);
                  d.collision(this);
                  // Les escaliers ne bloquent pas le passage
                  if (!(d instanceof Escalier)) {
                     // On remet à la position initiale
                     this.setX(avant[0]);
                     this.setY(avant[1]);
                     return;
                  }
               }
            }
         }
         movementCounter = 0;
      }
   }

}
