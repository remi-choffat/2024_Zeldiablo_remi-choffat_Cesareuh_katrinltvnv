package gameLaby.laby;

import moteurJeu.Clavier;

import java.io.IOException;

public class LabyJeu implements moteurJeu.Jeu {

   public final Labyrinthe laby;
   boolean deplacement = false;

   public LabyJeu() throws IOException {
      laby = new Labyrinthe("labySimple/labyTest.txt");
   }

   @Override
   public void update(double secondes, Clavier clavier) {
      if (!deplacement) {
         if (clavier.droite) {
            laby.deplacerPerso(Labyrinthe.DROITE);
         }

         if (clavier.gauche) {
            laby.deplacerPerso(Labyrinthe.GAUCHE);
         }

         if (clavier.haut) {
            laby.deplacerPerso(Labyrinthe.HAUT);
         }

         if (clavier.bas) {
            laby.deplacerPerso(Labyrinthe.BAS);
         }

         if (clavier.space) {
            Labyrinthe.pj.lancerFleche();
         }
      }
      deplacement = (clavier.droite || clavier.gauche || clavier.haut || clavier.bas || clavier.space);
   }

   @Override
   public void init() {

   }

   @Override
   public boolean etreFini() {
      return false;
   }
}
