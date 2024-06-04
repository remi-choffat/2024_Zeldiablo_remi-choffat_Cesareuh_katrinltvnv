package gameLaby.laby;


/**
 * Représente un monstre
 */
public class Monstre extends Vivant{

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Monstre(int dx, int dy){
        super(dx,dy,1,2);
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

    public void deplacer() {
        String[] actions = {Labyrinthe.HAUT, Labyrinthe.BAS, Labyrinthe.GAUCHE, Labyrinthe.DROITE};
        String direction = actions[(int) (Math.random() * actions.length-.001)];
        this.setDirection(direction);

        super.deplacer();
    }

    public void infligerDegats(Vivant v){
        v.subirDegats(1);
    }

}
