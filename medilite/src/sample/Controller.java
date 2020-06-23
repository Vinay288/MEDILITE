package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public void logbutton(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(parent, 1098, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    public void selectionb(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("selection.fxml"));
        Scene scene = new Scene(parent, 1098, 600);
        Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        wndow.setScene(scene);
        wndow.show();

    }



}

