module org.chuchro.towerdefence {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens org.chuchro.towerdefence to javafx.fxml;
    exports org.chuchro.towerdefence;
}