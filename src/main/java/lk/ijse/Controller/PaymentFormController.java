package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.bo.custom.impl.PaymentBOImpl;
import lk.ijse.dto.itemDto;
import lk.ijse.dto.paymentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentFormController {

    public ComboBox cmbItemID;
    public Label lblItemId;
    @FXML
    private AnchorPane Pane;

    @FXML
    private Button clearOnAction;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPaymentMethod;

    @FXML
    public TableView tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtItem;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtPaymentMethod;

   PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
   ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    private void clear() {
        txtPaymentId.clear();
        txtItem.clear();
        txtAmount.clear();
        txtPaymentMethod.clear();
    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id = lblItemId.getText();

        var model = new PaymentBOImpl();

        try {
            boolean isDelete = model.deletePayment(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "payment Delete!").show();
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
        String amount = txtAmount.getText();
        String paymentMethod = txtPaymentMethod.getText();
        String itemID = (String) cmbItemID.getValue();



        try {
            PaymentBOImpl paymentModel = new PaymentBOImpl();
            boolean b = paymentModel.savePayment(new paymentDto(id,amount,paymentMethod,itemID ));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "payment saved!").show();
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
        String amount = txtAmount.getText();
        String paymentMethod = txtPaymentMethod.getText();
        String itemID = (String) cmbItemID.getValue();


        var dto = new paymentDto(id,amount,paymentMethod,itemID);

        var model = new PaymentBOImpl();

        try {
            boolean isSaved = model.updatePayment(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Update!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void clearOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void searchOnAction(ActionEvent event) {
        String code = txtPaymentId.getText();

        try {
            paymentDto dto = paymentBO.searchPayment(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "payment not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(paymentDto dto) {
        txtPaymentId.setText(dto.getId());
        txtAmount.setText(dto.getAmount());
        txtPaymentMethod.setText(dto.getPaymentMethod());
        txtItem.setText(dto.getItemID());
    }
    private void loadData() {

        try {
            ArrayList<paymentDto> all = paymentBO.getAll();
            tblPayment.getItems().setAll(FXCollections.observableArrayList(all));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}

    public void initialize(){

        tblPayment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            paymentDto PaymentDto = (paymentDto) newValue;
            if (newValue != null) {
                lblItemId.setText(PaymentDto.getId());
                cmbItemID.setValue(PaymentDto.getItemID());
                txtAmount.setText(PaymentDto.getAmount());
                txtPaymentMethod.setText(PaymentDto.getPaymentMethod());

            }
        });

        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

        loadData();
        generateNextId();
        loadItemIds();
    }

    private void loadItemIds() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<itemDto> itemDtos = null;
        try {
            itemDtos = itemBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (itemDto dto : itemDtos){
            observableList.add(dto.getId());
        }
        cmbItemID.setItems(observableList);

    }

    private void generateNextId() {
        try {
            String ID = paymentBO.generateNextID();
            lblItemId.setText(ID);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }


}
