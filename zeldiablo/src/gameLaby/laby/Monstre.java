package gameLaby.laby;

/**
 * Représente un monstre
 */
public class Monstre extends Vivant {

   /**
    * constructeur
    *
    * @param dx position selon x
    * @param dy position selon y
    */
   public Monstre(int dx, int dy) {
      super(dx, dy, 1, 4);
   }


   /**
    * deplace le monstre
    * le monstre se déplace aléatoirement
    */
   public void deplacer() {
      String[] actions = {Labyrinthe.HAUT, Labyrinthe.BAS, Labyrinthe.GAUCHE, Labyrinthe.DROITE};
      String direction = actions[(int) (Math.random() * actions.length - .001)];
      this.setDirection(direction);

      super.deplacer();
   }


   /**
    * inflige des dégats à un vivant
    *
    * @param v vivant à attaquer
    */
   public void infligerDegats(Vivant v) {
      v.subirDegats(1, this);
   }


   /**
    * Subit des dégâts
    *
    * @param degats    dégâts subits
    * @param attaquant entité attaquante
    */
   @Override
   public void subirDegats(int degats, Entite attaquant) {
      // Si le monstre est déjà mort, ne fait rien
      if (this.getPv() <= 0) {
         return;
      }
      // Fait subir des dégâts au monstre
      super.subirDegats(degats);
      // Si le monstre est mort après avoir subi des dégâts
      if (this.getPv() <= 0) {
         // Si l'attaquant est le personnage ou une flèche lancée par le personnage
         if (attaquant instanceof Perso) {
            // Ajoute 10 points au personnage
            Labyrinthe.pj.addPoints(10, "Monstre tué par attaque directe");
         } else if (attaquant instanceof Fleche) {
            // Ajoute 10 points au personnage
            Labyrinthe.pj.addPoints(10, "Monstre tué par attaque à distance");
         } else {
            // Soustrait 2 points au personnage
            Labyrinthe.pj.addPoints(-2, "Monstre tué par un autre monstre");
         }
      }
   }

}
