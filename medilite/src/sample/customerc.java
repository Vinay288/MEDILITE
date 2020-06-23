package sample;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class customerc implements Initializable {

    Object val;
    String ss1,ss2;

    @FXML private TableView<Customer> ctable;
    @FXML private TableView<Medicine> mtable;

    @FXML private TableColumn<Customer,String> snamecol;
    @FXML private TableColumn<Customer,String> anamecol;
    @FXML private TableColumn<Customer,String> sidcol;

    @FXML private TableColumn<Medicine,String> medcol;

    @FXML private Button clearb,searchb1,searchb2,reloadb1,reloadb2;
    @FXML private TextField sfield1,sfiel2;
    public class Customer
    {
        private String sname,aname,sid;


        public Customer(String sname,String aname,String sid)
        {
            this.sname=sname;
            this.aname=aname;
            this.sid=sid;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }


        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

    }



    public class Medicine
    {
        private String med;

        public Medicine(String med)
        {
            this.med=med;
        }

        public String getMed() {
            return med;
        }

        public void setMed(String med) {
            this.med = med;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        snamecol.setCellValueFactory(new PropertyValueFactory<Customer, String>("sname"));
        anamecol.setCellValueFactory(new PropertyValueFactory<Customer, String>("aname"));
        sidcol.setCellValueFactory(new PropertyValueFactory<Customer, String>("sid"));

        medcol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("med"));

        try {
            ctable.setItems(getDetails());
        }
        catch(Exception e)
        {
            System.out.println("error");
        }

        ctable.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells=ctable.getSelectionModel().getSelectedCells();

        selectedCells.addListener(new ListChangeListener()
        {
            @Override
            public void onChanged(Change c)
            {
                TablePosition tablePosition = (TablePosition)selectedCells.get(0);
                val= tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                System.out.println("Selected Value"+val);
                try {
                    mtable.setItems(getDetails1());
                }
                catch(Exception e)
                {
                    System.out.println("error2");
                }
            }
        });
    }


    public ObservableList<Customer> getDetails()throws Exception
    {

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
        Statement statement = connection.createStatement();
       // Statement statement1 = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * from shop_db");
//        ResultSet rss = statement1.executeQuery("select * from customer_db");
        ObservableList<Customer> cus=FXCollections.observableArrayList();
        while(rs.next()){

            String str1=rs.getString("shop_name");
            String str2=rs.getString("address");
            String str3=rs.getString("shop_id");
            cus.add(new Customer(str1,str2,str3));


        }
//        ObservableList<Customer> cus=FXCollections.observableArrayList();
//        cus.add(new Customer("Siddharth"));


        return cus;


    }




    public ObservableList<Medicine> getDetails1()throws Exception
    {

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * from " + val + "");

        ObservableList<Medicine> cus=FXCollections.observableArrayList();
        while(rs.next()){

            String str1=rs.getString("medicine");
            cus.add(new Medicine(str1));
        }

        return cus;


    }


    public ObservableList<Customer> getDetails2()throws Exception
    {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
                Statement statement = connection.createStatement();
                // Statement statement1 = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * from shop_db where shop_name like '"+ss1+"' || address like '"+ss1+"'");

                ObservableList<Customer> cus=FXCollections.observableArrayList();
                while(rs.next()){

                    String str1=rs.getString("shop_name");
                    String str2=rs.getString("address");
                    String str3=rs.getString("shop_id");
                    cus.add(new Customer(str1,str2,str3));


                }
        return cus;
    }

    public ObservableList<Medicine> getDetails3()throws Exception
    {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
        Statement statement = connection.createStatement();
        // Statement statement1 = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * from " +val+ " where medicine like '"+ss2+"'");

        ObservableList<Medicine> cus=FXCollections.observableArrayList();
        while(rs.next()){

            String str1=rs.getString("medicine");

            cus.add(new Medicine(str1));


        }
        return cus;
    }


    public void change(ActionEvent event)throws Exception
    {
        if(event.getTarget()==clearb)
        {
            for(int i=0;i<ctable.getItems().size();i++)
            {
                ctable.getItems().clear();
            }
        }

        if(event.getTarget()==searchb1)
        {
            if(sfield1.getText().isEmpty()) {
                System.out.println("enter medicine name");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Field is Empty");
                alert.setHeaderText("Enter seach parameter");
                alert.setContentText("Please Enter search parameter");
                alert.showAndWait();
            }

            else {
                String ss=sfield1.getText();
                System.out.println(ss);
                ss1="%"+ss+"%";
                System.out.println(ss1);
                for(int i=0;i<ctable.getItems().size();i++)
                {
                    ctable.getItems().clear();
                }

                ctable.setItems(getDetails2());

            }
        }

        if(event.getTarget()==reloadb1){
            ctable.setItems(getDetails());
        }

        if(event.getTarget()==searchb2){

            if(sfiel2.getText().isEmpty()) {
                System.out.println("enter medicine name");
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("Field is Empty");
                alert1.setHeaderText("Enter seach parameter");
                alert1.setContentText("Please Enter search parameter");
                alert1.showAndWait();
            }

            else {
                String ss=sfiel2.getText();
                System.out.println(ss);
                ss2="%"+ss+"%";
                System.out.println(ss2);
                for(int i=0;i<mtable.getItems().size();i++)
                {
                    mtable.getItems().clear();
                }

                mtable.setItems(getDetails3());

            }

        }

        if(event.getTarget()==reloadb2){
            mtable.setItems(getDetails1());
        }
    }



    public void back2(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(parent, 1098, 600);
        Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        wndow.setScene(scene);
        wndow.show();
    }
}
