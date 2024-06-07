package gameLaby.laby;

import javafx.scene.image.ImageView;

/**
 * Représente une entité du labyrinthe
 */
public abstract class Entite {

   private int x;
   private int y;
   private ImageView imageView;

   /**
    * constructeur
    *
    * @param x position selon x
    * @param y position selon y
    */
   public Entite(int x, int y) {
      this.x = x;
      this.y = y;
      Labyrinthe.entites.add(this);
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

   /**
    * Indique si l'entité doit être supprimée
    *
    * @return true si l'entité doit être supprimée
    */
   public abstract boolean supprimer();

   /**
    * gère la collision avec un autre élément
    *
    * @param d élément avec lequel il y a collision
    */
   public abstract void collision(Entite d);

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

   /**
    * Modifie X
    *
    * @param x nouvelle valeur de X
    */
   public void setX(int x) {
      this.x = x;
   }

   /**
    * Modifie Y
    *
    * @param y nouvelle valeur de Y
    */
   public void setY(int y) {
      this.y = y;
   }

   /**
    * * Renvoie l'ImageView correspondant à l'entité
    *
    * @return ImageView correspondant à l'entité
    */
   public ImageView getImageView() {
      return this.imageView;
   }

   /**
    * Modifie l'ImageView
    *
    * @param imageView nouvelle valeur de l'ImageView
    */
   public void setImageView(ImageView imageView) {
      this.imageView = imageView;
   }
}
