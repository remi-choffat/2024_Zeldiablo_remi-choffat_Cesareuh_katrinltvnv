package gameLaby.laby;

import moteurJeu.Clavier;

import java.io.IOException;

public class LabyJeu implements moteurJeu.Jeu {

   public final Labyrinthe laby;
   private String direction = "";
   double debut = 0;

   /**
    * Constructeur de LabyJeu
    *
    * @throws IOException
    */
   public LabyJeu() throws IOException {
      laby = new Labyrinthe("labySimple/laby0.txt");
   }

   /**
    * Met a jour le jeu
    *
    * @param secondes temps ecoule depuis la derniere mise a jour
    * @param clavier  objet contenant l'état du clavier
    */
   @Override
   public void update(double secondes, Clavier clavier) {
      debut += secondes;
      if (clavier.droite) {
         Labyrinthe.pj.setDirection(Labyrinthe.DROITE);
      }

      if (clavier.gauche) {
         Labyrinthe.pj.setDirection(Labyrinthe.GAUCHE);
      }

      if (clavier.haut) {
         Labyrinthe.pj.setDirection(Labyrinthe.HAUT);
      }

      if (clavier.bas) {
         Labyrinthe.pj.setDirection(Labyrinthe.BAS);
      }

      if (clavier.space) {
         Labyrinthe.pj.lancerFleche();
         Labyrinthe.pj.setDirection("");
      }

      if (!(clavier.droite || clavier.gauche || clavier.haut || clavier.bas)) {
         Labyrinthe.pj.setDirection("");
      }


      if (debut > .5) {
         Labyrinthe.updateLaby();
         debut = 0;
      }

      // Si le personnage n'a plus de point de vie, on arrête le jeu
      if (etreFini()) {
         System.out.println("Game Over");
         System.exit(0);
      }

   }

   @Override
   public void init() {

   }

   /**
    * Fin du jeu quand le personnage n'a plus de point de vie
    *
    * @return fin du jeu
    */
   @Override
   public boolean etreFini() {
      return (Labyrinthe.pj.getPv() <= 0);
   }
}
