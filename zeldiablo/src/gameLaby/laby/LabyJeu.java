package gameLaby.laby;

import moteurJeu.Clavier;

import java.io.IOException;

public class LabyJeu implements moteurJeu.Jeu {

    public final Labyrinthe laby;
    private String direction = "";
    double debut = 0;


    public LabyJeu() throws IOException {
        laby = new Labyrinthe("labySimple/labyBase.txt");
    }

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

        if(clavier.space){
            Labyrinthe.pj.lancerFleche();
            Labyrinthe.pj.setDirection("");
        }

        if(!(clavier.droite || clavier.gauche || clavier.haut || clavier.bas)){
            Labyrinthe.pj.setDirection("");
        }



        if(debut > .5){

            laby.updateLaby();
            debut = 0;
        }

        /*
        if (clavier.space) {
            Labyrinthe.pj.lancerFleche();
        }

         */
    }

    @Override
    public void init() {

    }

    @Override
    public boolean etreFini() {
        return false;
    }
}
