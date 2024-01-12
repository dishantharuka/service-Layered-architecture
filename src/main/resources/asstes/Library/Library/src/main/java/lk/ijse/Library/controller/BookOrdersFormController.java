package lk.ijse.Library.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class BookOrdersFormController {

    @FXML
    private TableColumn<?, ?> colBook_id;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colMem_id;

    @FXML
    private TableColumn<?, ?> colReternDate;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtBook_id;

    @FXML
    private Label txtDate;

    @FXML
    private TextField txtReternDate;

    @FXML
    private TextField txtmem_id;

    @FXML
    void ADDOnAction(ActionEvent event) {

    }

    @FXML
    void BookOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"book_form.fxml","book");

    }

    @FXML
    void BookOrdersOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"book_orders_form.fxml","book_order");

    }

    @FXML
    void BookSeachsOnAction(ActionEvent event) {

    }

    @FXML
    void CLEAROnAction(ActionEvent event) {

    }

    @FXML
    void DELETEOnAction(ActionEvent event) {

    }

    @FXML
    void DonationOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"donation_form.fxml","donation");

    }

    @FXML
    void EmployeOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"employe_form.fxml","employe");

    }

    @FXML
    void IncomeOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"income.fxml","income");

    }

    @FXML
    void LoginHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void LogoutOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"login_form.fxml","login");

    }

    @FXML
    void MemberOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"member_form.fxml","member");

    }

    @FXML
    void ReportOnAction(ActionEvent event) {

    }

    @FXML
    void SalaryOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"salary_form.fxml","salary");

    }

    @FXML
    void SupplierOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"supplier_form.fxml","supplier");

    }

    @FXML
    void UPDATEOnAction(ActionEvent event) {

    }

}
