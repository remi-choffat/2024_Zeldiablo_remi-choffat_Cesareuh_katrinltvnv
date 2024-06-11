package gameLaby.laby;

/**
 * Représente un élément vivant dans un labyrinthe
 */
public abstract class Vivant extends Deplacable implements Attaque, Victime {

   /**
    * Nombre de points de vie de l'être vivant
    */
   private int pv;

   /**
    * Nombre de points de vie initial
    */
   private final int initialPv;


   /**
    * constructeur
    *
    * @param dx position selon x
    * @param dy position selon y
    * @param pv nombre de points de vie
    */
   public Vivant(int dx, int dy, int pv) {
      super(dx, dy);
      this.pv = pv;
      this.initialPv = pv;
   }


   /**
    * Subit des degats
    *
    * @param d degats subits
    */
   public void subirDegats(int d) {
      this.addPv(-d);
   }


   /**
    * Subit des degats d'une source
    *
    * @param degats    degats subits
    * @param attaquant entite attaquante
    */
   public abstract void subirDegats(int degats, Entite attaquant);


   /**
    * collision avec un deplacable
    *
    * @param d deplacable en collision
    */
   public void collision(Entite d) {
      if (d instanceof Vivant) {
         this.infligerDegats((Vivant) d);
      }
   }


   /**
    * Indique si l'entité doit être supprimée (points de vie <= 0)
    *
    * @return true si l'entité doit être supprimée
    */
   @Override
   public boolean supprimer() {
      return this.getPv() <= 0;
   }


   /**
    * Renvoie le nombre de points de vie
    *
    * @return nombre de points de vie
    */
   public int getPv() {
      return this.pv;
   }


   /**
    * Change le nombre de points de vie
    *
    * @param ajoutPv points de vie à ajouter
    */
   public void addPv(int ajoutPv) {
      if (this.pv + ajoutPv < 0) {
         this.pv = 0;
      } else {
         if (this.pv + ajoutPv > this.initialPv) {
            ajoutPv = this.initialPv - this.pv;
         }
         this.pv += ajoutPv;
      }
   }


   /**
    * Renvoie le nombre de points de vie initial
    *
    * @return nombre de points de vie initial
    */
   public int getInitialPv() {
      return this.initialPv;
   }

}