package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        APPOINTMENT, CUSTOMER, EMPLOYEE, ITEM, ORDERS, ORDERDETAIL, PAYMENT, PLACEORDER, RECORD, SALARY, SUPPLIER, SUPPLIERINVENTORY, USER, VEHICLE

    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {

            case APPOINTMENT:
                return new AppoinmentBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDERS:
                return new OrderBOImpl();
            case ORDERDETAIL:
                return new OrderDetailBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PLACEORDER:
                return (SuperBO) new PlaceOrderBOImpl();
            case RECORD:
                return new RecordBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case SUPPLIERINVENTORY:
                return new SupplierInventoryBOImpl();
            case USER:
                return new UserBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            default:
                return null;
        }
    }
}
