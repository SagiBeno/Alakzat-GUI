module com.example.alakzatgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires javafx.base;


    opens com.example.alakzatgui to javafx.fxml;
    exports com.example.alakzatgui;
}