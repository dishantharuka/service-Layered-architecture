package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dto.itemDto;
import lk.ijse.bo.custom.impl.ItemBOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemFormController {

    public TableView tblItem;
    public Label lblItemId;
    @FXML
    private AnchorPane Pane;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private ImageView imgItem;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    public AnchorPane ChangePane;
    private Scene scene;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void initialize(){

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           itemDto Dto = (itemDto) newValue;
            if (newValue != null) {
                lblItemId.setText(Dto.getId());
                txtItemName.setText(Dto.getName());
                txtQuantity.setText(Dto.getQty());
                txtUnitPrice.setText(Dto.getUniPrice());

            }
        });

        generateNextID();

        colItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UniPrice"));
        loadData();
    }

    private void generateNextID() {
       ItemBOImpl itemModel = new ItemBOImpl();
        try {
            String Id = itemModel.generateNextId();
            lblItemId.setText(Id);

        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void loadData() {
        try {
            ArrayList<itemDto> all = itemBO.getAll();
            tblItem.getItems().setAll(FXCollections.observableArrayList(all));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}

    @FXML
    void ClearOnAction(ActionEvent event) {
        clear();
    }

    private void clear() {
        txtItemId.clear();
        txtItemName.clear();
        txtQuantity.clear();
        txtUnitPrice.clear();


    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id = lblItemId.getText();

        var model = new ItemBOImpl();

        try {
            boolean isDelete = model.deleteItem(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "item Delete!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SaveOnAction(ActionEvent event) {
        String id = lblItemId.getText();
        String name = txtItemName.getText();
        String qty = txtQuantity.getText();
        String UniPrice = txtUnitPrice.getText();


        try {
            ItemBOImpl itemModel = new ItemBOImpl();
            boolean b = itemModel.saveItem(new itemDto(id, name, qty, UniPrice));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "item saved!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) {
        String id = lblItemId.getText();
        String name = txtItemName.getText();
        String qty = txtQuantity.getText();
        String UniPrice = txtUnitPrice.getText();



        var dto = new itemDto(id, name, qty, UniPrice);

        var model = new ItemBOImpl();

        try {
            boolean isSaved = model.updateItem(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "item Update!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {

        String code = txtItemId.getText();

        try {
            itemDto dto = itemBO.searchItem(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Appointment not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void OrderOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/placeOrder_form.fxml"));
        this.Pane.getChildren().clear();
        this.Pane.getChildren().add(parent);

    }

    private void setFields(itemDto dto) {
        txtItemId.setText(dto.getId());
        txtItemName.setText(dto.getName());
        txtQuantity.setText(dto.getQty());
        txtUnitPrice.setText(dto.getUniPrice());

    }

}



