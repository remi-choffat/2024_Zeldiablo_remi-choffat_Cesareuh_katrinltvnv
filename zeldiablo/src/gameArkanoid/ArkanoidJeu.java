package gameArkanoid;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

/**
 * represente un jeu de type casse brique : possede une raquette et une balle
 */
public class ArkanoidJeu implements Jeu {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    /**
     * raquette et balle
     */
    private final Raquette raquette;
    private final Balle balle;


    /**
     * constructeur par defaut
     */
    public ArkanoidJeu() {
        this.raquette = new Raquette(WIDTH, HEIGHT);
        this.balle = new Balle(WIDTH, HEIGHT);
    }


    @Override
    /**
     * met a jour l'etat du jeu
     */
    public void update(double secondes, Clavier clavier) {

        // deplace la raquette en fonction des touches
        if (clavier.droite) {
            this.raquette.allerDroite(WIDTH, secondes);
        }

        if (clavier.gauche) {
            this.raquette.allerGauche(WIDTH, secondes);
        }

        // fait evoluer la balle
        this.balle.evoluer(secondes);

        // teste si la raquette retourne la balle
        this.balle.collision(raquette);
    }


    @Override
    public void init() {
        // pas d'initialisation particuliere
    }

    @Override
    public boolean etreFini() {
        // le jeu ne s'arrete jamais
        return false;
    }


    /*#######################################
    # GETTER
    ######################################### */

    public Raquette getRaquette() {
        return this.raquette;
    }

    public Balle getBalle() {
        return balle;
    }
}
