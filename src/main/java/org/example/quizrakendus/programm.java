package org.example.quizrakendus;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class programm  {

    public static void flash(Stage stage, String projekt1) throws Exception {
        // lugemine
        quiz projekt = lugemine_ja_kirjutamine.loe(projekt1);
        List<String[]> flashid = projekt.getFlashcards();
        List<Scene> stseenid = kuju(projekt, stage);
        stseenid.add(lõpp(stage, stseenid));
        stage.setTitle(projekt1);
        stage.setScene(stseenid.get(0));
        stage.show();
    }


    public static List<Scene> kuju(quiz projekt, Stage stage) {

        // flashcardid
        List<String[]> flashcardid = projekt.getFlashcards();
        int arv = projekt.getFlashcards().size();

        //stseenid
        List<Scene> stseenid = new ArrayList<>();

        // paigutamine
        for (int i = 0; i < arv; i++) {

            // küsimuse ja vastuse nupud
            Button küsimus = new Button(flashcardid.get(i)[0]);
            Button vastus = new Button(flashcardid.get(i)[1]);
            vastus.setVisible(false); // vastus pole nähtav
            // küsimuse kohandused
            küsimus.setWrapText(true);
            küsimus.setMinSize(425, 175); // miinimum ja maksimum suurused
            küsimus.setMaxSize(425, 175);
            küsimus.getStyleClass().add("button-flash");
            küsimus.setStyle("-fx-background-color: #5cb9ce");
            // vastuse kohandused
            vastus.setWrapText(true);
            vastus.setMinSize(425, 175); // miinimum ja maksimum suurused
            vastus.setMaxSize(425, 175);
            vastus.setFont(new Font("Arial", 18));
            vastus.getStyleClass().add("button-flash");
            vastus.setStyle("-fx-background-color: #5cb9ce");

            // edasi ja tagasi nupud
            Button edasi = new Button("Edasi");
            edasi.getStyleClass().add("button-default");
            edasi.setStyle("-fx-background-color: #5cb9ce");
            Button tagasi = new Button("Tagasi");
            tagasi.getStyleClass().add("button-default");
            tagasi.setStyle("-fx-background-color: #5cb9ce");
            tagasi.setVisible(i > 0); // ei näite tagasi nuppu esimesel stseenil

            // paigutamine
            VBox v = paigutamine(küsimus, vastus, edasi, tagasi);
            Scene s = new Scene(v, 500, 400);
            s.setRoot(v); // root v
            v.setStyle("-fx-background-color: #2d2b2b");
            v.setMinWidth(100);
            v.setMinHeight(100);
            HBox h = new HBox(tagasi, edasi); // edasi ja tagasi nuppude paigutus
            h.setAlignment(Pos.CENTER);
            h.setSpacing(200);
            h.setPadding(new Insets(10, 9, 20, 10));
            v.getChildren().add(h);

            // lisatakse stseen teiste stseenide hulka
            String css = programm.class.getResource("/styles.css").toExternalForm();
            s.getStylesheets().add(css);
            stseenid.add(s);

            // kui küsimuse peale vajutada
            küsimus.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    popTransition(küsimus);
                    küsimus.setVisible(false);
                    vastus.setVisible(true);
                }
            });

            // kui vastuse peale vajutada
            vastus.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    popTransition(vastus);
                    vastus.setVisible(false);
                    küsimus.setVisible(true);
                }
            });

            int finalI = i;
            // kui edasi nupu peale vajutatakse
            edasi.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (finalI < arv) {
                        stage.setScene(stseenid.get(finalI + 1)); // üks stseen edasi kui vajutatakse
                        stage.show();
                    }
                }
            });

            // kui tagasi nupu peale vajutatakse
            tagasi.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (finalI > 0) {
                        stage.setScene(stseenid.get(finalI - 1)); // üks stseen tagasi kui vajutatakse
                        stage.show();
                    }
                }
            });
        }
        return stseenid;
    }

    // abimeetod vboxi jaoks, sest muidu ei jäta meelde järgmise stseeni sisu
    private static VBox paigutamine(Button küsimus, Button vastus, Button edasi, Button tagasi) {
        GridPane g = new GridPane();
        g.setAlignment(Pos.CENTER);
        g.getChildren().addAll(küsimus, vastus, tagasi, edasi);
        VBox v = new VBox(g);
        v.setAlignment(Pos.CENTER);
        v.setSpacing(10);
        return v;
    }

    private static void popTransition(Button b) {
        // edasi
        ScaleTransition pop = new ScaleTransition(Duration.seconds(0.4), b);
        pop.setFromX(1);
        pop.setFromY(1);
        pop.setToX(1.1);
        pop.setToY(1.1);
        pop.setCycleCount(1);
        pop.setAutoReverse(false);
        pop.play();

        // tagasi
        ScaleTransition scaleBack = new ScaleTransition(Duration.seconds(0.4), b);
        scaleBack.setFromX(1.1);
        scaleBack.setFromY(1.1);
        scaleBack.setToX(1);
        scaleBack.setToY(1);
        scaleBack.setCycleCount(1);
        scaleBack.setAutoReverse(false);
        scaleBack.play();
    }

    private static Scene lõpp(Stage stage, List<Scene> stseenid) {
        // tekst
        Label l = new Label("Olete jõudnud küsimuste lõppu.\n");
        l.setAlignment(Pos.CENTER);
        l.setFont(new Font("Arial", 32));
        l.setTextFill(Color.rgb(92, 185, 206));

        // avaleht ja tagasi nupud
        Button tagasi = new Button("Tagasi");
        tagasi.getStyleClass().add("button-default");
        tagasi.setStyle("-fx-background-color: #5cb9ce");
        Button avaleht = new Button("Avaleht");
        avaleht.getStyleClass().add("button-default");
        avaleht.setStyle("-fx-background-color: #5cb9ce");

        // paigutus
        GridPane g = new GridPane();
        g.setAlignment(Pos.CENTER);
        if (stseenid.size() >= 1) g.getChildren().addAll(l, tagasi, avaleht); //kui vähemalt 1 päris stseen
        else g.getChildren().addAll(l, avaleht);
        VBox v = new VBox(g);
        v.setAlignment(Pos.CENTER);
        v.setSpacing(10);
        HBox h = new HBox(); // edasi ja tagasi nuppude paigutus
        if (stseenid.size() >= 1) h.getChildren().addAll(tagasi, avaleht); //kui vähemalt 1 päris stseen
        else h.getChildren().addAll(avaleht);
        h.setAlignment(Pos.CENTER);
        h.setSpacing(155);
        h.setPadding(new Insets(10, 9, 20, 10));
        v.getChildren().add(h);

        // stseen ja nupuvajutused
        Scene s = new Scene(v, 500, 400);
        String css = programm.class.getResource("/styles.css").toExternalForm();
        s.getStylesheets().add(css);
        s.setRoot(v); // root v
        v.setStyle("-fx-background-color: #2d2b2b");
        v.setMinWidth(100);
        v.setMinHeight(100);
        // kui tagasi vajutatakse
        tagasi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setScene(stseenid.get(stseenid.size() - 2)); // üks stseen tagasi kui vajutatakse
                stage.show();
            }
        });

        // kui avalehe nupule vajutatakse
        avaleht.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Avaleht a = new Avaleht();
                try {
                    a.start(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return s;
    }
}