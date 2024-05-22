package gameArkanoid;

import moteurJeu.MoteurJeu;

/**
 * lance un jeu de type arkanoid en 100 frames par second
 * (en fait 60 FPS car synchronise sur le rafraichissement de l'ecran)
 */
public class MainArkanoidRapide {

    public static void main(String[] args) {
        int width = 800;
        int height = 600;
        int pFPS = 100;

        // creation des objets
        ArkanoidJeu jeuArk = new ArkanoidJeu();
        ArkanoidDessin dessinArk = new ArkanoidDessin();

        // parametrage du moteur de jeu
        MoteurJeu.setTaille(width,height);
        MoteurJeu.setFPS(pFPS);

        // lancement du jeu
        MoteurJeu.launch(jeuArk, dessinArk);
    }
}
