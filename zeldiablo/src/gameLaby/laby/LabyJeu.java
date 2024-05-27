package gameLaby.laby;


import moteurJeu.Clavier;

import java.io.IOException;

public class LabyJeu implements moteurJeu.Jeu {

    private final Labyrinthe laby;

    public LabyJeu() throws IOException {
        laby = new Labyrinthe("labySimple/laby1.txt");
    }

    @Override
    public void update(double secondes, Clavier clavier) {
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
    }

    @Override
    public void init() {

    }

    @Override
    public boolean etreFini() {
        return false;
    }
}
