package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SalaryBO;
import lk.ijse.dto.Employeedto;
import lk.ijse.dto.SalaryDto;
import lk.ijse.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.bo.custom.impl.SalaryBOImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SalaryFormController {

    public TableView tblSalary;
    @FXML
    private Label lblYear;
    @FXML
    private Label lblMonth;
    @FXML
    private ComboBox comEmpId;
    @FXML
    private Label lblSalaryId;
    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colMonth;

    @FXML
    private TableColumn<?, ?> colSalaryId;

    @FXML
    private TableColumn<?, ?> colYear;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtMonth;

    @FXML
    private TextField txtSalaryId;

    @FXML
    private TextField txtYear;

    SalaryBOImpl salaryModel = new SalaryBOImpl();
    EmployeeBOImpl employeeModel = new EmployeeBOImpl();
    SalaryBO salaryBO = (SalaryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SALARY);

    public void initialize() throws SQLException {
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));

        loadData();
        generateNextSalaryID();
        loadAllEmpIDs();
        setMonthandYear();
    }

    private void setMonthandYear() {
        LocalDate currentDate = LocalDate.now();
        lblMonth.setText(currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        lblYear.setText(String.valueOf(Year.now().getValue()));
    }

    private void loadAllEmpIDs() throws SQLException {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<Employeedto> vehicleDtoList = employeeModel.getAll();

        for (Employeedto dto : vehicleDtoList){
            observableList.add(dto.getEmployeeId());
        }
        comEmpId.setItems(observableList);

    }

    private void generateNextSalaryID() {
        try {
            String appointmentID = salaryModel.generateNextSalaryID();
            lblSalaryId.setText(appointmentID);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void loadData() throws SQLException {
        SalaryBOImpl salaryModel = new SalaryBOImpl();
        ArrayList<SalaryDto> all = salaryModel.getAll();
        tblSalary.getItems().setAll(FXCollections.observableArrayList(all));
    }

     public void ClearOnAction(ActionEvent event) {
        clear();
    }


    private void clear() {
        txtAmount.clear();
    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id = txtSalaryId.getText();

        var model = new SalaryBOImpl();

        try {
            boolean isDelete = model.deleteSalary(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary Delete!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    @FXML
    void SaveOnAction(ActionEvent event) {
        String salaryId = lblSalaryId.getText();
        String EmpId = (String) comEmpId.getValue();
        String month = lblMonth.getText();
        String year = lblYear.getText();
        String Amount = txtAmount.getText();



        try {
            SalaryBOImpl salaryModel = new SalaryBOImpl();
            boolean b = salaryBO.saveSalary(new SalaryDto(salaryId,EmpId,month,year,Amount ));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary saved!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) {
        String salaryId = txtSalaryId.getText();
        String EmpId = txtEmpId.getText();
        String month = txtMonth.getText();
        String year = txtYear.getText();
        String Amount = txtAmount.getText();



        var dto = new SalaryDto(salaryId,EmpId,month,year,Amount);

        var model = new SalaryBOImpl();

        try {
            boolean isSaved = model.updateSalary(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary Update!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void searchOnAction(ActionEvent actionEvent) {

        String code = txtSalaryId.getText();

        try {
            SalaryDto dto = salaryBO.searchAppointment(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Salary not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(SalaryDto dto) {
        txtSalaryId.setText(dto.getSalaryId());
        txtEmpId.setText(dto.getEmployeeID());
        txtMonth.setText(dto.getMonth());
        txtYear.setText(dto.getYear());
        txtAmount.setText(dto.getAmount());
    }

}
