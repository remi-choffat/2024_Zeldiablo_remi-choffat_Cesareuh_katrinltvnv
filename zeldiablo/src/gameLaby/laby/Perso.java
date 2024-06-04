package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Perso extends Position{

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy) {
        super(dx,dy);
    }

    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    @Override
    public boolean etrePresent(int dx, int dy) {
        return super.etrePresent(dx, dy);
    }
    // ############################################
    // GETTER
    // ############################################

    /**
     * @return position x du personnage
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * @return position y du personnage
     */
    @Override
    public int getY() {
        return y;
    }
}
