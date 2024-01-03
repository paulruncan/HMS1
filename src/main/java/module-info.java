module com.example.hms1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hms1 to javafx.fxml;
    exports com.example.hms1;
    exports com.example.hms1.Controllers;
    opens com.example.hms1.Controllers to javafx.fxml;
    exports com.example.hms1.Patients;
    opens com.example.hms1.Patients to javafx.fxml;
}