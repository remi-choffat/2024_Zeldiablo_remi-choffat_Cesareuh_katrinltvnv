package moteurJeu;


/**
 * modele de jeu
 */
public interface Jeu {

    /**
     * methode mise a jour du jeu
     * @param secondes temps ecoule depuis la derniere mise a jour
     * @param clavier objet contenant l'état du clavier'
     */
    void update(double secondes, Clavier clavier);

    /**
     * initialisation du jeu
     */
    void init();

    /**
     * verifie si le jeu est fini
     * @return booleen true si le jeu est fini
     */
    boolean etreFini();
}
