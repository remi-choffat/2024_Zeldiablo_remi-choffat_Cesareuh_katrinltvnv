package gameLaby.laby;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

            if (Labyrinthe.entites != null) {
               // Dessine les entités
               for (Entite entite : Labyrinthe.entites) {
                  if (entite.etrePresent(c, l)) {
                     if (entite instanceof Perso) {
                        gc.setFill(Color.RED);
                     } else if (entite instanceof Monstre) {
                        gc.setFill(Color.BLUE);
                     } else if (entite instanceof Fleche) {
                        ImageView iv = getImageView((Fleche) entite);
                        SnapshotParameters params = new SnapshotParameters();
                        params.setFill(Color.TRANSPARENT);
                        Image rotatedImage = iv.snapshot(params, null);
                        gc.drawImage(rotatedImage, x, y, w, h); // Dessine l'image
                        continue;
                     } else {
                        gc.setFill(Color.YELLOW);
                     }
                     gc.fillOval(x, y, w, h);
                     // Affiche la barre de vie
                     if (entite instanceof Vivant v) {
                        int healthSize = 7;
                        gc.setFill(Color.RED);
                        gc.fillRect(x, y - healthSize, w, healthSize);
                        gc.setFill(Color.GREEN);
                        gc.fillRect(x, y - healthSize, w * v.getPv() / v.getInitialPv(), healthSize);
                     }
                  }
               }
            }
         }
      }
   }

   private static ImageView getImageView(Fleche entite) {
      String direction = entite.getDirection();
      Image fleche = new Image("file:images/fleche.png");
      ImageView iv = new ImageView(fleche);
      double rotate = switch (direction) {
         case Labyrinthe.DROITE -> 90;
         case Labyrinthe.GAUCHE -> 270;
         case Labyrinthe.BAS -> 180;
         default -> 0;
      };
      iv.setRotate(rotate); // Applique la rotation
      return iv;
   }
}
