module equipe5MegaSae {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens equipe5MegaSae.controller to javafx.fxml;

    exports equipe5MegaSae.main;
    exports equipe5MegaSae.controller;
}