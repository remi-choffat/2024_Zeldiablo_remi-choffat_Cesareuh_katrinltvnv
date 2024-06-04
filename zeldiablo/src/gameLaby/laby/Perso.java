package gameLaby.laby;

/**
 * Représente le personnage du jeu
 */
public class Perso extends Vivant{

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy, int pv) {
        super(dx,dy,pv);
    }

    @Override
    public void infligerDegats(Vivant v) {
        v.subirDegats(1);
    }

}
