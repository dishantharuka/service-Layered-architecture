package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RecordBO;
import lk.ijse.bo.custom.impl.RecordBOImpl;
import lk.ijse.dto.RecordDto;
import lk.ijse.dto.VehicleDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecordFormController {

    public AnchorPane Pane;
    @FXML
    private TableView tblRecord;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label lblNextRecordID;
    @FXML
    private ComboBox cmbTechnician;
    @FXML
    private ComboBox cmbVehicleId;
    @FXML
    private TableColumn<?, ?> colRecordId;

    @FXML
    private ComboBox combSerDescription;

    @FXML
    private TableColumn<?, ?> colServiceDate;

    @FXML
    private TableColumn<?, ?> colServiceDescription;

    @FXML
    private TableColumn<?, ?> colTechnicianName;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private TextField txtRecord;

    @FXML
    private TextField txtServiceDate;

    @FXML
    private TextField txtServiceDescription;

    @FXML
    private TextField txtTechnicianName;

    @FXML
    private TextField txtVehi;

    RecordBO recordBO = (RecordBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RECORD);
    @FXML
    void ClearOnAction(ActionEvent event) {
        clear();
    }


    public void initialize(){

        tblRecord.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            RecordDto recordDto = (RecordDto) newValue;
            if (newValue != null) {
                lblNextRecordID.setText(recordDto.getId());
                cmbVehicleId.setValue(recordDto.getVehicleID());
                cmbTechnician.setValue(recordDto.getTechnicianName());
                combSerDescription.setValue(recordDto.getServiceDescription());
                datePicker.setValue(LocalDate.parse(recordDto.getServiceDate()));
            }
        });


        colRecordId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        combSerDescription.getItems().addAll("Oil Change", "Body Wash", "Full Service");
        colServiceDescription.setCellValueFactory(new PropertyValueFactory<>("serviceDescription"));
        colTechnicianName.setCellValueFactory(new PropertyValueFactory<>("technicianName"));
        colServiceDate.setCellValueFactory(new PropertyValueFactory<>("serviceDate"));

        loadData();
        loadAllVehicles();
        loadAllTechnicianNames();
        generateNextRecordID();
    }

    private void generateNextRecordID() {
        RecordBOImpl recordModel = new RecordBOImpl();
        try {
            String recordId = recordModel.generateNextRecordId();
            System.out.println(recordId);
            lblNextRecordID.setText(recordId);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void loadAllTechnicianNames() {

        ArrayList<String> technicianNames = null;
        try {
            technicianNames = recordBO.getTechnicianNames();
            ObservableList<String> technicians = FXCollections.observableArrayList();
            for (String technician : technicianNames ){
                technicians.add(technician);
            }
            cmbTechnician.setItems(technicians);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAllVehicles() {
        List<VehicleDto> dtos = null;
        try {
            dtos = recordBO.getAllVehicles();
            ObservableList<String> vehicleIds= FXCollections.observableArrayList();
            for (VehicleDto dto:dtos){
                vehicleIds.add(dto.getId());
            }
            cmbVehicleId.setItems(vehicleIds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadData() {

        try {
            ArrayList<RecordDto> all= recordBO.getAll();
            tblRecord.getItems().setAll(FXCollections.observableArrayList(all));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void clear() {
        lblNextRecordID.setText("");
        cmbVehicleId.setValue("");
        cmbTechnician.setValue("");
        combSerDescription.setValue("");

    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id = lblNextRecordID.getText();

        var model = new RecordBOImpl();

        try {
            boolean isDelete = model.deleteRecord(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Record Delete!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    @FXML
    void SaveOnAction(ActionEvent event) {
        String id = lblNextRecordID.getText();
        String vehi = (String) cmbVehicleId.getValue();
        String serviceDateId = String.valueOf(datePicker.getValue());
        String serviceDescription = (String) combSerDescription.getValue();
        String technicianName = (String) cmbTechnician.getValue();



        try {
            RecordBOImpl RecordModel = new RecordBOImpl();
            boolean b = RecordModel.saveRecord(new RecordDto(id,vehi,serviceDateId,serviceDescription,technicianName ));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Record saved!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = lblNextRecordID.getText();
        String vehi = (String) cmbVehicleId.getValue();
        String serviceDateId = String.valueOf(datePicker.getValue());
        String serviceDescription = (String) combSerDescription.getValue();
        String technicianName = (String) cmbTechnician.getValue();



        var dto = new RecordDto(id,vehi,serviceDateId,serviceDescription,technicianName);

        var model = new RecordBOImpl();

        try {
            boolean isSaved = model.updateRecord(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Record Update!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }




    public void searchOnAction(ActionEvent actionEvent) {

        String code = txtRecord.getText();

        try {
            RecordDto dto = recordBO.searchAppointment(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Record not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SelectOnAction(ActionEvent event) {

    }

    private void setFields(RecordDto dto) {
        txtRecord.setText(dto.getId());
        txtVehi.setText(dto.getVehicleID());
        txtServiceDate.setText(dto.getServiceDate());
        txtServiceDescription.setText(dto.getServiceDescription());
        txtTechnicianName.setText(dto.getTechnicianName());
    }
}
