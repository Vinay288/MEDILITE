package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

public class signup_shopc {
    public void back2(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("selection.fxml"));
        Scene scene = new Scene(parent, 1098, 785);
        Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        wndow.setScene(scene);
        wndow.show();

    }
    @FXML
    private JFXTextField Text1;
    @FXML
    private JFXTextField Text2;
    @FXML
    private JFXTextField Text3;
    @FXML
    private JFXTextArea Tex4;
    @FXML
    private JFXTextField Text5;
    @FXML
    private JFXTextField Tex8;
    @FXML
    private JFXPasswordField Text7,Text6;
    @FXML
    private Text sss;
    @FXML
    private Button regngo;
    @FXML
    private Text ll;
    private boolean check()
    {
        if(Text1.getText().isEmpty()||Text2.getText().isEmpty()||Text3.getText().isEmpty()||Tex4.getText().isEmpty()||Text5.getText().isEmpty()||Text6.getText().isEmpty()||Tex8.getText().isEmpty())
        {
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
            ResultSet rs = statement.executeQuery("select * from shop_db");
            while(rs.next())
            { String qq = Text5.getText();
                if(qq.equals(rs.getString("shop_id")))
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ID exists");
                    alert.setHeaderText("ID already exits");
                    alert.setContentText("Please change ID as the ID which you entered already exists");
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
        String ph = Text2.getText();
        int i = 0;
        Pattern p = Pattern.compile("[0-9]+");
        int sa = ph.length();
        Matcher m = p.matcher((Text2.getText()));
        if(m.find()&&m.group().equals((Text2.getText())) &&sa ==10 )
            return true;






        else {

            String ss = "!!10 digit phone number";
            sss.setText(ss);
            System.out.println(sss.getText());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong phone number");
            alert.setHeaderText("Please enter the correct phone number");
            alert.setContentText("please enter your 10 digit mobile number");
            alert.showAndWait();
            return false;

        }
    }
    private boolean pass()
    {
        if(Text6.getText().length()<5)
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
        else if(Text6.getText().equals(Text7.getText()))
        {
            return true;
        }
        else
        {
            String ss = "Password doesnt match";
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
        Matcher m = p.matcher((Text3.getText()));
        if(m.find()&&m.group().equals((Text3.getText())))
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
    private  boolean checkid()
    {
        Pattern p = Pattern.compile("[a-zA-Z][a-zA-Z0-9]+");
        Matcher m = p.matcher((Text5.getText()));
        if(m.find()&&m.group().equals((Text5.getText())))
            return true;
        else
        {
            String ss = " id is wrong";
            sss.setText(ss);
            System.out.println(sss.getText());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("wrong id");
            alert.setHeaderText("Invalid Id!!!");
            alert.setContentText("Id should not start with number or special character");
            alert.showAndWait();
            return false;
        }


    }

    @FXML
    protected void registerngo(ActionEvent event) throws Exception {




        try {


            System.out.println("Connecting");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();

            if (check()) {
                if (checknameee()) {
                    if (checkph()) {
                        if (checkuser()) {
                            if (pass()) {
                                if(checkid()){
                                System.out.println("Done");
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Account is created");
                                alert.setHeaderText("Account is created");
                                alert.setContentText(Text1.getText().toUpperCase() + " " + "Thank you for creating your account as a Pharmacy owner Please confirm again whether the informations given by you is correct");

                                Optional<ButtonType> result = alert.showAndWait();
                                {
                                    if (result.get() == ButtonType.OK) {
                                        String z = Text5.getText();

                                        String z1=z+"2";

                                            statement.execute("INSERT INTO shop_db (shop_name,shop_id,owner_name,password,address,phno,email_id) VALUES ('" + Text1.getText() + "','" + Text5.getText() + "','" + Tex8.getText() + "','" + Text6.getText() + "','" + Tex4.getText() + "','" + Text2.getText() + "','" + Text3.getText() + "')");


                                            statement1.execute("create table if not exists " + z + " (medicine varchar (15),tablete float (6,3),price float (7,3),exp_date varchar (15))");
                                            statement2.execute("create table if not exists " + z1 + " (dat date ,amt float(13,4))" );
                                            Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
                                            Scene scene = new Scene(parent, 1098, 785);
                                            Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                            wndow.setScene(scene);
                                            wndow.show();
                                            System.out.println("Done");
                                        }
                                    }
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
    @FXML
    public void pre(KeyEvent keyEvent) throws Exception
    {
        ll.setText("ID should start from an alphabet");

    }
    }
