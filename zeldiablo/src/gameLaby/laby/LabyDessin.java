package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

public class LabyDessin implements DessinJeu {
    double x;
    double y;
    double w;
    double h;

    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        Labyrinthe laby = ((LabyJeu)jeu).laby;
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(int l = 0; l < laby.getLengthY(); l++){
            for(int c = 0; c < laby.getLength(); c++){
                w = Math.ceil(canvas.getWidth()/laby.getLength());
                h = Math.ceil(canvas.getHeight()/laby.getLengthY());
                x = Math.ceil(w*c);
                y = Math.ceil(h*l);

                if(laby.getMur(c,l)) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x, y, w, h);
                }

                if (laby.pj.x == c && laby.pj.y == l) {
                    gc.setFill(Color.RED);
                    gc.fillOval(x, y, w, h);
                }
            }
        }
    }
}
