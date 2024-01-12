package lk.ijse.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class DashboardFormController {
    public AnchorPane ChangePane;
    public Label lbltime;
    public Label lbldate;
    private Scene scene;

    public void initialize() throws IOException {
        loadTime();


        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }

    private void loadTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd:MMM:yyyy");
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            lbltime.setText(dateFormat.format(new Date()));
            lbldate.setText(dateFormat1.format(new Date()));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }


    public DashboardFormController() {
    }

    public static void onTheTopNavigation(Pane pane, String link) {
        try {
            FXMLLoader loader = new FXMLLoader(DashboardFormController.class.getResource("/view/" + link));
            Parent root = loader.load();
            pane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AppoinmentOnAction(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/Appoinment_form.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);

    }

    public void reportOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/ReportForm.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }
    public void ItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/Item_form.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }

    public void SupplierOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/Supplier_form.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }

    public void EmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/Employee_form.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }

    public void VehicleOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/VehicleForm.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }

    public void CustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/Customer_form.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }

    public void PaymentsOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/Payment_form.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }

    public void RecordOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.<Parent>load(this.getClass().getResource("/view/Record_form.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }

    public void DashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"));
        this.ChangePane.getChildren().clear();
        this.ChangePane.getChildren().add(parent);
    }

    public void SignOutOnAction(ActionEvent actionEvent) throws IOException {

        Node node = (Node) actionEvent.getSource();
        Stage stg = (Stage) node.getScene().getWindow();
        stg.close();
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/Login_form.fxml"))));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMaxHeight(768);
        stage.setMaxWidth(1366);
        stage.show();

    }


}
