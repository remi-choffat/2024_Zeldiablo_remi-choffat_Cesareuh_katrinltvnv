package gameLaby.laby;

/**
 * Représente le personnage du jeu
 */
public class Perso extends Vivant {

   /**
    * Indique si le personnage peut lancer une flèche
    */
   boolean peutLancerFleche = true;

   /**
    * Nombre de points
    */
   private int nbPoints = 0;

   /**
    * Nombre de flèches disponibles
    */
   private int nbFleches = 0;


   /**
    * constructeur
    *
    * @param dx position selon x
    * @param dy position selon y
    * @param speed vitesse de deplacement
    */
   public Perso(int dx, int dy, double speed) {
      super(dx, dy, 1, 20, speed);
   }


   /**
    * déplace le personnage
    * @param deltaTime temps écoulé depuis la dernière mise à jour
    */
   public void deplacer(double deltaTime) {
      super.deplacer(deltaTime);
      this.setDirection("");
   }


   /**
    * inflige des dégats à un vivant
    *
    * @param v vivant à attaquer
    */
   @Override
   public void infligerDegats(Vivant v) {
      v.subirDegats(1, this);
   }


   /**
    * lance une flèche
    */
   public void lancerFleche() {
      // Si le personnage a une direction
      if (!this.getDirection().equals("") && this.peutLancerFleche && this.nbFleches > 0) {
         int[] pos = Labyrinthe.getSuivant(this.getX(), this.getY(), this.getDirection());
         // Si la case suivante n'est pas un mur
         if (!Labyrinthe.murs[pos[0]][pos[1]]) {
            Fleche f = new Fleche(pos[0], pos[1], this.getDirection(), 5);
            f.setDirection(this.getDirection());
            this.peutLancerFleche = false;
         }
         this.nbFleches--;
         System.out.println(this.nbFleches + " flèches restantes");
      }
   }


   /**
    * Renvoie le nombre de points du personnage
    *
    * @return nombre de points
    */
   public int getPoints() {
      return this.nbPoints;
   }


   /**
    * Ajoute des points
    *
    * @param points points à ajouter
    */
   public void addPoints(int points, String message) {
      if (points < 0 && this.nbPoints + points < 0) {
         this.nbPoints = 0;
         return;
      }
      this.nbPoints += points;
      System.out.println(message + " : " + points + " points → " + this.nbPoints);
   }


   /**
    * Subit des degats
    *
    * @param degats    degats subits
    * @param attaquant entite attaquante
    */
   @Override
   public void subirDegats(int degats, Entite attaquant) {
      super.subirDegats(degats);
   }


   /**
    * Ajoute des flèches
    *
    * @param nbFleches nombre de flèches à ajouter
    */
   public void addFleches(int nbFleches) {
      this.nbFleches += nbFleches;
   }


}
