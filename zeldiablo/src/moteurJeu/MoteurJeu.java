package moteurJeu;

//https://github.com/zarandok/megabounce/blob/master/MainCanvas.java

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// copied from: https://gist.github.com/james-d/8327842
// and modified to use canvas drawing instead of shapes

public class MoteurJeu extends Application {

    /**
     * gestion du temps : nombre de frame par secondes et temps par iteration
     */
    private static double FPS = 100;
    private static double dureeFPS = 1000 / (FPS + 1);

    /**
     * taille par defaut
     */
    private static double WIDTH = 800;
    private static double HEIGHT = 600;

    /**
     * statistiques sur les frames
     */
    private final FrameStats frameStats = new FrameStats();

    /**
     * jeu en Cours et renderer du jeu
     */
    private static Jeu jeu = null;
    private static DessinJeu dessin = null;

    /**
     * touches appuyee entre deux frame
     */
    Clavier controle = new Clavier();

    /**
     * AnimationTimer pour la boucle de jeu
     */
    private static AnimationTimer gameLoop;

    /**
     * lancement d'un jeu
     *
     * @param jeu    jeu a lancer
     * @param dessin dessin du jeu
     */
    public static void launch(Jeu jeu, DessinJeu dessin) {
        // le jeu en cours et son afficheur
        MoteurJeu.jeu = jeu;
        MoteurJeu.dessin = dessin;

        // si le jeu existe, on lance le moteur de jeu
        if (jeu != null)
            launch();
    }

    /**
     * frame par secondes
     *
     * @param FPSSouhaitees nombre de frames par secondes souhaitees
     */
    public static void setFPS(int FPSSouhaitees) {
        FPS = FPSSouhaitees;
        dureeFPS = 1000 / (FPS + 1);
    }

    public static void setTaille(double width, double height) {
        WIDTH = width;
        HEIGHT = height;
    }


    //#################################
    // SURCHARGE Application
    //#################################

    /**
     * Lance une application
     * @param primaryStage  Le stage principal de l'application
     */
    public void start(Stage primaryStage) {
        VBox pane = new VBox();
        Scene s = new Scene(pane, WIDTH, HEIGHT);

        //ajout de l'image de fond
        Image img = new Image(new File("./images/main.png").toURI().toString());
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(WIDTH, HEIGHT, false, false, false, false));
        Background bGround = new Background(bImg);
        pane.setBackground(bGround);

        //ajout du boutton play
        Image img1 = new Image(new File("./images/start.jpg").toURI().toString());
        ImageView start = new ImageView(img1);
        start.setFitWidth(200);
        start.setFitHeight(110);
        start.setTranslateX(380);
        start.setTranslateY(345);
        pane.getChildren().add(start);

        //ajout du boutton quit
        Image img2 = new Image(new File("./images/exit.png").toURI().toString());
        ImageView exit = new ImageView(img2);
        exit.setFitWidth(200);
        exit.setFitHeight(110);
        exit.setTranslateX(380);
        exit.setTranslateY(290);
        pane.getChildren().add(exit);

