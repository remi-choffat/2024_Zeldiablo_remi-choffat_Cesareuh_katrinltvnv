package gameLaby.laby;

import moteurJeu.Clavier;

import java.io.IOException;

/**
 * Gère le déroulement du jeu
 */
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

      if (clavier.shoot) {
         Labyrinthe.pj.lancerFleche();
         Labyrinthe.pj.setDirection("");
      }

      if (!(clavier.droite || clavier.gauche || clavier.haut || clavier.bas)) {
         Labyrinthe.pj.setDirection("");
      }

      if (debut > .25) {
         Labyrinthe.updateLaby();
         debut = 0;
      }

      // Si le personnage n'a plus de point de vie, on arrête le jeu
      if (etreFini()) {
         System.out.println("GAME OVER");
         System.out.println("Score : " + Labyrinthe.pj.getPoints() + " points");
         System.out.println("Niveau : " + Labyrinthe.prochainNiveau);
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
