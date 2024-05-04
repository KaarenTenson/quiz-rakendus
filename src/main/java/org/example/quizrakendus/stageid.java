package org.example.quizrakendus;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class stageid {
    public ArrayList<Scene> kohad;
    public static Scene sisestaprojekt(Stage stage){
        GridPane juur=new GridPane();
        TextField nimi = new TextField();
        Button nupp=new Button("Salvesta");
        Label label = new Label("Sisesta projekt");
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                label.setText("salvestatud");
                lugemine_ja_kirjutamine.lisaprojektid(nimi.getText());
                stage.setScene(lisaflash(nimi.getText(),stage));
            }
        };
        nupp.setOnAction(event2);
        juur.add(nimi,1,0);
        juur.add(label,1,1);
        juur.add(nupp,1,2);
        Scene scene = new Scene(juur, 700, 700);
        return scene;
    }
    public static Scene lisaflash(String nimi, Stage stage){
        quiz uusquiz=new quiz(nimi,new ArrayList<>(),0);
        final int[] indeks = {0};
        GridPane juur=new GridPane();
        TextField kusimus = new TextField();
        TextField vastus= new TextField();
        Button nupp=new Button("Salvesta");
        Button nuppjargmine=new Button("jargmine");
        Label label = new Label("Sisesta flashkaar");
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Avaleht uus=new Avaleht();
                try {
                    uusquiz.kirjuta();
                    uus.start(stage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                //stage.setScene(Avaleht.avaleht);
            }
        };
        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                    indeks[0] +=1;
                    if(indeks[0] <uusquiz.getFlashcards().size()){
                        kusimus.setText(uusquiz.getFlashcards().get(indeks[0])[0]);
                        vastus.setText(uusquiz.getFlashcards().get(indeks[0])[1]);

                    }
                    uusquiz.lisa(new String[]{kusimus.getText(),vastus.getText()});
                    kusimus.setText("");
                    vastus.setText("");
                //stage.setScene(Avaleht.avaleht);
            }
        };
        nupp.setOnAction(event2);
        nuppjargmine.setOnAction(event3);
        juur.add(kusimus,1,0);
        juur.add(vastus,2,0);
        juur.add(label,1,1);
        juur.add(nupp,1,2);
        juur.add(nuppjargmine,2,2);
        Scene scene = new Scene(juur, 700, 700);
        return scene;
    }
}
