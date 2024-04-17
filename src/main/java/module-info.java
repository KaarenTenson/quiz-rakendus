module org.example.quizrakendus {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.quizrakendus to javafx.fxml;
    exports org.example.quizrakendus;
}