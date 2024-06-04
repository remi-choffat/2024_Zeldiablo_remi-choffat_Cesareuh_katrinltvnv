package gameLaby.laby;

/**
 * gere un mostre situe en x,y
 */
public class Monstre extends Position{

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Monstre(int dx, int dy){
        super(dx,dy);
    }

    /**
     * permet de savoir si le monstre est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le monstre est bien en (dx,dy)
     */
    @Override
    public boolean etrePresent(int dx, int dy) {
        return super.etrePresent(dx, dy);
    }

    /**
     * @return position x du monstre
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * @return position y du monstre
     */
    @Override
    public int getY() {
        return y;
    }
}
