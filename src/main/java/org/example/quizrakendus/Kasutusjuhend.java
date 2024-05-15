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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Kasutusjuhend extends Avaleht{
    public static Scene sisestaprojekt(Stage stage) {
        String tekst = "Meie Quizlet-rakendus on loodud selleks, et pakkuda kasutajatele lihtsat ja meeldivat kogemust flashcardide loomisel ja kasutamisel. Rakendus võimaldab mitmeid funktsioone, mis aitavad teil oma õppimise eesmärke saavutada.";

        Label label = new Label(tekst);
        label.getStyleClass().add("label");
        label.setWrapText(true);
        label.setMaxWidth(350);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new javafx.geometry.Insets(10));
        vbox.setMinHeight(100);
        vbox.setMinWidth(100);
        vbox.getChildren().add(label);
        vbox.getStyleClass().add("vbox-default");

        BorderPane bp = new BorderPane();
        Button button = new Button("Tagasi avalehele");
        button.getStyleClass().add("button-default");

        BorderPane.setAlignment(button, Pos.BOTTOM_RIGHT);
        bp.getStyleClass().add("vbox-default");
        bp.setBottom(button);
        bp.setMinHeight(400);
        bp.setMinWidth(400);
        bp.setCenter(vbox);

        EventHandler<ActionEvent> tagasi = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                stage.setScene(Avaleht.avaleht);
            }
        };

        button.setOnAction(tagasi);

        Scene scene = new Scene(bp, 400, 400);

        String css = Kasutusjuhend.class.getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        return scene;
    }
}
