package gameArkanoid;

/**
 * represente une raquette dans unjeu casse brique
 */
public class Raquette {

    /**
     * position raquette
     */
    double posX;
    double posY;

    /**
     * vitesse selon x
     */
    double vx;

    /**
     * taille du jeu
     */
    double tailleEcran;

    /**
     * taille de la raquette
     */
    public static final double RAQUETTE_TAILLE = 100;

    /**
     * constructeur
     *
     * @param width  taille ecran
     * @param height taille ecran
     */
    public Raquette(double width, double height) {
        // initialise caracteristique de la raquette
        this.posX = width / 2;
        this.posY = height - 100;
        this.vx = width;
    }

    /**
     * gere deplacement de la raquette vers la droite
     *
     * @param width    taille ecran
     * @param secondes temps ecoule depuis la derniere fois
     */
    public void allerDroite(int width, double secondes) {
        posX = posX + vx * secondes;

        // si on sort ecran
        if (posX > width)
            posX = width;
    }

    /**
     * gere deplacement de la raquette vers la gauche
     *
     * @param width    taille ecran
     * @param secondes temps ecoule depuis la derniere fois
     */
    public void allerGauche(int width, double secondes) {
        posX = posX - vx * secondes;

        // si on sort ecran
        if (posX < 0)
            posX = 0;
    }

    // ################################
    // GETTER
    // ################################

    public double getPx() {
        return this.posX;

    }

    public double getPy() {
        return this.posY;

    }
}
