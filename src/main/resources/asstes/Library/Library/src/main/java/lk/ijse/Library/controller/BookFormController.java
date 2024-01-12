package lk.ijse.Library.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Library.dto.BookDto;
import lk.ijse.Library.model.BookModel;

import java.io.IOException;
import java.sql.SQLException;

public class BookFormController {

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colBook_id;

    @FXML
    private TableColumn<?, ?> colCatagory;

    @FXML
    private TableColumn<?, ?> colLanguege;

    @FXML
    private TableColumn<?, ?> cold_id;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtBook_id;

    @FXML
    private TextField txtCatagory;

    @FXML
    private TextField txtLanguege;

    @FXML
    private TextField txtd_id;

    @FXML
    private TextField txts_id1;



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
    void ADDOnAction(ActionEvent event) {

        String id = txtBook_id.getText();
        String name = txtBookName.getText();
        String author = txtAuthor.getText();
        String catagory = txtCatagory.getText();
        String languege = txtLanguege.getText();
        String d_id = txtd_id.getText();
        String s_id = txts_id1.getText();

        var dto = new BookDto(id, name, author, catagory,languege,d_id,s_id);

        var model = new BookModel();

        try {
            boolean isSaved = model.saveBook(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void CLEAROnAction(ActionEvent event) {clearFields();}

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
    void LoginHistoryOnAction(ActionEvent event) throws IOException {
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

        String id = txtBook_id.getText();
        String name = txtBookName.getText();
        String author = txtAuthor.getText();
        String catagory = txtCatagory.getText();
        String languege = txtLanguege.getText();
        String d_id = txtd_id.getText();
        String s_id = txts_id1.getText();

        var dto = new BookDto(id, name, author, catagory,languege,d_id,s_id);

        var model = new BookModel();

        try {
            boolean isSaved = model.(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    void clearFields() {
        txtBook_id.setText("");
        txtBookName.setText("");
        txtAuthor.setText("");
        txtCatagory.setText("");
        txtLanguege.setText("");
        txtd_id.setText("");
        txts_id1.setText("");
    }

}
