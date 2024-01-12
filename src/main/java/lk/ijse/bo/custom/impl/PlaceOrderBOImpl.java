package lk.ijse.bo.custom.impl;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.OrderBO;
import lk.ijse.bo.custom.PlaceOrderBO;
import lk.ijse.dao.Custom.CustomerDAO;
import lk.ijse.dao.Custom.ItemDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.dto.itemDto;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final ItemBOImpl itemModel = new ItemBOImpl();
    private final OrderDetailBOImpl orderDetailModel = new OrderDetailBOImpl();

    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERS);

    public boolean placeOrder(PlaceOrderDto pDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = orderBO.saveOrder(pDto.getOrderId(), pDto.getCusId(), pDto.getDate());
            if (isOrderSaved) {
                System.out.println("orderSaved");
                boolean isUpdated = itemModel.updateItem(pDto.getTmList());
                if(isUpdated) {
                    System.out.println("Items Saved");

                    boolean isOrderDetailSaved = orderDetailModel.saveOrderDetail(pDto.getOrderId(), pDto.getTmList());
                    if(isOrderDetailSaved) {

                        System.out.println("orderDetailSaved");
                        connection.commit();
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }

    @Override
    public itemDto searchItem(String code) throws SQLException {
        return itemDAO.search(code);
    }

    @Override
    public CustomerDto searchCustomer(String id) throws SQLException {
        return customerDAO.search(id);
    }
}
