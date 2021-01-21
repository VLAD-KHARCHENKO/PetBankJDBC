package com.project.petbank.service;


import com.project.petbank.repository.DaoFactory;

public class ServiceFactory {
    private ServiceFactory() {
    }

   //private static OrderService orderService = OrderServiceFactory.getOrderService();
//
    private static UserService userService = new UserService(
            DaoFactory.getUserDao()
    );
//    private static DishService dishService = new DishService(
//            DaoFactory.getDishDao()
//    );

//    private static InvoiceService invoiceService = new InvoiceService(
//            DaoFactory.getInvoiceDao(),
//            OrderServiceFactory.getOrderService()
//    );

   // public static OrderService getOrderService() {
      //  return orderService;
    //}

    public static UserService getUserService() {
        return userService;
    }

//    public static DishService getDishService() {
//        return dishService;
//    }
//
//    public static InvoiceService getInvoiceService() {
//        return invoiceService;
//    }

}
