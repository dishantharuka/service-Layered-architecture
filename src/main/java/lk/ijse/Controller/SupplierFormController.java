package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dto.SupplierDto;
import lk.ijse.bo.custom.impl.SupplierBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierFormController {

    @FXML
    private Label lblSupplierID;
    @FXML
    private ComboBox cmbuserId;
    @FXML
    private AnchorPane Pane;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactInformation;

    @FXML
    private TableColumn<?, ?> colSupName;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableView tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactInformation;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private TextField txtUserId;

    SupplierBOImpl supplierModel = new SupplierBOImpl();
    UserBOImpl userModel = new UserBOImpl();
    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    public void initialize(){

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            SupplierDto supplierDto = (SupplierDto) newValue;
            if (newValue != null) {
                lblSupplierID.setText(supplierDto.getSupplierId());
                cmbuserId.setValue(supplierDto.getUserID());
                txtAddress.setText(supplierDto.getAddress());
                txtSupplierName.setText(supplierDto.getSupplierName());
                txtContactInformation.setText(supplierDto.getContactInformation());

            }
        });

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colContactInformation.setCellValueFactory(new PropertyValueFactory<>("contactInformation"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadData();
        generateNextId();
        setUserIDs();
    }

    private void setUserIDs() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try{
            String[] userId = userModel.loadAllUsers();

            for (String userIds : userId){
                observableList.add(userIds);
            }
            cmbuserId.setItems(observableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextId() {
        try {
            String appointmentID = supplierModel.generateNextID();
            lblSupplierID.setText(appointmentID);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void loadData() {
        try {
            ArrayList<SupplierDto> all= supplierBO.getAll();
            tblSupplier.getItems().setAll(FXCollections.observableArrayList(all));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ClearOnAction(ActionEvent event) {
        clear();
    }


    private void clear() {
        txtSupplierName.clear();
        txtContactInformation.clear();
        txtAddress.clear();
    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id = lblSupplierID.getText();

        var model = new SupplierBOImpl();

        try {
            boolean isDelete = model.deleteSupplier(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Delete!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    @FXML
    void SaveOnAction(ActionEvent event) {
        String supplierId = lblSupplierID.getText();
        String supplierName = txtSupplierName.getText();
        String contactInformation = txtContactInformation.getText();
        String address = txtAddress.getText();
        String UserID = (String) cmbuserId.getValue();



        try {
            SupplierBOImpl SupplierModel = new SupplierBOImpl();
            boolean b = SupplierModel.saveSupplier(new SupplierDto(supplierId,supplierName,contactInformation,address,UserID ));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier saved!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) {
        String supplierName = txtSupplierName.getText();
        String supplierId = lblSupplierID.getText();
        String contactInformation = txtContactInformation.getText();
        String address = txtAddress.getText();
        String UserID = (String) cmbuserId.getValue();




        var dto = new SupplierDto(supplierId,supplierName,contactInformation,address,UserID);

        var model = new SupplierBOImpl();

        try {
            boolean isSaved = model.updateSupplier(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Update!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void searchOnAction(ActionEvent actionEvent) {

        String code = txtSupplierId.getText();

        try {
            SupplierDto dto = supplierBO.searchAppointment(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Supplier not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(SupplierDto dto) {
        txtSupplierId.setText(dto.getSupplierId());
        txtSupplierName.setText(dto.getSupplierName());
        txtContactInformation.setText(dto.getContactInformation());
        txtAddress.setText(dto.getAddress());
        txtUserId.setText(dto.getUserID());
    }

    public void supplierItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/SupplirInventory.fxml"));
        this.Pane.getChildren().clear();
        this.Pane.getChildren().add(parent);

    }


}
