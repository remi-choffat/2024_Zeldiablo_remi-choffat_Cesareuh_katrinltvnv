package gameLaby.laby;

/**
 * Classe pour representer une position dans un labyrinthe
 */
public abstract class Vivant extends Deplacable {

    /**
     * position dans un labyrinthe
     */
    int pv;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     * @param mv nombre de mouvements
     * @param pv nombre de points de vie
     */
    public Vivant(int dx, int dy, int mv, int pv) {
        super(dx, dy, mv);
        this.pv = pv;
    }


    /**
     * inflige des degats a un vivant
     *
     * @param v vivant a attaquer
     */
    public abstract void infligerDegats(Vivant v);

    /**
     * Subit des degats d'une source
     *
     * @param d degats subits
     */
    public void subirDegats(int d){
        this.pv -= d;
    }

    public void collision(Deplacable d) {
        if(d instanceof Vivant){
            this.infligerDegats((Vivant) d);
        }

    }

    public int getPv() {
        return this.pv;
    }

}