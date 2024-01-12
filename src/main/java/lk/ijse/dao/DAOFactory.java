package lk.ijse.dao;

import lk.ijse.dao.Custom.Impl.*;

public class DAOFactory {
        private static DAOFactory daoFactory;

        private DAOFactory(){

        }
        public static  DAOFactory getDaoFactory(){
            return (daoFactory==null)?daoFactory =new DAOFactory():daoFactory;
        }
        public enum DAOTypes {
            APPOINTMENT, CUSTOMER, EMPLOYEE, ITEM, ORDERS, ORDERDETAIL, PAYMENT, RECORD, SALARY, SUPPLIER, SUPPLIERINVENTORY, USER, VEHICLE
        }
        public SuperDAO  getDAO(DAOTypes types){
            switch (types){
                case APPOINTMENT:
                    return  new AppointmentDAOImpl();
                case CUSTOMER:
                    return  new CustomerDAOImpl();
                case EMPLOYEE:
                    return  new EmployeeDAOImpl();
                case ITEM:
                    return  new ItemDAOImpl();
                case ORDERS:
                    return  new OrderDAOImpl();
                case ORDERDETAIL:
                    return  new OrderDetailDAOImpl();
                case PAYMENT:
                    return  new PaymentDAOImpl();
                case RECORD:
                    return  new RecordDAOImpl();
                case SALARY:
                    return  new SalaryDAOImpl();
                case SUPPLIER:
                    return  new SupplierDAOImpl();
                case SUPPLIERINVENTORY:
                    return  new SupplierInventoryDAOImpl();
                case USER:
                    return  new UserDAOImpl();
                case VEHICLE:
                    return  new VehicleDAOImpl();
                default:
                    return null;
            }
        }
}
