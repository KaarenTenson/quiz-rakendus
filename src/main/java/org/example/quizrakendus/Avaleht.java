package org.example.quizrakendus;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Avaleht extends Application {

    public static Scene avaleht;


    @Override
    public void start(Stage stage) throws IOException {
        try {
            BorderPane root = new BorderPane();

            //loadRobotoFont();

            HBox topContainer = new HBox();
            topContainer.setPadding(new Insets(20));

            EventHandler<ActionEvent> kasutusjuhendiKuvamine = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    stage.setScene(Kasutusjuhend.sisestaprojekt(stage));
                }
            };

            Button ringiKujulineNupp = new Button("?");

            Circle circle = new Circle();
            circle.setRadius(25);
            circle.setStyle("-fx-fill: #3377FF;");

            ringiKujulineNupp.setShape(circle);

            ringiKujulineNupp.setAlignment(Pos.TOP_CENTER);

            ringiKujulineNupp.setMinSize(50, 50);
            ringiKujulineNupp.setMaxSize(50, 50);
            ringiKujulineNupp.setOnAction(kasutusjuhendiKuvamine);

            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    stage.setScene(stageid.sisestaprojekt(stage));
                }
            };

            Button ülemineParemNupp = new Button("Uus projekt");
            ülemineParemNupp.setMinWidth(80);
            ülemineParemNupp.setMinHeight(50);

            ülemineParemNupp.setOnAction(event);


            topContainer.setSpacing(10);

            topContainer.getChildren().addAll(ringiKujulineNupp, ülemineParemNupp);

            topContainer.setAlignment(Pos.TOP_RIGHT);

            root.setTop(topContainer);

            List<String> projektid = lugemine_ja_kirjutamine.leiaprojektid();

            System.out.println(projektid);

            VBox vBox = new VBox();

            Label label = new Label("QUIZLET");

            label.setStyle("-fx-font-size: 40px;");

            //label.setStyle("-fx-font-family: 'Roboto';");

            vBox.getChildren().add(label);


            for (int i = 0; i < projektid.size(); i++) { //loob projektide pealkirjadega nupud
                Button nupp = createButton(projektid.get(i));
                vBox.getChildren().add(nupp);
            }

            vBox.setAlignment(javafx.geometry.Pos.CENTER);
            vBox.setSpacing(10);

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(vBox);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);

            root.setCenter(scrollPane);

            avaleht = new Scene(root, 700, 700);
            stage.setScene(avaleht);
            stage.setTitle("Quizlet");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(400);
        button.setMinHeight(70);
        return button;
    }

    /*/private void loadRobotoFont() {
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf");
            Font font = Font.loadFont(is, 12); // Load with a dummy size
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }/*/
    public static void main(String[] args) {
        launch();
    }
}