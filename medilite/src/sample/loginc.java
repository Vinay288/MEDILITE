package sample;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.*;
import java.util.Random;

import static java.lang.Math.random;

public class loginc {
    public String shop_name;
//    @FXML
//    private TextField Text1;
//    @FXML
//    private PasswordField Text2;
    @FXML
    private JFXTextField Text1;
    @FXML
    private JFXPasswordField Text2;
    @FXML
    private JFXSpinner ll;


    boolean check() {
        if (Text1.getText().isEmpty() || Text2.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fields are Empty");
            alert.setHeaderText("Enter all fields");
            alert.setContentText("Please Enter all field");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    boolean prefix() {
//        String pre=Text1.getText().substring(3,3);
//        if(pre.equals('_')) {
//            return true;
//        }
//        return false;
        return true;
    }


    public void loginb(ActionEvent event) throws Exception


    {
        ll.setOpacity(1);
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        Statement st2=connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * from shop_db");
        ResultSet rss = statement1.executeQuery("select * from customer_db");
        System.out.println("okk");


        if (check()) {
            if (prefix()) {
                String us = Text1.getText();
                String new_us = us.substring(4);
                String us2 = us.substring(0, 3);
                char p = us.charAt(3);
                System.out.println(p);
                String prefix = us.substring(0, 4);
                System.out.println(new_us);
                System.out.println(prefix);


                String shop = "sho_";
                String cus = "cus_";
                if (prefix.equalsIgnoreCase(shop)) {
                    while (rs.next()) {
                        System.out.println("okk");
                        if (new_us.equals(rs.getString("shop_id")) && Text2.getText().equals(rs.getString("password"))) {
//
// statement.execute("INSERT INTO userr (shop_name,shop_id,owner_name,password,address,phoneno,emailid) VALUES ('" + rs.getString(1) + "','" + rs.getString(2) + "','" + rs.getString(3) + "','" + rs.getString(4) + "','" + rs.getString(5) + "','" + rs.getString(6) + "','" + rs.getString(7) + "')");
                            Random rnd = new Random();
                            shop_name=rs.getString(1);
                            System.out.println(shop_name);
                            int num= rnd.nextInt(100000);
                            System.out.println(num);
                            String sesid=String.valueOf(num);
                            st2.execute("insert into session(sesid, sid) values('" + num + "','" + new_us + "')");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("OTP");
                            alert.setHeaderText("Please remeber this otp until the end of this session");
                            alert.setContentText(num +"  "+ "this is your otp please remember this and enter it in the next page");
                            alert.showAndWait();


                            Parent parent = FXMLLoader.load(getClass().getResource("shop.fxml"));
                            Scene scene = new Scene(parent, 1098, 600);
                            Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            wndow.setScene(scene);
                            wndow.show();
                            return;

                        }
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("YOUR USERNAME AND PASSWORD DONOT MATCH");
                    alert.setContentText("Please renter your username and password");
                    alert.showAndWait();
                } else if (prefix.equalsIgnoreCase(cus)) {
                    System.out.println("okk1");

                    while (rss.next()) {
                        System.out.println("okk");

                        if (new_us.equals(rss.getString("id")) && Text2.getText().equals(rss.getString("password"))) {
//                                    statement1.execute("INSERT INTO userngo (shop_name,shop_id,owner_name,password,address,phoneno,emailid) VALUES ('" + rs.getString(1) + "','" + rs.getString(2) + "','" + rs.getString(3) + "','" + rs.getString(4) + "','" + rs.getString(5) + "','" + rs.getString(6) + "','" + rs.getString(7) + "')");

                            Parent parent = FXMLLoader.load(getClass().getResource("customer.fxml"));
                            Scene scene = new Scene(parent, 1098, 600);
                            Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            wndow.setScene(scene);
                            wndow.show();
                            return;
                        }
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("YOUR USERNAME AND PASSWORD DONOT MATCH");
                    alert.setContentText("Please renter your username and password");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("please change your prefix");
                    alert.setContentText("if you are logging as a customer please prefix your id by cus_ or if you are a pharmacy owner please use prefix sho_");
                    alert.showAndWait();
                }
                if ((us2.equalsIgnoreCase("cus") || us2.equalsIgnoreCase("sho")) && p != '_') {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("please include underscore(_) after adding prefix");
                    alert.setContentText("if you are logging as a customer please prefix your id by cus_ or if you are a pharmacy owner please use prefix sho_");
                    alert.showAndWait();
                }
            }
        }
    }


        public void back2 (ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(parent, 1098, 600);
        Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        wndow.setScene(scene);
        wndow.show();

    }
    }










