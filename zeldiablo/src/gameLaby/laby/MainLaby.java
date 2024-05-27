package gameLaby.laby;

import java.io.IOException;

public class MainLaby {
    public static void main(String[] args) throws IOException {
        LabyJeu jeu = new LabyJeu();
        LabyDessin dessin = new LabyDessin();

        moteurJeu.MoteurJeu.setTaille(200, 200);
        moteurJeu.MoteurJeu.setFPS(20);
        moteurJeu.MoteurJeu.launch(jeu, dessin);
    }

}
