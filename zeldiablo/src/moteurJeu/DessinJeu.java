package moteurJeu;

import javafx.scene.canvas.Canvas;


/**
 * interface pour afficher le jeu
 */
public interface DessinJeu {

    /**
     * affiche l'etat du jeu dans le canvas passe en parametre
     *
     * @param jeu jeu a afficher
     * @param canvas canvas dans lequel dessiner l'etat du jeu
     */
    void dessinerJeu(Jeu jeu, Canvas canvas);

}
