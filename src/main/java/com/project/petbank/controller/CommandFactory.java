package com.project.petbank.controller;

import com.project.petbank.controller.command.Command;
import com.project.petbank.controller.command.HomeCommand;
import com.project.petbank.controller.command.NotFoundCommand;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Logger LOG = Logger.getLogger(CommandFactory.class);
    private static Map<String, Command> getCommandMap = new HashMap<>();
    private static Map<String, Command> postCommandMap = new HashMap<>();
    private static Command defaultCommand = new NotFoundCommand();

    static {
        getCommandMap.put("index", new HomeCommand());
//        getCommandMap.put("under-construction", new UnderConstructionCommand());
//        getCommandMap.put("ui-elements", new ElementCommand());
//        getCommandMap.put("admin", new AdminCommand());
//        getCommandMap.put("chart", new ChartCommand());
//        getCommandMap.put("tab-panel", new TabPanelCommand());
//        getCommandMap.put("table", new TableCommand());
//        getCommandMap.put("form", new FormCommand());
//        getCommandMap.put("empty", new EmptyCommand());
//        getCommandMap.put("about", new AboutUsCommand());
//        getCommandMap.put("blog", new BlogCommand());
//        getCommandMap.put("contact", new ContactCommand());
//        getCommandMap.put("blog-single", new BlogSingleCommand());
//        getCommandMap.put("reservation", new ReservationCommand());
//        getCommandMap.put("specialties", new SpecialtiesCommand());
//        getCommandMap.put("error", new ErrorCommand());
//        getCommandMap.put("language", new LanguageCommand());
//        getCommandMap.put("logout", new LogoutCommand());
//        getCommandMap.put("403-error", new ForbiddenCommand());
//        getCommandMap.put("404-error", defaultCommand);
//        getCommandMap.put("order-list", new OrderListCommand(
//                ServiceFactory.getOrderService()
//        ));
//        getCommandMap.put("chef", new ChefCommand(
//                ServiceFactory.getOrderService()
//        ));
//        getCommandMap.put("menu", new MenuCommand(
//                ServiceFactory.getDishService(),
//                ServiceFactory.getOrderService()
//        ));
//        getCommandMap.put("edit-menu", new EditMenuCommand(
//                ServiceFactory.getDishService()
//        ));
//        getCommandMap.put("login", new LoginCommand(
//                ServiceFactory.getUserService()
//        ));
//        getCommandMap.put("register", new RegisterCommand(
//                ServiceFactory.getUserService()
//        ));
//        getCommandMap.put("dish", new DishCommand(
//                ServiceFactory.getDishService()
//        ));
//        getCommandMap.put("dish-delete", new DishDeleteCommand(
//                ServiceFactory.getDishService()
//        ));
//        getCommandMap.put("order", new OrderCommand(
//                ServiceFactory.getOrderService(),
//                ServiceFactory.getDishService()
//        ));
//        getCommandMap.put("order-dish-delete", new DishOrderDeleteCommand(
//                ServiceFactory.getOrderService()
//        ));
//        getCommandMap.put("add-order-dish", new AddOrderDishCommand(
//                ServiceFactory.getOrderService()
//        ));
//        getCommandMap.put("update-order-dish-quantity", new UpdateOrderDishCommand(
//                ServiceFactory.getOrderService()
//        ));
//        getCommandMap.put("my-orders", new MyOrderCommand(
//                ServiceFactory.getOrderService()
//        ));
//        getCommandMap.put("invoice", new InvoiceCommand(
//                ServiceFactory.getInvoiceService()
//        ));
//
//
//        postCommandMap.put("invoice", new InvoiceCommand(
//                ServiceFactory.getInvoiceService()
//        ));
//        postCommandMap.put("login", new LoginCommand(
//                ServiceFactory.getUserService()
//        ));
//        postCommandMap.put("register", new RegisterCommand(
//                ServiceFactory.getUserService()
//        ));
//        postCommandMap.put("menu", new MenuCommand(
//                ServiceFactory.getDishService(),
//                ServiceFactory.getOrderService()
//        ));
//        postCommandMap.put("edit-menu", new EditMenuCommand(
//                ServiceFactory.getDishService()
//        ));
//        postCommandMap.put("dish", new DishCommand(
//                ServiceFactory.getDishService()
//        ));
//        postCommandMap.put("status", new StatusCommand(
//                ServiceFactory.getOrderService(),
//                ServiceFactory.getInvoiceService()
//        ));
//        postCommandMap.put("order", new OrderCommand(
//                ServiceFactory.getOrderService(),
//                ServiceFactory.getDishService()
//        ));

    }

    private CommandFactory() {
    }

    public static Command getCommand(String path, String type) {
        LOG.info("Extract command " + type + " for path=" + path);
        return "GET".equals(type)
                ? getRequestCommand(path)
                : postRequestCommand(path);
    }

    private static Command getRequestCommand(String path) {
        return getCommandMap.getOrDefault(path, defaultCommand);
    }

    private static Command postRequestCommand(String path) {
        return postCommandMap.getOrDefault(path, defaultCommand);
    }
}
