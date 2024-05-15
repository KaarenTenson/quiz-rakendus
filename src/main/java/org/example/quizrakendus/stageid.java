package org.example.quizrakendus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class stageid {
    public ArrayList<Scene> kohad;
    public static Scene sisestaprojekt(Stage stage){
        GridPane juur=new GridPane();
        juur.getStyleClass().add("vbox-default");
        TextField nimi = new TextField();
        Button nupp=new Button("Salvesta");
        Label label = new Label("Sisesta projekt");
        nupp.getStyleClass().add("button-default");
        label.getStyleClass().add("label");


        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                label.setText("salvestatud");
                String d=nimi.getText();
                if(!lugemine_ja_kirjutamine.leiaprojektid().contains(d)){
                    lugemine_ja_kirjutamine.lisaprojektid(nimi.getText());}
                stage.setScene(lisaflash(nimi.getText(),stage));
            }
        };
        nupp.setOnAction(event2);
        juur.add(nimi,1,0);
        juur.add(label,1,1);
        juur.add(nupp,1,2);
        Scene scene = new Scene(juur, 700, 700);

        String css = Kasutusjuhend.class.getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        return scene;
    }
    public static Scene lisaflash(String nimi, Stage stage){

        final int[] indeks = {0}; //näitab mitmes flashcard see on
        GridPane juur=new GridPane();
        juur.getStyleClass().add("vbox-default");
        TextField kusimus = new TextField();
        TextField vastus= new TextField();
        //kusimus.getStyleClass().add("label");
        //vastus.getStyleClass().add("label");
        Button nupp=new Button("Salvesta");
        Button nuppjargmine=new Button("järgmine");
        Button nuppeelmine=new Button("eelmine");
        Button nuppkustuta=new Button("kustuta");
        nupp.getStyleClass().add("button-default");
        nuppeelmine.getStyleClass().add("button-default");
        nuppjargmine.getStyleClass().add("button-default");


        Label label = new Label("Sisesta flashkaart");
        Label label1=new Label("1");
        label.getStyleClass().add("label");
        label1.getStyleClass().add("label-title");
        nuppeelmine.setDisable(true);
        quiz uusquiz;
        try {
            uusquiz=lugemine_ja_kirjutamine.loe(nimi);
            kusimus.setText(uusquiz.getFlashcards().get(0)[0]);
            vastus.setText(uusquiz.getFlashcards().get(0)[1]);
        }catch (Exception e){
            uusquiz=new quiz(nimi,new ArrayList<>(),0);
        }
        quiz finalUusquiz = uusquiz;
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Avaleht uus=new Avaleht();
                try {
                    finalUusquiz.kirjuta();
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
                if(!kusimus.getText().isEmpty()){
                if(nuppeelmine.isDisable()){
                    nuppeelmine.setDisable(false);
                }
                if(indeks[0] <finalUusquiz.getFlashcards().size()-1){
                    finalUusquiz.muuda(indeks[0],kusimus.getText(),vastus.getText());
                    kusimus.setText(finalUusquiz.getFlashcards().get(indeks[0]+1)[0]);
                    vastus.setText(finalUusquiz.getFlashcards().get(indeks[0]+1)[1]);

                }
                else{
                    finalUusquiz.lisa(new String[]{kusimus.getText(),vastus.getText()});
                    kusimus.setText("");
                    vastus.setText("");}
                indeks[0] +=1;}
                label1.setText(String.valueOf(indeks[0]+1));

                //stage.setScene(Avaleht.avaleht);
            }
        };
        EventHandler<ActionEvent> event4 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if (indeks[0]<=1){
                        nuppeelmine.setDisable(true);
                }
                if(indeks[0]>finalUusquiz.getFlashcards().size()-1){
                    finalUusquiz.lisa(new String[]{kusimus.getText(),vastus.getText()});
                }
                else{
                    finalUusquiz.muuda(indeks[0],kusimus.getText(),vastus.getText());}
                if(indeks[0] >=1){
                    indeks[0] -=1;
                    kusimus.setText(finalUusquiz.getFlashcards().get(indeks[0])[0]);
                    vastus.setText(finalUusquiz.getFlashcards().get(indeks[0])[1]);
                }
                label1.setText(String.valueOf(indeks[0]+1));


                //stage.setScene(Avaleht.avaleht);
            }
        };
        EventHandler<ActionEvent> eventKustuta = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Avaleht uus=new Avaleht();
                try {
                    List<String> as=lugemine_ja_kirjutamine.leiaprojektid();
                    as.remove(nimi);
                    lugemine_ja_kirjutamine.kirjutaUle(as.toArray(new String[0]));
                    uus.start(stage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                //stage.setScene(Avaleht.avaleht);
            }
        };
        nupp.setOnAction(event2);
        nuppjargmine.setOnAction(event3);
        nuppeelmine.setOnAction(event4);
        nuppkustuta.setOnAction(eventKustuta);
        juur.add(kusimus,1,0);
        juur.add(vastus,2,0);
        juur.add(label,1,1);
        juur.add(nupp,1,2);
        juur.add(nuppjargmine,2,2);
        juur.add(nuppeelmine,3,2);
        juur.add(nuppkustuta,4,2);
        juur.add(label1,0,0);
        Scene scene = new Scene(juur, 700, 700);

        String css = Kasutusjuhend.class.getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        return scene;
    }
}
