package org.example.quizrakendus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
        String tekst = "Meie Quizlet-rakendus on loodud selleks, et pakkuda kasutajatele lihtsat ja meeldivat kogemust õppimiskaartide loomisel ja kasutamisel. Eriti kasulik võiks see olla just aines 'Matemaatiline maailmapilt' definitsioonide õppimisel.\n\nEsiteks on vaja luua oma projekt või kasutada juba olemasolevat --> vajuta nupul 'Uus projekt' või kliki soovitud projekti nimega nupul. Juba loodud projekte on ka võimalik muuta ja kustutada. \n\nSeejärel tuleb lisada projektile nimi ja asuda õppimiskaartide loomise juurde. Küsimuste ning vastuste vahel saab edasi-tagasi ka liikuda. Kui see tehtud saab kasutaja vajutada salvesta.\n\nSeejärel jääbki üle ainult oma projekt listist üles otsida ja tööle asuda. Meeldivat õppimist!";

        Label label = new Label(tekst);
        label.getStyleClass().add("label-text");
        label.setWrapText(true);
        label.setMinWidth(350);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new javafx.geometry.Insets(10));
        vbox.setMaxHeight(500);
        vbox.setMaxWidth(100);
        vbox.getChildren().add(label);
        vbox.getStyleClass().add("vbox-default");

        BorderPane bp = new BorderPane();
        Button button = new Button("Tagasi avalehele");
        button.getStyleClass().add("button-default");

        BorderPane.setAlignment(button, Pos.BOTTOM_RIGHT);
        bp.getStyleClass().add("vbox-default");
        bp.setBottom(button);
        bp.setPadding(new Insets(10));
        bp.setCenter(vbox);

        EventHandler<ActionEvent> tagasi = new EventHandler<ActionEvent>() { //tagasi avalehele
            public void handle(ActionEvent e) {
                stage.setScene(Avaleht.avaleht);
            }
        };

        button.setOnAction(tagasi);

        Scene scene = new Scene(bp, 400, 600);

        String css = Kasutusjuhend.class.getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(css); //rakendame css-i

        return scene;
    }
}
