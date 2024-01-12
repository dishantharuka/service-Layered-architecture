package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PlaceOrderBO;
import lk.ijse.bo.custom.impl.*;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.dto.itemDto;
import lk.ijse.dto.tm.CartTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaceOrderFormController {

        private final ObservableList<CartTm> obList = FXCollections.observableArrayList();
        @FXML
        private AnchorPane Pane;

        @FXML
        private JFXButton btnPlaceOrder;

        @FXML
        private JFXButton btnSave;

        @FXML
        private JFXComboBox<String> cmbCustomerId;

        @FXML
        private JFXComboBox<String> cmbItemCode;

        @FXML
        private TableColumn<?, ?> colDescription;

        @FXML
        private TableColumn<?, ?> colItemId;

        @FXML
        private TableColumn<?, ?> colQty;

        @FXML
        private TableColumn<?, ?> colAction;
        @FXML
        private TableColumn<?, ?> colTotal;

        @FXML
        private TableColumn<?, ?> colUnitPrice;

        @FXML
        private Label lblDate;

        @FXML
        private Label lblId;

        @FXML
        private Label lblTotal;

        @FXML
        private TableView<CartTm> tblOrderDetails;

        @FXML
        private TextField txtCustomerName;

        @FXML
        private TextField txtDescription;

        @FXML
        private TextField txtQty;

        @FXML
        private TextField txtQtyOnHand;

        @FXML
        private TextField txtUnitPrice;
        PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACEORDER);

        public void initialize() throws SQLException {
                generateNewId();
                loadCustIds();
                loadItemIds();
                setCellValueFactory();
        }

        private void generateNewId(){
                try {
                        lblId.setText(new OrderBOImpl().generateNextOrderId());
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        private void setCellValueFactory() {
                colItemId.setCellValueFactory(new PropertyValueFactory<>("code"));
                colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
                colTotal.setCellValueFactory(new PropertyValueFactory<>("tot"));
                colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
        }

        private void loadItemIds() throws SQLException {
                ItemBOImpl model = new ItemBOImpl();
                ArrayList<itemDto> dtos = model.getAll();
                ObservableList<String> Oids= FXCollections.observableArrayList();
                for (itemDto dto:dtos){
                        Oids.add(dto.getId());
                }
                cmbItemCode.setItems(Oids);
        }

        private void loadCustIds() throws SQLException {
                CustomerBOImpl model = new CustomerBOImpl();
                ArrayList<CustomerDto> dtos = model.getAll();
                ObservableList<String> custIds= FXCollections.observableArrayList();
                for (CustomerDto dto:dtos){
                        custIds.add(dto.getId());
                }
                cmbCustomerId.setItems(custIds);
        }
        @FXML
        void cmbItemOnAction(ActionEvent event) {
                String code = cmbItemCode.getValue();

                txtQty.requestFocus();

                try {
                        itemDto dto = placeOrderBO.searchItem(code);

                        txtDescription.setText(dto.getName());
                        txtUnitPrice.setText(String.valueOf(dto.getUniPrice()));
                        txtQtyOnHand.setText(String.valueOf(dto.getQty()));

                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }

        @FXML
        void cmbCustomerIdOnAction(ActionEvent event) throws SQLException {
                String id = cmbCustomerId.getValue();
                CustomerDto dto = placeOrderBO.searchCustomer(id);

                txtCustomerName.setText(dto.getName());
        }

        @FXML
        void btnAdd_OnAction(ActionEvent event) {
                String code = cmbItemCode.getValue();
                String description = txtDescription.getText();
                int qty = Integer.parseInt(txtQty.getText());
                double unitPrice = Double.parseDouble(txtUnitPrice.getText());
                double total = qty * unitPrice;
                Button btn = new Button("remove");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                        if (type.orElse(no) == yes) {
                                int index = tblOrderDetails.getSelectionModel().getSelectedIndex();
                                obList.remove(index);
                                tblOrderDetails.refresh();

                                calculateNetTotal();
                        }
                });

                for (int i = 0; i < tblOrderDetails.getItems().size(); i++) {
                        if (code.equals(colItemId.getCellData(i))) {
                                qty += (int) colQty.getCellData(i);
                                total = qty * unitPrice;

                                obList.get(i).setQty(qty);
                                obList.get(i).setTot(total);

                                tblOrderDetails.refresh();
                                calculateNetTotal();
                                return;
                        }
                }

                obList.add(new CartTm(
                        code,
                        description,
                        qty,
                        unitPrice,
                        total,
                        btn
                ));

                tblOrderDetails.setItems(obList);
                calculateNetTotal();
                txtQty.clear();
        }

        private void calculateNetTotal() {
                double total = 0;
                for (int i = 0; i < tblOrderDetails.getItems().size(); i++) {
                        total += (double) colTotal.getCellData(i);
                }

                lblTotal.setText(String.valueOf(total));
        }

        @FXML
        void btnPlaceOrder_OnAction(ActionEvent event) {
                String orderId = lblId.getText();
                String cusId = cmbCustomerId.getValue();
                LocalDate date=LocalDate.now();

                List<CartTm> tmList = new ArrayList<>();

                for (CartTm cartTm : obList) {
                        tmList.add(cartTm);
                }

                var placeOrderDto = new PlaceOrderDto(
                        orderId,
                        cusId,
                        date,
                        tmList
                );

                try {

                        boolean isSuccess =placeOrderBO.placeOrder(placeOrderDto);
                        if(isSuccess) {
                                new Alert(Alert.AlertType.CONFIRMATION, "order completed!").show();
                                generateNewId();
                                tblOrderDetails.getItems().clear();
                                txtDescription.clear();
                                txtCustomerName.clear();
                                txtQtyOnHand.clear();
                                txtUnitPrice.clear();
                                txtQty.clear();
                        }else new Alert(Alert.AlertType.ERROR, "order failed!").show();
                } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
        }

        @FXML
        void txtQty_OnAction(ActionEvent event) {

        }

        @FXML
        void backBtnOnAction(ActionEvent event) throws IOException {
                Parent parent = FXMLLoader.load(this.getClass().getResource("/view/Item_form.fxml"));
                this.Pane.getChildren().clear();
                this.Pane.getChildren().add(parent);
        }

    }

