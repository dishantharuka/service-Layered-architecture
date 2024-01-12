package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.AppointmentBO;
import lk.ijse.dto.VehicleDto;
import lk.ijse.dto.appoinmentDto;
import lk.ijse.bo.custom.impl.AppoinmentBOImpl;
import lk.ijse.bo.custom.impl.VehicleBOImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppoinmentForm {
        public javafx.scene.control.TableView tblAppoinment;
    @FXML
    private ComboBox cmbTime;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox cmbServiceType;
    @FXML
    private ComboBox cmbVehicle;

    @FXML
        private Label appointmentId;

         @FXML
        private TableColumn<?, ?> colAppoinmentId;

        @FXML
        private TableColumn<?, ?> colServiceType;

        @FXML
        private TableColumn<?, ?> colTime;

        @FXML
        private TableColumn<?, ?> colVehicleId;

        @FXML
        private TableColumn<?, ?> coldate;

        @FXML
        private Label lblName;


        @FXML
        private TextField txtAppoinmentId;

        @FXML
        private TextField txtDate;

        @FXML
        private TextField txtServiceType;

        @FXML
        private TextField txtTime;

        @FXML
        private TextField txtVehicleID;

        AppointmentBO appointmentBO = (AppointmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.APPOINTMENT);

        AppoinmentBOImpl appoinmentModel = new AppoinmentBOImpl();
        VehicleBOImpl vehicleModel = new VehicleBOImpl();

        private void clear() {
            cmbTime.setItems(FXCollections.observableArrayList());
            cmbVehicle.setItems(FXCollections.observableArrayList());
            cmbServiceType.setItems(FXCollections.observableArrayList());
            datePicker.setValue(null);

        }

        public void initialize() throws SQLException {


            tblAppoinment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                appoinmentDto appoinmentDto = (lk.ijse.dto.appoinmentDto) newValue;
                if (newValue != null) {
                    appointmentId.setText(appoinmentDto.getId());
                    datePicker.setValue(LocalDate.parse(appoinmentDto.getDate()));
                    this.cmbTime.setValue(appoinmentDto.getTime());
                    cmbServiceType.setValue(appoinmentDto.getServiceType());
                    cmbVehicle.setValue(appoinmentDto.getVehicleID());
                }
            });




            colAppoinmentId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
            colServiceType.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
            colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
            coldate.setCellValueFactory(new PropertyValueFactory<>("date"));



            loadData();
            generateAppointmentID();
            loadAllVehicleIDs();
            setServiceType();
            setCmbTime();
        }

    private void setCmbTime() {
        ObservableList <String> ob = FXCollections.observableArrayList();
        String[] times = {
                "8:00-9:00 am",
                "9:00-10:00 am",
                "10:00-11:00 am",
                "11:00 am-12:00 pm",
                "12:00-1:00 pm",
                "1:00-2:00 pm",
                "2:00-3:00 pm",
                "3:00-4:00 pm",
                "4:00-5:00 pm",
                "5:00-6:00 pm",
                "6:00-7:00 pm"
        };


        for (String time : times){
            ob.add(time);
        }

        cmbTime.setItems(ob);
    }

    private void setServiceType() {

            ObservableList <String> ob = FXCollections.observableArrayList();
            String[] serviceTypes = {"Body wash","Oil change","Full service"};

            for (String serviceType : serviceTypes){
                ob.add(serviceType);
            }

            cmbServiceType.setItems(ob);
    }

    private void loadAllVehicleIDs() {

            ObservableList<String> observableList = FXCollections.observableArrayList();
            try{
                List<VehicleDto> vehicleDtoList = vehicleModel.loadAllVehicles();

                for (VehicleDto dto : vehicleDtoList){
                    observableList.add(dto.getId());
                }
                cmbVehicle.setItems(observableList);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }


    private void generateAppointmentID() throws SQLException {
        try {
            String appointmentID = appoinmentModel.generateNextAppointmentID();
            appointmentId.setText(appointmentID);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @FXML
        void ClearOnAction(ActionEvent event) {
            clear();
        }

        @FXML
        void DeleteOnAction(ActionEvent event) {
            String id = appointmentId.getText();

            var model = new AppoinmentBOImpl();

            try {
                boolean isDelete = model.deleteAppoinment(id);
                if (isDelete) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Appoinment Delete!").show();
                    clear();
                    loadData();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        @FXML
        void SaveOnAction(ActionEvent event) throws SQLException {

            if (checkAlradyExit()) {

                String id = appointmentId.getText();
                String date = String.valueOf(datePicker.getValue());
                String time = String.valueOf(cmbTime.getValue());
                String serviceType = String.valueOf(cmbServiceType.getValue());
                String vehicleId = String.valueOf(cmbVehicle.getValue());


                try {
                    boolean isTimeReserved = true;
                    if (isTimeReserved) {
                        AppoinmentBOImpl appoinmentModel = new AppoinmentBOImpl();
                        boolean b = appointmentBO.saveAppoinment(new appoinmentDto(id, date, time, serviceType, vehicleId));
                        if (b) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Appoinment saved!").show();
                            clear();
                            loadData();
                        }
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"Alrady have appoinment on that time").show();
            }
        }

        @FXML
        void UpdateOnAction(ActionEvent event) {

            String id = appointmentId.getText();
            String date = String.valueOf(datePicker.getValue());
            String time = (String) cmbTime.getValue();
            String serviceType = (String) cmbServiceType.getValue();
            String vehicleId = (String) cmbVehicle.getValue();


            AppoinmentBOImpl model;
            var dto = new appoinmentDto(id, date, time, serviceType, vehicleId);

            model = new AppoinmentBOImpl();

            try {
                boolean isSaved = model.updateAppoinment(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Appoinment Update!").show();
                    clear();
                    loadData();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }


        @FXML
        void searchOnAction(ActionEvent event) {

            String code = txtAppoinmentId.getText();

            try {
                appoinmentDto dto = appointmentBO.searchAppointment(code);
                if (dto != null) {
                    setFields(dto);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Appointment not found!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        private void setFields(appoinmentDto dto) {
            txtAppoinmentId.setText(dto.getId());
            txtDate.setText(dto.getDate());
            txtTime.setText(dto.getTime());
            txtServiceType.setText(dto.getServiceType());
            txtVehicleID.setText(dto.getVehicleID());
        }

        private void loadData() throws SQLException {
            AppoinmentBOImpl appoinmentModel = new AppoinmentBOImpl();
            ArrayList<appoinmentDto> all = appoinmentModel.getAll();
            tblAppoinment.getItems().setAll(FXCollections.observableArrayList(all));


        }

        boolean checkAlradyExit() throws SQLException {
            String date = String.valueOf(this.datePicker.getValue());
            String time = (String) this.cmbTime.getValue();
            return appoinmentModel.getExit(date, time);
        }

    }


