package gameLaby.laby;

/**
 * Classe pour representer une position dans un labyrinthe
 */
public class Position {

    /**
     * position dans un labyrinthe
     */
    int x;
    int y;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Position(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    /**
     * permet de savoir si quelqu'un est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si lquelqu'un est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {

        return (this.x == dx && this.y == dy);
    }

    /**
     * @return position x dans un labyrinthe
     */
    public int getX() {
        // getter
        return this.x;
    }


    /**
     * @return position y dans un labyrinthe
     */
    public int getY() {
        //getter
        return this.y;
    }

}