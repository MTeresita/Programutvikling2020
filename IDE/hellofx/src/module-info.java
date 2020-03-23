module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.openjfx.Controller to javafx.fxml;
    exports org.openjfx;
}