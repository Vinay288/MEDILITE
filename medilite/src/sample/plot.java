package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class plot implements Initializable {
    @FXML
    private LineChart<?,?> grp;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;

    String shopid;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
        //final NumberAxis yAxis = new NumberAxis();
        //xAxis.setLabel("time/s");
        //xAxis.setAnimated(false); // axis animations are removed
        //yAxis.setLabel("Value");
        //yAxis.setAnimated(false); // axis animations are removed

        //creating the line chart with two axis created above
        //final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        //lineChart.setAnimated(false); // disable animations
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Data Series");


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MySQL", "root", "omsairam");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from session");
            rs.next();
            shopid=rs.getString(2);
            String shopid2=shopid+"2";
            Statement st2 =connection.createStatement();
            ResultSet rs2=st2.executeQuery("select * from "+shopid2);
            while(rs2.next()) {
                Float b=rs2.getFloat(2);
                DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

                String a =dateFormat.format(rs2.getDate(1));
                System.out.println(a);
                series.getData().add(new XYChart.Data(a, b));
            }
        }
        catch(Exception e)
        {

        }

        //rs.next();



        // add series to chart
        grp.getData().addAll(series);
    }
}