        /**
         *  Gestion de l'evenement du bouton play
         */
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent MouseEvent) {
                startJeu(primaryStage);
            }
        });

        /**
         *  Gestion de l'evenement du bouton exit
         */
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.close();
            }
        });


        // Bouton règles du jeu
        Image rulesImg = new Image(new File("./images/rules.jpg").toURI().toString());
        ImageView rulesButton = new ImageView(rulesImg);
        rulesButton.setFitWidth(60);
        rulesButton.setFitHeight(60);
        rulesButton.setTranslateX(250);
        rulesButton.setTranslateY(150);
        rulesButton.setOnMouseClicked(event -> {
            Stage rulesStage = new Stage();
            Text rulesText = new Text();

            try {
                String rules = new String(Files.readAllBytes(Paths.get("reglesJeu.txt")));
                rulesText.setText(rules);
            } catch (IOException e) {
                rulesText.setText("[Erreur lors de la lecture des règles du jeu]");
            }

            ScrollPane scrollPane = new ScrollPane();
            VBox vbox = new VBox();
            vbox.getChildren().add(rulesText);
            scrollPane.setContent(vbox);
            scrollPane.setPadding(new Insets(10, 10, 10, 10));
            scrollPane.setFitToWidth(true);

            rulesText.wrappingWidthProperty().bind(vbox.widthProperty().subtract(20));
            rulesText.setStyle("-fx-font-size: 14px; -fx-font-family: 'Courier New';");


            rulesStage.setScene(new Scene(scrollPane, 500, 500));
            rulesStage.setTitle("Zeldiablo - Règles du jeu");
            rulesStage.show();
        });

        pane.getChildren().add(rulesButton);

        // Bouton paramètres
        Image settingsImg = new Image(new File("./images/settings.jpg").toURI().toString());
        ImageView settingsButton = new ImageView(settingsImg);
        settingsButton.setFitWidth(60);
        settingsButton.setFitHeight(60);
        settingsButton.setTranslateX(650);
        settingsButton.setTranslateY(90);

        pane.getChildren().add(settingsButton);

        primaryStage.setScene(s);
        primaryStage.show();
    }

    /**
     * creation de l'application avec juste un canvas et des statistiques
     */
    public void startJeu(Stage primaryStage) {
        // initialisation du canvas de dessin et du container
        final Canvas canvas = new Canvas();
        final Pane canvasContainer = new Pane(canvas);
        canvas.widthProperty().bind(canvasContainer.widthProperty());
        canvas.heightProperty().bind(canvasContainer.heightProperty());

        // affichage des stats
        final Label stats = new Label();
        stats.textProperty().bind(frameStats.textProperty());

        // ajout des statistiques en bas de la fenetre
        final BorderPane root = new BorderPane();
        root.setCenter(canvasContainer);
        root.setBottom(stats);

        // creation de la scene
        final Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();


        // listener clavier
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controle.appuyerTouche(event);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controle.relacherTouche(event);
            }
        });


        // creation du listener souris
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2) {
                            jeu.init();
                        }
                    }
                });

        // lance la boucle de jeu
        startAnimation(canvas);
    }

    /**
     * Affiche l'ecran de fin de jeu avec le score du personnage
     *
     * @param score Score du personnage
     */
    public static void showGameOverScreen(int score) {
        if (gameLoop != null) {
            gameLoop.stop();
        }

        Stage stage = new Stage();
        VBox pane = new VBox();
        Scene scene = new Scene(pane, WIDTH, HEIGHT);

        // Ajout de l'image de fond
        Image img = new Image(new File("./images/back.png").toURI().toString());
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(WIDTH, HEIGHT, false, false, false, false));
        Background bGround = new Background(bImg);
        pane.setBackground(bGround);

        // Affiche le score
        Text scoreText = new Text("Score: " + score);
        scoreText.setFont(new Font(36));
        scoreText.setTranslateX(415);
        scoreText.setTranslateY(280);
        pane.getChildren().add(scoreText);

        // Ajout du bouton quit
        Image img2 = new Image(new File("./images/exit.png").toURI().toString());
        ImageView exit = new ImageView(img2);
        exit.setFitWidth(200);
        exit.setFitHeight(110);
        exit.setTranslateX(390);
        exit.setTranslateY(330);
        pane.getChildren().add(exit);

        // Gestion de l'événement du bouton quit
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Platform.exit();
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    /**
     * gestion de l'animation (boucle de jeu)
     *
     * @param canvas le canvas sur lequel on est synchronise
     */
    private void startAnimation(final Canvas canvas) {
        // stocke la derniere mise e jour
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);

        // Timer pour boucle de jeu
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {

                // Si jamais passe dans la boucle, initialise le temps
                if (lastUpdateTime.get() == 0) {
                    lastUpdateTime.set(timestamp);
                }

                // Mesure le temps écoulé depuis la dernière mise à jour
                long elapsedTime = timestamp - lastUpdateTime.get();
                double dureeEnMilliSecondes = elapsedTime / 1_000_000.0;

                // Si le temps écoulé dépasse le nécessaire pour FPS souhaité
                if (dureeEnMilliSecondes > dureeFPS) {
                    // Met à jour le jeu en passant les touches appuyées
                    if (!jeu.etreFini()) {
                        jeu.update(dureeEnMilliSecondes / 1_000., controle);
                        // Dessine le jeu
                        dessin.dessinerJeu(jeu, canvas);
                    }

                    // Ajoute la durée dans les statistiques
                    frameStats.addFrame(elapsedTime);

                    // Met à jour la date de dernière mise à jour
                    lastUpdateTime.set(timestamp);
                }
            }
        };

        // Lance l'animation
        gameLoop.start();
    }
}