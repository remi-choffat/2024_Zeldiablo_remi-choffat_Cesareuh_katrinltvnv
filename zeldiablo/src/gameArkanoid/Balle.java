package gameArkanoid;

/**
 * represente une balle avec une position et une vitesse
 */
public class Balle {

    /**
     * position de la balle
     */
    double posx, posy;

    /**
     * vitesse de la balle
     */
    double vx, vy;

    /**
     * taille ecran
     */
    double tailleEcran;

    /**
     * si la balle est en dessous de la raquette
     */
    boolean balleSousRaquette;


    public Balle(int width, int height) {
        // initialisation
        this.posx = width / 2.;
        this.posy = height / 3.;
        this.vx = width / 3.;
        this.vy = height / 2.;
        this.tailleEcran = width;

        // la balle est au dessus de la raquette
        this.balleSousRaquette = false;
    }

    /**
     * fait evoluer la balle
     *
     * @param secondes temps ecoule depuis la derniere fois
     */
    public void evoluer(double secondes) {
        this.posx = this.posx + vx * secondes;
        this.posy = this.posy + vy * secondes;

        // rebond a gauche
        if (posx < 0) {
            posx = 0;
            // inverse vitesse x
            vx = -vx;
        }

        // rebond a droite
        if (posx > tailleEcran) {
            posx = tailleEcran;
            vx = -vx;
        }

        // rebond en haut
        if (posy < 0) {
            posy = 0;
            vy = -vy;
        }

        // pas besoin de rebond en bas
        // fin de partie
    }

    /**
     * teste collision balle / raquette
     *
     * @param raquette raquette du jeu
     */
    public void collision(Raquette raquette) {
        // regarde si balle viens de passer dessous (et qu'elle ne l'etaia pas avant)
        if (this.getPy() > raquette.getPy() && !balleSousRaquette) {
            // balle au meme endroit que la raquette selon axe X
            if ((this.getPx() < raquette.getPx() + Raquette.RAQUETTE_TAILLE / 2) && (this.getPx() > raquette.getPx() - Raquette.RAQUETTE_TAILLE / 2)) {
                // change la vitesse verticale de la balle
                this.vy = -this.vy;
                // replace la balle au dessus de la raquette
                this.posy = raquette.getPy() - 1;
            } else {
                // pas rattrappe et la balle est desormais en dessous de la raquette
                balleSousRaquette = true;
            }
        }


    }

    // ###########################################
    // GETTER
    // ###########################################
    public double getPx() {
        return this.posx;
    }

    public double getPy() {
        return this.posy;
    }
}
