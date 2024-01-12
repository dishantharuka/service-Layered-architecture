package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SupplierInventoryBO;
import lk.ijse.dto.SupplierInventoryDto;
import lk.ijse.bo.custom.impl.SupplierInventoryBOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplirInventoryFormController {

    public javafx.scene.control.TableView tblSupplierInventory;

    @FXML
    private AnchorPane Pane;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colItemID;

    @FXML
    private TableColumn<?, ?> colSupplierID;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtSupplierID;

    @FXML
    private TextField txtTime;
    SupplierInventoryBO supplierInventoryBO = (SupplierInventoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIERINVENTORY);
    public void initialize() throws SQLException {
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        colItemID.setCellValueFactory(new PropertyValueFactory<>("ItemID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));



        loadData();
    }

    private void loadData() throws SQLException {
        ArrayList<SupplierInventoryDto> all = supplierInventoryBO.getAll();
        tblSupplierInventory.getItems().setAll(FXCollections.observableArrayList(all));
    }
    private void clear() {
        txtSupplierID.clear();
        txtItemID.clear();
        txtDate.clear();
        txtTime.clear();
    }

    @FXML
    void ClearOnAction(ActionEvent event) {
        clear();
    }



    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id = txtSupplierID.getText();

        var model = new SupplierInventoryBOImpl();

        try {
            boolean isDelete = model.deleteSupplierInventory(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SaveOnAction(ActionEvent event) {
        String supId = txtSupplierID.getText();
        String itemId = txtItemID.getText();
        String date = txtDate.getText();
        String time = txtTime.getText();

        try {
            boolean b = supplierInventoryBO.saveSupplierInventory(new SupplierInventoryDto(supId, itemId, date, time));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String supId = txtSupplierID.getText();
        String itemId = txtItemID.getText();
        String date = txtDate.getText();
        String time = txtTime.getText();



        var dto = new SupplierInventoryDto(supId, itemId, date, time);

        var model = new SupplierInventoryBOImpl();

        try {
            boolean isSaved = model.updateSupplierInventory(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Update!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {

        String code = txtSupplierID.getText();

        try {
            SupplierInventoryDto dto = supplierInventoryBO.searchAppointment(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Supplier item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(SupplierInventoryDto dto) {
        txtSupplierID.setText(dto.getSupplierID());
        txtItemID.setText(dto.getItemID());
        txtDate.setText(dto.getDate());
        txtTime.setText(dto.getTime());

    }

}
