package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application{
        public static void main(String[] args) {
            launch(args);
        }
        public static Stage stage;
        public void start(Stage stage) throws Exception{
            this.stage = stage;
            Parent parent = FXMLLoader.load(this.getClass().getResource("/View/Login_form.fxml"));
            Scene scene = new Scene(parent);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("BASIL AUTO CARE");
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
        }
    }
