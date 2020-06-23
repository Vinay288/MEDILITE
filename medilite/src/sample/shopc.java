package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class shopc implements Initializable {

    @FXML
    private TableView<Person> table1;


    @FXML
    private TableColumn<Person, String> medcol2;
    @FXML
    private TableColumn<Person, String> pricecol;
    @FXML
    private TableColumn<Person, String> sypcol;
    @FXML
    private TableColumn<Person, String> costcol;

    @FXML
    private TableView<Medicine> avatab;
    @FXML
    private TableColumn<Medicine, String> medcol;
    @FXML
    private TableColumn<Medicine, String> tabcol;
    @FXML
    private TableColumn<Medicine, String> expcol;
    @FXML
    private Button sumb, clearb, billb, addb, upb,btn;

    @FXML
    private TextField otp;
    @FXML
    private TextField sid;
    @FXML
    private TextField med, tab, price, exp, tab2, price2, med2;
    @FXML
    private Text tot;
    @FXML
    private Text na;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton finalbill;

    @FXML private VBox dialogVbox;

    @FXML
    private JFXTextField sumtext;
    public String shopid, ot,ss1;
    List<String> lis;
    Object val;
    Float sum2 = 0.0f;
    String mm;

    public class Person {
        private String medname2, priceno, sypno, costno;


        public Person(String medname2, String priceno, String sypno, String costno) {
            this.medname2 = medname2;
            this.priceno = priceno;
            this.sypno = sypno;
            this.costno = costno;
        }


        public String getMedname2() {
            return medname2;
        }

        public void setMedname2(String medname2) {
            this.medname2 = medname2;
        }

        public String getPriceno() {
            return priceno;
        }

        public void setPriceno(String priceno) {
            this.priceno = priceno;
        }

        public String getSypno() {
            return sypno;
        }

        public void setSypno(String sypno) {
            this.sypno = sypno;
        }

        public String getCostno() {
            return costno;
        }

        public void setCostno(String costno) {
            this.costno = costno;
        }
    }


    public class Medicine {
        private String medName, tabNum, expDate;


        public Medicine(String medName, String tabNum, String expDate) {
            this.medName = medName;
            this.tabNum = tabNum;
            this.expDate = expDate;
        }


        public String getMedName() {
            return medName;
        }

        public void setMedName(String medName) {
            this.medName = medName;
        }

        public String getTabNum() {
            return tabNum;
        }

        public void setTabNum(String tabNum) {
            this.tabNum = tabNum;
        }

        public String getExpDate() {
            return expDate;
        }

        public void setExpDate(String expDate) {
            this.expDate = expDate;
        }
    }


    @Override

    public void initialize(URL url, ResourceBundle rb) {

        billb.setDisable(true);
        addb.setDisable(true);
        upb.setDisable(true);
        b2.setDisable(true);
        btn.setDisable(true);
        finalbill.setDisable(true);


    }

    ObservableList<Person> people = FXCollections.observableArrayList();


    public ObservableList<Person> getPeople() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * from " + shopid + " where medicine='" + val + "'");

        rs.next();
        String str1 = rs.getString("medicine");
        //String str3=rs.getString("tablete");
        String str2 = rs.getString("price");
        people.add(new Person(str1, str2, "", ""));


        return people;


    }

    public ObservableList<Person> getPeople1(List<String> columnData3, List<String> columnData2, List<String> columnData1, List<String> columnData4) throws Exception {


        for (int i = 0; i < table1.getItems().size(); i++) {
            table1.getItems().clear();
        }


        for (int i = 0; i < columnData1.size(); i++) {
            people.add(new Person(columnData3.get(i), columnData2.get(i), columnData1.get(i), columnData4.get(i)));


        }

        return people;


    }


    public ObservableList<Medicine> getMedicine() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
        Statement statement = connection.createStatement();
        // Statement statement1 = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * from " + shopid + "");

        ObservableList<Medicine> cus = FXCollections.observableArrayList();
        while (rs.next()) {

            String str1 = rs.getString(1);
            String str2 = rs.getString(2);
            String str3 = rs.getString(4);
            cus.add(new Medicine(str1, str2, str3));


        }
        return cus;
    }


    public void change1(ActionEvent event) throws Exception {
        //Float sum2 = 0.0f;

        if (event.getTarget() == sumb) {


            TableColumn<Person, String> column1 = sypcol;
            TableColumn<Person, String> column2 = pricecol;
            TableColumn<Person, String> column3 = medcol2;

            List<String> columnData1 = new ArrayList<>();
            List<String> columnData2 = new ArrayList<>();
            List<String> columnData3 = new ArrayList<>();
            List<String> columnData4 = new ArrayList<>();

            for (Person item : table1.getItems()) {
                columnData1.add(column1.getCellObservableValue(item).getValue());
                columnData2.add(column2.getCellObservableValue(item).getValue());
                columnData3.add(column3.getCellObservableValue(item).getValue());

            }

            sum2 = 0.0f;
            String mm = "";
            for (int i = 0; i < columnData1.size(); i++) {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from " + shopid + "");

                //statement.executeQuery("insert into buffer values('"+columnData3+"','"+columnData1+"')");
                while (rs.next()) {

                    System.out.println(columnData3.get(i));
                    System.out.println(rs.getString(1));
                    Float st = Float.parseFloat(columnData1.get(i));
                    Float dif = rs.getFloat(2) - st;
                    if (dif < 0) {
                        if (rs.getString(1).equalsIgnoreCase(columnData3.get(i)))
                            mm = mm + " , " + columnData3.get(i);

                    }
                }
                System.out.println(columnData1.get(i));
                Float a = Float.parseFloat(columnData1.get(i));
                System.out.println(columnData2.get(i));
                Float b = Float.parseFloat(columnData2.get(i));
                Float c = a * b;
                String cost = Float.toString(c);
                columnData4.add(cost);
                sum2 = sum2 + c;
                System.out.println(c);
            }

            System.out.println(sum2);

            if (mm.isEmpty()) {
//                    tot.setText(Float.toString(sum2));
                table1.setItems(getPeople1(columnData3, columnData2, columnData1, columnData4));
                tot.setText(Float.toString(sum2));
                billb.setDisable(false);

            }


            if (!mm.isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Insufficient quantity");
                alert.setHeaderText("Medicinal qunatity");
                alert.setContentText(mm + " " + "available quantity of these medicines are not enough please refill the medicine and then bill");
                alert.showAndWait();
            }
        }

        int u = 4;

        if (event.getTarget() == clearb) {

            Thread t = new Thread(new demo());

            t.start();

        }

        if (event.getTarget() == billb) {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            tot.setText("");
            TableColumn<Person, String> column1 = sypcol;
            TableColumn<Person, String> column3 = medcol2;
            List<String> columnData1 = new ArrayList<>();
            List<String> columnData3 = new ArrayList<>();
            for (Person item : table1.getItems()) {
                columnData1.add(column1.getCellObservableValue(item).getValue());
                columnData3.add(column3.getCellObservableValue(item).getValue());
            }
            String mm = "";
            String mm2 = "";
            for (int i = 0; i < columnData1.size(); i++) {
                ResultSet rs = statement.executeQuery("select * from " + shopid + "");
                while (rs.next()) {
                    System.out.println(columnData3.get(i));
                    System.out.println(rs.getString(1));
                    Float st = Float.parseFloat(columnData1.get(i));
                    Float dif = rs.getFloat(2) - st;
                    if (rs.getString(1).equalsIgnoreCase(columnData3.get(i))) {
                        System.out.println("ok");
                        statement1.executeUpdate("update " + shopid + " set tablete ='" + dif + "' where medicine = '" + columnData3.get(i) + "'");
                        if (dif == 0) {
                            mm2 = mm2 + " ," + columnData3.get(i);
                        }
                    }
                }
                String shopid1=shopid+"1";

                Statement statement3 = connection.createStatement();
                Statement stt=connection.createStatement();
                Statement statement4 = connection.createStatement();
                    statement3.execute("create table if not exists "+shopid1+" (tid numeric (5), tcost float(10,3) )");
                    ResultSet rs2=stt.executeQuery("select count(*) from "+shopid1);
                    rs2.next();
                    int a=rs2.getInt(1)+1;


                    statement4.execute(" insert into " +shopid1 + " values('"+a+"', '" + sum2 + "')");


            }

            if (!mm2.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Remainder!!");
                alert.setHeaderText("Refill these medicines");
                alert.setContentText(mm2 + " These medicines got over just now ,here is the remainder please refill them");
                alert.showAndWait();
            }


            billb.setDisable(true);
            for (int i = 0; i < table1.getItems().size(); i++) {
                table1.getItems().clear();
            }
            avatab.setItems(getMedicine());
            tot.setText("");
        }

    }


    class demo implements Runnable {

        @Override
        public void run() {

            try {
                Person selecteditem = table1.getSelectionModel().getSelectedItem();


                people.remove(selecteditem);
            } catch (Exception e) {

            }

        }
    }


    public void otpbutton(ActionEvent event) throws Exception {

        if (chec()) {
            medcol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("medName"));
            tabcol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("tabNum"));
            expcol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("expDate"));
            avatab.setItems(getMedicine());


            avatab.getSelectionModel().setCellSelectionEnabled(true);
            ObservableList selectedCells = avatab.getSelectionModel().getSelectedCells();

            selectedCells.addListener(new ListChangeListener() {
                @Override
                public void onChanged(Change c) {
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                    System.out.println("Selected Value" + val);
                    try {
                        table1.setItems(getPeople());
                    } catch (Exception e) {
                    }

                }
            });


            medcol2.setCellValueFactory(new PropertyValueFactory<Person, String>("medname2"));
            pricecol.setCellValueFactory(new PropertyValueFactory<Person, String>("priceno"));
            sypcol.setCellValueFactory(new PropertyValueFactory<Person, String>("sypno"));
            costcol.setCellValueFactory(new PropertyValueFactory<Person, String>("costno"));


            table1.setEditable(true);
            sypcol.setCellFactory(TextFieldTableCell.forTableColumn());
            sypcol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {

                @Override
                public void handle(TableColumn.CellEditEvent<Person, String> t) {

                    ((Person) t.getTableView().getItems().get(
                            t.getTablePosition().getRow()
                    )).setSypno(t.getNewValue());
                }
            });


            //table1.setItems(getPeople());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("OTP and ID");
            alert.setHeaderText("enter correct the otp and ID");
            alert.setContentText("reenter the otp and ID below");
            alert.showAndWait();

        }

    }

    public boolean chec() throws Exception {

        String sh = "welcome back ";

        ot = otp.getText();
        int ott = Integer.valueOf(ot);
        String idn = sid.getText();
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
        Statement statement = connection.createStatement();
        Statement st = connection.createStatement();
       // ResultSet rs2 = st.executeQuery("select * from shop_db where shop_id = '"+sid+"'");
        ResultSet rs = statement.executeQuery("select * from session");


        while (rs.next()) {
            if (ott == rs.getInt(1) && idn.equals(rs.getString(2))) {
                ResultSet rs3 = st.executeQuery("select * from shop_db where shop_id = '"+idn+"'");
                rs3.next();
                mm=rs3.getString(1);
                shopid = rs.getString(2);
                addb.setDisable(false);
                upb.setDisable(false);
                b2.setDisable(false);
                btn.setDisable(false);
                finalbill.setDisable(false);
                na.setText(sh+" "+rs3.getString(1));
                return true;
            }

        }

        return false;


    }

    public void addnew(ActionEvent event) throws Exception {
        if (tab.getText() == null || med.getText() == null || price.getText() == null || exp.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("enter all the fields");
            alert.setHeaderText("enter all fields correctly");
            alert.setContentText("reenter the fields");
            alert.showAndWait();
        } else {

            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
            Statement statement = connection.createStatement();
            Statement st = connection.createStatement();
            float t = Float.valueOf(tab.getText());
            float p = Float.valueOf(price.getText());
            ResultSet rs = st.executeQuery("select * from "+shopid);
            int qwe=0;
            while (rs.next()) {
                if (med.getText().equalsIgnoreCase(rs.getString(1))) {
                    qwe=1;

                }
            }
            if(qwe==0)

//             statement.execute("insert into"+shopid+" values ('" +med.getText()+ "','" + tab.getText())"','"+price.getText()+"','"+exp.getText()"')");
            {
                statement.execute("insert into " + shopid + "(medicine,tablete,price,exp_date) values('" + med.getText() + "','" + t + "','" + p + "','" + exp.getText() + "')");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("values are added");
                alert.setHeaderText("succesfully added");
                alert.setContentText("values added");
                alert.showAndWait();
                med.setText("");
                tab.setText("");
                price.setText("");
                exp.setText("");
                avatab.setItems(getMedicine());
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("already exist");
                alert.setHeaderText("medicine with this name already exist");
                alert.setContentText("please use update field");
                alert.showAndWait();
            }
        }

    }

    public void update(ActionEvent event) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
        Statement statement = connection.createStatement();
        Statement st = connection.createStatement();

        if (price2.getText().isEmpty()) {
            ResultSet rs = statement.executeQuery("select * from " + shopid + " where medicine = '" + med2.getText() + "'");
            rs.next();
            float t1 = rs.getFloat(2);
            float t2 = Float.valueOf(tab2.getText());
            float t = t1 + t2;
            st.executeUpdate("update " + shopid + " set tablete ='" + t + "' where medicine = '" + med2.getText() + "'");


        } else {

            ResultSet rs = statement.executeQuery("select * from " + shopid + " where medicine = '" + med2.getText() + "'");
            rs.next();
            float p = Float.valueOf(price2.getText());
            float t2 = Float.valueOf(tab2.getText());
            float t1 = rs.getFloat(2);
            float tf = t1 + t2;
            st.executeUpdate("update " + shopid + " set tablete ='" + tf + "',price='" + p + "' where medicine = '" + med2.getText() + "'");


        }
        avatab.setItems(getMedicine());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("values are updated");
        alert.setHeaderText("succesfully updated");
        alert.setContentText("values updated ");
        alert.showAndWait();
        med2.setText("");
        tab2.setText("");
        price2.setText("");



    }

    public void log(ActionEvent event) throws Exception {
        loginc a = new loginc();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("do you want to logout?");
        alert.setHeaderText("Press ok if you really want to logout");
        alert.setContentText("are you sure you want to logout?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("medilite");
            alert1.setHeaderText("you have been logged out");
            alert1.setContentText("Thank you " + mm + "for using medilite,you have been logged out succesfully");
            alert1.showAndWait();

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from session where sesid = '" + ot + "'");
            Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(parent, 1098, 600);
            Stage wndow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            wndow.setScene(scene);
            wndow.show();

    }
}
    public void sea(ActionEvent event) throws Exception {
        if(sumtext.getText().isEmpty()) {
            System.out.println("enter medicine name");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Field is Empty");
            alert.setHeaderText("Enter seach parameter");
            alert.setContentText("Please Enter search parameter");
            alert.showAndWait();
        }

        else {
            String ss=sumtext.getText();
            System.out.println(ss);
             ss1="%"+ss+"%";
            System.out.println(ss1);
            for(int i=0;i<avatab.getItems().size();i++)
            {
                avatab.getItems().clear();
            }

            avatab.setItems(getDetails2());

        }





    }
    public ObservableList<Medicine> getDetails2()throws Exception
    {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
        Statement statement = connection.createStatement();
        // Statement statement1 = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * from "+shopid+" where medicine like '"+ss1+"'");

        ObservableList<Medicine> cus=FXCollections.observableArrayList();
        while(rs.next()){

            String str1=rs.getString(1);
            String str2=rs.getString(2);
            String str3=rs.getString(4);
            cus.add(new Medicine(str1,str2,str3));


        }
        return cus;
    }
    public void clr(ActionEvent event) throws Exception {
        tot.setText("");

    for(int i=0;i<table1.getItems().size();i++)
    {
        table1.getItems().clear();
    }
    }
    public void qww(ActionEvent event)throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("graph.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Stage");
        stage.setScene(scene);
        stage.show();

    }
    public void fin(ActionEvent event) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
        Statement statement = connection.createStatement();
        Statement st2=connection.createStatement();
        String shopid1=shopid+"1";
        String shopid2=shopid+"2";
        float su=0.0f;
        ResultSet rs1=statement.executeQuery("select * from "+shopid1);
        while (rs1.next())
        {
            su=su+rs1.getFloat(2);
        }
        statement.executeUpdate("drop table "+shopid1);
        Calendar calendar=Calendar.getInstance();
        java.sql.Date db=new java.sql.Date(calendar.getTime().getTime());
        st2.execute("insert into " + shopid2 + " values ('"+db+"','"+su+"')");






    }

    }

