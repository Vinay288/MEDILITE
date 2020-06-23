package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.sql.Types.NULL;

public class signup_customerc {
    @FXML
    private JFXTextField textfield1;
    @FXML
    private JFXTextField textfield4;
    @FXML
    private JFXTextField textfield5;
    @FXML
    private JFXTextArea text21;
    @FXML
    private JFXTextField textfield6;
    @FXML
    private JFXPasswordField textfield7,textfield8;
    @FXML
    private Text sss;
    @FXML
    private boolean check()
    {
        if(textfield1.getText().isEmpty()||text21.getText().isEmpty()||textfield4.getText().isEmpty()||textfield5.getText().isEmpty()||textfield6.getText().isEmpty()||textfield8.getText().isEmpty())
        { String ss ="enter all fields";
            sss.setText(ss);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fields are Empty");
            alert.setHeaderText("Enter all fields");
            alert.setContentText("Please Enter all fields");
            alert.showAndWait();

            return false;
        }
        return  true;
    }
    private boolean checkuser() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from customer_db");
            while(rs.next())
            { String qq = textfield6.getText();
                if(qq.equals(rs.getString("id")))
                { String  ss ="id already exists!!";
                    sss.setText(ss);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("id exists");
                    alert.setHeaderText("id already exits");
                    alert.setContentText("Please change ID as the username which you entered is already exist");
                    alert.showAndWait();
                    return false;
                }

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return true;
    }
    private boolean  checkph() {
        String ss = "enter 10 digit ph no!!!!";
        sss.setText(ss);
        String ph = textfield4.getText();
        int i = 0;
        Pattern p = Pattern.compile("[0-9]+");
        int sa = ph.length();
        Matcher m = p.matcher((textfield4.getText()));
        if(m.find()&&m.group().equals((textfield4.getText())) &&sa ==10 )
            return true;






        else {

            String ess = "!!10 digit phone number";
            sss.setText(ess);
            System.out.println(sss.getText());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong phone number");
            alert.setHeaderText("Please enter the correct phone number");
            alert.setContentText("please enter your 10 digit mobile number");
            alert.showAndWait();
            return false;

        }
    }
    private boolean pass() {
        if(textfield7.getText().length()<5)
        {
        String ss = "Password should have minimum length of 5";
        sss.setText(ss);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("please make your password strong");
        alert.setHeaderText(ss);
        alert.setContentText("password");
        alert.showAndWait();
        return false;
    }
        if(textfield7.getText().equals(textfield8.getText()))
        {
            return true;
        }
        else
        {
            String ss = "Password doent match";
            sss.setText(ss);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("password doesnt match");
            alert.setHeaderText("please enter correct password");
            alert.setContentText("password");
            alert.showAndWait();
            return false;
        }
    }
    private  boolean checknameee()
    {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher((textfield5.getText()));
        if(m.find()&&m.group().equals((textfield5.getText())))
            return true;
        else
        {
            String ss = "email id is wrong";
            sss.setText(ss);
            System.out.println(sss.getText());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("wrong emaild");
            alert.setHeaderText("Please enter the correct emailid");
            alert.setContentText("emailid!");
            alert.showAndWait();
            return false;
        }


    }





    @FXML
    protected void register(ActionEvent event) throws Exception {


        try {


            System.out.println("Connecting");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
            Statement statement = connection.createStatement();

            if (check()) {
                if (checknameee()) {
                    if (checkph()) {
                        if (checkuser()) {
                            if (pass()) {


                                System.out.println("Done");
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Account is created");
                                alert.setHeaderText("Account is created");
                                alert.setContentText(textfield1.getText().toUpperCase() + " " + "Thank you for creating your account , Please confirm again whether the informations given by you is correct");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    statement.execute("INSERT INTO customer_db (id,user,phno,emailid,address,password) VALUES ('" + textfield6.getText() + "','" + textfield1.getText() +  "','" + textfield4.getText() + "','" + textfield5.getText() + "','" + text21.getText() + "','" + textfield7.getText() + "')");
                                    Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
                                    Scene scene = new Scene(parent, 1098, 785);
                                    Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    wndow.setScene(scene);
                                    wndow.show();
                                }
                            }
                        }
                    }

                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }


    }

    public void backk(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("selection.fxml"));
        Scene scene = new Scene(parent, 1098, 785);
        Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        wndow.setScene(scene);
        wndow.show();
    }
}







