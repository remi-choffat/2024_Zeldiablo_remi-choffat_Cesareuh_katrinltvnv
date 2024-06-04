package moteurJeu;

import javafx.scene.input.KeyEvent;

public class Clavier {

    /**
     * controle appuyes
     */
    public boolean haut, bas, gauche, droite;

    /**
     * stocke les commandes
     *
     * @param event evenement clavier
     */
    public void appuyerTouche(KeyEvent event) {

        switch (event.getCode()) {

            // si touche bas
            case S, DOWN:
                this.bas = true;
                break;

            // si touche haut
            case Z, UP:
                this.haut = true;
                break;

            // si touche gauche
            case Q, LEFT:
                this.gauche = true;
                break;

            // si touche droite
            case D, RIGHT:
                this.droite = true;
                break;

        }

    }

    /**
     * stocke les commandes
     *
     * @param event evenement clavier
     */
    public void relacherTouche(KeyEvent event) {

        switch (event.getCode()) {

            // si touche bas
            case S, DOWN:
                this.bas = false;
                break;

            // si touche haut
            case Z, UP:
                this.haut = false;
                break;

            // si touche gauche
            case Q, LEFT:
                this.gauche = false;
                break;

            // si touche droite
            case D, RIGHT:
                this.droite = false;
                break;

        }
    }
}
