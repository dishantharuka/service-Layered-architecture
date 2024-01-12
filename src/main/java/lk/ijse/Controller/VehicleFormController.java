package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.VehicleBO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.VehicleDto;
import lk.ijse.bo.custom.impl.CustomerBOImpl;
import lk.ijse.bo.custom.impl.VehicleBOImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleFormController {

    public TableView tblVehicle;
    public TextField txtAppoinmentId;
    @FXML
    private ComboBox cmbModel;
    @FXML
    private ComboBox cmbCusId;
    @FXML
    private Label lblvehicleID;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colLicensePlate;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private TableColumn<?, ?> colYear;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtLicensePlate;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtVehicleId;

    @FXML
    private TextField txtYear;

    VehicleBOImpl vehicleModel = new VehicleBOImpl();
    CustomerBOImpl customerModel = new CustomerBOImpl();
     VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICLE);


    public void initialize() throws SQLException {

        tblVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            VehicleDto vehicleDto = (VehicleDto) newValue;
            if (newValue != null) {
                lblvehicleID.setText(vehicleDto.getId());
                cmbModel.setValue(vehicleDto.getModel());
                txtYear.setText(vehicleDto.getYear());
                cmbCusId.setValue(vehicleDto.getCustomerID());

            }
        });


        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colLicensePlate.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

        loadData();
        loadAllCusIDs();
        generateVehicleID();
        setCmbModel();
    }

    private void setCmbModel() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        String [] models = {"Car","Van","Bus"};

        for (String model : models){
            observableList.add(model);
        }
        cmbModel.setItems(observableList);

    }

    private void loadAllCusIDs() throws SQLException {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<CustomerDto> customerDto = customerModel.getAll();

        for (CustomerDto dto : customerDto){
            observableList.add(dto.getId());
        }
        cmbCusId.setItems(observableList);

    }

    private void generateVehicleID() {
        try {
            String appointmentID = vehicleModel.generateNextVehicleID();
            lblvehicleID.setText(appointmentID);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }


    private void loadData() throws SQLException {
        VehicleBOImpl vehicleModel = new VehicleBOImpl();
        ArrayList<VehicleDto> all = vehicleModel.getAll();
        tblVehicle.getItems().setAll(FXCollections.observableArrayList(all));
    }


    @FXML
    void ClearOnAction(ActionEvent event) {
        clear();
    }


    private void clear() {
    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id = lblvehicleID.getText();

        var model = new VehicleBOImpl();

        try {
            boolean isDelete = model.deleteVehicle(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Delete!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }




    @FXML
    void saveOnAction(ActionEvent event) {
        String id = lblvehicleID.getText();
        String Model = String.valueOf(cmbModel.getValue());
        String year = txtYear.getText();
        String licensePlate = txtLicensePlate.getText();
        String customerId = String.valueOf(cmbCusId.getValue());



        try {
            VehicleBOImpl vehicleModel = new VehicleBOImpl();
            boolean b = vehicleModel.saveVehicle(new VehicleDto(id, Model, year, licensePlate, customerId));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void UpdateOnAction(ActionEvent event) {
        String id = lblvehicleID.getText();
        String Model = (String) cmbModel.getValue();
        String year = txtYear.getText();
        String licensePlate = txtLicensePlate.getText();
        String customerId = (String) cmbCusId.getValue();



        var dto = new VehicleDto(id, Model, year, licensePlate, customerId);

        var model = new VehicleBOImpl();

        try {
            boolean isSaved = model.updateVehicle(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Update!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void searchOnAction(ActionEvent actionEvent) {

        String code = txtVehicleId.getText();

        try {
            VehicleDto dto = vehicleBO.searchVehicle(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Vehicle not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(VehicleDto dto) {
        txtVehicleId.setText(dto.getId());
        txtModel.setText(dto.getModel());
        txtYear.setText(dto.getYear());
        txtLicensePlate.setText(dto.getLicensePlate());
        txtCustomerId.setText(dto.getCustomerID());

    }
}
