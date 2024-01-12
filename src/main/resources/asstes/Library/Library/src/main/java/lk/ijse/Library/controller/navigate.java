package lk.ijse.Library.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class navigate {
    public static void navigate(AnchorPane Pane, String path,String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(navigate.class.getResource("/view/"+path));
        AnchorPane newScene = fxmlLoader.load();
        //((MainDashbordFormController)(fxmlLoader.getController())).initData(name);
        Pane.getChildren().clear();
        Pane.getChildren().setAll(newScene);
    }
}
