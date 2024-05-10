package org.example.quizrakendus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Kasutusjuhend {
    public static Scene sisestaprojekt(Stage stage){

        Label label = new Label("kasutusjuhend");

        StackPane sp = new StackPane();
        sp.getChildren().add(label);

        BorderPane bp = new BorderPane();
        Button button = new Button("tagasi");


        BorderPane.setAlignment(button, Pos.BOTTOM_RIGHT);
        bp.setBottom(button);

        StackPane.setAlignment(label, Pos.CENTER);

        bp.setCenter(sp);

        EventHandler<ActionEvent> tagasi = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                stage.setScene(Avaleht.avaleht);
            }
        };
        button.setOnAction(tagasi);
        Scene scene = new Scene(bp, 450, 450);
        return scene;
    }
}
