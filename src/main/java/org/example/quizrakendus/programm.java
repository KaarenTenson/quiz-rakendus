package org.example.quizrakendus;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class programm extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // lugemine
        try { quiz projekt = lugemine_ja_kirjutamine.loe("test");
             List<Scene> stseenid = kuju(projekt);
            stage.setScene(stseenid.get(0));
            stage.show();
        } catch (FileNotFoundException e) {
            System.out.println("ei leidnud :(");
        }

    }

    public static void main(String[] args) {
        launch();
    }

    public static List<Scene> kuju(quiz projekt) {

        // flashcardid
        List<String[]> flashcardid = projekt.getFlashcards();
        int arv = (projekt.getFlashcards()).size();

        //stseenid
        List<Scene> stseenid = new ArrayList<>();

        // paigutamine
        for (int i = 0; i < arv; i++) {
            Button küsimus = new Button(flashcardid.get(i)[0]);
            Button vastus = new Button(flashcardid.get(i)[1]);
            vastus.setVisible(false); // pole nähtav
            FlowPane f = new FlowPane(10, 10);
            f.setAlignment(Pos.CENTER);
            f.getChildren().addAll(küsimus, vastus);
            VBox v = new VBox(10);
            v.setAlignment(Pos.CENTER);
            v.getChildren().addAll(f);
            Scene s = new Scene(v, 100, 100);
            stseenid.add(s);

            // kui küsimuse peale vajutada
            küsimus.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    küsimus.setVisible(false);
                    vastus.setVisible(true);
                }
            });

            // kui vastuse peale vajutada
            vastus.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    vastus.setVisible(false);
                    küsimus.setVisible(true);
                }
            });
        }


        return stseenid;
    }
}
// {1, 2}, {3, 4}