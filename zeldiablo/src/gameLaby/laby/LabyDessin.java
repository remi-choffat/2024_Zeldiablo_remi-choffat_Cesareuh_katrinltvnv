package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

public class LabyDessin implements DessinJeu {

   @Override
   public void dessinerJeu(Jeu jeu, Canvas canvas) {

      double x;
      double y;
      double w;
      double h;

      Labyrinthe laby = ((LabyJeu) jeu).laby;
      GraphicsContext gc = canvas.getGraphicsContext2D();

      gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
      for (int l = 0; l < laby.getLengthY(); l++) {
         for (int c = 0; c < laby.getLengthX(); c++) {
            w = Math.ceil(canvas.getWidth() / laby.getLengthX());
            h = w;
            x = Math.ceil(w * c);
            y = Math.ceil(h * l);

            if (laby.getMur(c, l)) {
               gc.setFill(Color.BLACK);
               gc.fillRect(x, y, w, h);
            }

            if (Labyrinthe.deplacables != null) {
               for (Deplacable deplacable : Labyrinthe.deplacables) {
                  if (deplacable.etrePresent(c, l)) {
                     if (deplacable instanceof Perso) {
                        gc.setFill(Color.RED);
                     } else {
                        if (deplacable instanceof Monstre) {
                           gc.setFill(Color.BLUE);
                        } else {
                           if (deplacable instanceof Fleche) {
                              gc.setFill(Color.GREEN);
                              gc.setStroke(Color.BLUEVIOLET);
                           } else {
                              gc.setFill(Color.YELLOW);
                           }
                        }
                     }
                     gc.fillOval(x, y, w, h);
                  }
               }
            }
         }
      }
   }
}
