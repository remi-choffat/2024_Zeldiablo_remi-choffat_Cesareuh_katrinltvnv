package gameArkanoid;


import moteurJeu.MoteurJeu;

/**
 * lance un jeu de type arkanoid en 10 frames par second
 */
public class MainArkanoidLent {

    public static void main(String[] args) {
        int width = 800;
        int height = 600;
        int pFPS = 20;

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
