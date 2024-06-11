package gameLaby.laby;

/**
 * Représente un élément déplaçable
 */
public abstract class Deplacable extends Entite {

    private String direction = "";

    private double speed;

    /**
     * constructeur
     *
     * @param x position selon x
     * @param y position selon y
     */
    public Deplacable(int x, int y) {
        super(x, y);
    }

    /**
     * Change la direction du déplacement
     *
     * @param direction direction du déplacement
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Renvoie la direction du déplacement
     *
     * @return direction du déplacement
     */
    public String getDirection() {
        return this.direction;
    }

    /**
     * Déplace l'élément en fonction de sa direction et des obstacles
     */
    public void deplacer() {
        int[] avant = {this.getX(), this.getY()};
        int[] suivante = Labyrinthe.getSuivant(this.getX(), this.getY(), this.direction);

        if (Labyrinthe.murs[suivante[0]][suivante[1]]) {
            return;
        }

        if (direction.isEmpty()) {
            return;
        }

        // On déplace l'élément
        this.setX(suivante[0]);
        this.setY(suivante[1]);

        for (Entite d : Labyrinthe.entites) {
            if (d.etrePresent(suivante[0], suivante[1]) && d != this) {
                this.collision(d);
                d.collision(this);
                // Les escaliers ne bloquent pas le passage
                if (!(d instanceof Escalier)) {
                    // On remet à la position initiale
                    this.setX(avant[0]);
                    this.setY(avant[1]);
                    return;
                }
            }
        }
    }

}
