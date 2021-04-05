module world.ucode {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens world.ucode.controls to javafx.fxml;
    exports world.ucode;
}