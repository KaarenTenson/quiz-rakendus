package org.example.quizrakendus;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Avaleht extends Application {

    public static Scene avaleht;


    @Override
    public void start(Stage stage) throws IOException {
        try {
            BorderPane root = new BorderPane();
            root.setPadding(new Insets(20));
            root.getStyleClass().add("root-pane");

            BorderPane ülemine = new BorderPane();
            ülemine.setPadding(new Insets(0,20,0,120));

            HBox nupudÜleval = new HBox();
            nupudÜleval.setPadding(new Insets(10));

            EventHandler<ActionEvent> kasutusjuhendiKuvamine = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    stage.setScene(Kasutusjuhend.sisestaprojekt(stage));
                }
            };

            //kasutusjuhendi kuvamise nupp
            Circle circle = new Circle();
            circle.setRadius(25);
            circle.getStyleClass().add("circle");

            Button ringiKujulineNupp = new Button("?");
            ringiKujulineNupp.getStyleClass().add("button-default");

            ringiKujulineNupp.setShape(circle);
            ringiKujulineNupp.setAlignment(Pos.TOP_CENTER);
            ringiKujulineNupp.setMinSize(50, 50);
            ringiKujulineNupp.setMaxSize(50, 50);
            ringiKujulineNupp.setAlignment(Pos.CENTER);
            ringiKujulineNupp.setOnAction(kasutusjuhendiKuvamine);

            EventHandler<ActionEvent> projektiLisamine = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    stage.setScene(stageid.sisestaprojekt(stage));
                }
            };

            //uue projekti lisamise nupp
            Button ülemineParemNupp = new Button("Uus projekt");
            ülemineParemNupp.getStyleClass().add("button-default");
            ülemineParemNupp.setMinWidth(80);
            ülemineParemNupp.setMinHeight(50);

            ülemineParemNupp.setOnAction(projektiLisamine);

            nupudÜleval.setSpacing(10);


            ülemine.setMinWidth(700);
            ülemine.setMinHeight(250);

            Label label = new Label("QUIZLET");
            label.getStyleClass().add("label-title");


            nupudÜleval.getChildren().addAll(ringiKujulineNupp, ülemineParemNupp);
            ülemine.setRight(nupudÜleval);
            ülemine.setCenter(label);

            List<String> projektid = lugemine_ja_kirjutamine.leiaprojektid();

            VBox vBox = new VBox();
            vBox.getStyleClass().add("vbox-default");

            for (int i = 0; i < projektid.size(); i++) { //loob projektide pealkirjadega nupud ja muuda nupud
                BorderPane nupud=new BorderPane();
                Button nupp = createButton(projektid.get(i));
                nupp.setOnAction(MangiEvent(projektid.get(i),stage));
                nupp.getStyleClass().add("button-default");

                Button nupp2 = new Button("muuda");
                nupp2.setMinWidth(70);
                nupp2.setMinHeight(70);
                nupp2.getStyleClass().add("button-default");
                nupp2.setOnAction(MuudaEvent(projektid.get(i),stage));

                nupud.setCenter(nupp);
                nupud.setRight(nupp2);
                nupud.setPadding(new Insets(0, 70, 0, 0));
                vBox.getChildren().add(nupud);
            }

            vBox.setAlignment(javafx.geometry.Pos.CENTER);
            vBox.setSpacing(10);

            //loon scrolleri
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(vBox);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            scrollPane.getStyleClass().add("scroll-pane");

            root.setCenter(scrollPane);
            root.setTop(ülemine);
            root.setMinSize(700, 700);

            avaleht = new Scene(root, 700, 700);

            //rakendab css faili avalehele
            String css = getClass().getResource("/styles.css").toExternalForm();
            avaleht.getStylesheets().add(css);

            stage.setTitle("Quizlet");
            stage.setScene(avaleht);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static EventHandler<ActionEvent> MuudaEvent(String nimi, Stage stage){
    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            stage.setScene(stageid.lisaflash(nimi, stage));
        }
    };
    return event1;
    }
        private static EventHandler<ActionEvent> MangiEvent(String nimi, Stage stage){
            EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    try {
                        programm.flash(stage,nimi);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    };
                }
            };
        return event2;}
    private Button createButton(String text) { //nupu loomise abimeetod
        Button button = new Button(text);
        button.setMinWidth(400);
        button.setMinHeight(70);
        return button;
    }

    public static void main(String[] args) {
        launch();
    }
}