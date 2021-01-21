package com.project.petbank.repository;


import com.project.petbank.config.ConnectionFactory;
import com.project.petbank.config.DataSourceConnectionPool;
import com.project.petbank.repository.impl.UserDaoImpl;

public class DaoFactory {

    private static final ConnectionFactory CONNECTION_FACTORY = DataSourceConnectionPool.getInstance();

    private static final UserDaoImpl USER_DAO = new UserDaoImpl(CONNECTION_FACTORY);
//    private static final DishDaoImpl DISH_DAO = new DishDaoImpl(CONNECTION_FACTORY);
//    private static final OrderDaoImpl ORDER_DAO = new OrderDaoImpl(CONNECTION_FACTORY);
//    private static final OrderDishDaoImpl ORDER_DISH_DAO = new OrderDishDaoImpl(CONNECTION_FACTORY);
//    private static final InvoiceDaoImpl INVOICE_DAO = new InvoiceDaoImpl(CONNECTION_FACTORY);

    public static UserDaoImpl getUserDao() {
        return USER_DAO;
    }

//    public static DishDaoImpl getDishDao() {
//        return DISH_DAO;
//    }
//
//    public static OrderDaoImpl getOrderDao() {
//        return ORDER_DAO;
//    }
//
//    public static OrderDishDaoImpl getOrderDishDao() {
//        return ORDER_DISH_DAO;
//    }
//
//    public static InvoiceDaoImpl getInvoiceDao() {
//        return INVOICE_DAO;
//    }

}
