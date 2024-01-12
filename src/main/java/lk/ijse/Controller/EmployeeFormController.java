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
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dto.Employeedto;
import lk.ijse.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class EmployeeFormController {

    public AnchorPane Pane;
    public TableView tblEmployee;
    @FXML
    private ComboBox cmbuserId;
    @FXML
    private ComboBox cmbJobRole;
    @FXML
    private Label lblEmployeeId;

    @FXML
    private TableColumn<?, ?> colUserID;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableColumn<?, ?> colJobRole;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtJobRole;

    @FXML
    private TextField txtSalary;

    EmployeeBOImpl employeeModel = new EmployeeBOImpl();
    UserBOImpl userModel = new UserBOImpl();

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);


    public void initialize(){

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Employeedto employeedto = (Employeedto) newValue;
            if (newValue != null) {
                lblEmployeeId.setText(employeedto.getEmployeeId());
                txtEmpName.setText(employeedto.getName());
                txtContactNo.setText(employeedto.getContactInformation());
                cmbuserId.setValue(employeedto.getUserId());
                cmbJobRole.setValue(employeedto.getRole());
                txtSalary.setText(employeedto.getSalary());
            }
        });


        colEmpId.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactInformation"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colJobRole.setCellValueFactory(new PropertyValueFactory<>("Role"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("UserId"));


        loadData();
        generateEmpID();
        setEmpRoles();
        loadUserIDs();
    }

    private void loadUserIDs() {
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

    private void setEmpRoles() {
        ObservableList<String> ob = FXCollections.observableArrayList();
        String[] roles = {
               "Manager",
                "Mechanic"
        };


        for (String job : roles){
            ob.add(job);
        }

        cmbJobRole.setItems(ob);
    }

    private void generateEmpID() {
        try {
            String appointmentID = employeeModel.generateNextEmpID();
            lblEmployeeId.setText(appointmentID);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void loadData() {
        try {
            ArrayList<Employeedto> all= employeeBO.getAll();
            tblEmployee.getItems().setAll(FXCollections.observableArrayList(all));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void SalaryOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/Salary_form.fxml"));
        this.Pane.getChildren().clear();
        this.Pane.getChildren().add(parent);

    }
    @FXML
    void ClearOnAction(ActionEvent event) {
        clear();
    }


    private void clear() {

        txtEmpName.clear();
        txtContactNo.clear();
        txtSalary.clear();

    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id = lblEmployeeId.getText();

        var model = new EmployeeBOImpl();

        try {
            boolean isDelete = model.deleteEmployee(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Delete!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void updateOnAction(ActionEvent event) {
        String id = lblEmployeeId.getText();
        String name = txtEmpName.getText();
        String tel = txtContactNo.getText();
        String role = (String) cmbJobRole.getValue();
        String salary = txtSalary.getText();
        String userId = (String) cmbuserId.getValue();


        Employeedto dto = new Employeedto(id, name, tel, role, salary, userId);

        var model = new EmployeeBOImpl();

        try {
            boolean isSaved = model.updateEmployee(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Update!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void SaveOnAction(ActionEvent event) {

        String id = lblEmployeeId.getText();
        String name = txtEmpName.getText();
        String contact = txtContactNo.getText();
        String role = (String) cmbJobRole.getValue();
        String salary = txtSalary.getText();
        String userId = (String) cmbuserId.getValue();


        try {
            EmployeeBOImpl employeeModel = new EmployeeBOImpl();
            boolean b = employeeModel.saveEmployee(new Employeedto(id, name, contact, role, salary, userId));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {

        String code = txtEmpId.getText();

        try {
            Employeedto dto = employeeBO.searchEmployee(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(Employeedto dto) {
        txtEmpId.setText(dto.getEmployeeId());
        txtEmpName.setText(dto.getName());
        txtContactNo.setText(dto.getContactInformation());
        txtJobRole.setText(dto.getRole());
        txtSalary.setText(dto.getSalary());
        txtUserId.setText(dto.getUserId());
    }

}
