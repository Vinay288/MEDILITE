package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class selectionc {
    public void c1(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("signup_shop.fxml"));
        Scene scene = new Scene(parent, 1098, 600);
        Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        wndow.setScene(scene);
        wndow.show();
    }
    public void ngo(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("signup_customer.fxml"));
        Scene scene = new Scene(parent, 1098, 600);
        Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        wndow.setScene(scene);
        wndow.show();

    }
    public void back2(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(parent, 1098, 600);
        Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        wndow.setScene(scene);
        wndow.show();

    }

}

