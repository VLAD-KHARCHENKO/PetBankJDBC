package com.project.petbank.controller;

import com.project.petbank.controller.command.*;
import com.project.petbank.service.ServiceFactory;
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
        getCommandMap.put("admin", new AdminCommand());
        getCommandMap.put("user", new UserCommand());
        getCommandMap.put("payments", new PaymentCommand(ServiceFactory.getCardService(), ServiceFactory.getPaymentService()));
        getCommandMap.put("statements", new StatementsCommand(ServiceFactory.getPaymentService(), ServiceFactory.getCardService()));
        getCommandMap.put("cards", new CardsCommand(ServiceFactory.getCardService()));
        getCommandMap.put("accounts", new AccountCommand(ServiceFactory.getAccountService()));
        getCommandMap.put("profile", new ProfileCommand());
        getCommandMap.put("pending-cards", new PendingCardsCommand(ServiceFactory.getCardService()));
        getCommandMap.put("language", new LanguageCommand());
        getCommandMap.put("logout", new LogoutCommand());
        getCommandMap.put("users", new UsersCommand(ServiceFactory.getUserService()));
        getCommandMap.put("login", new LoginCommand(ServiceFactory.getUserService()));
        getCommandMap.put("register", new RegisterCommand(ServiceFactory.getUserService()));

        postCommandMap.put("login", new LoginCommand(ServiceFactory.getUserService()));
        postCommandMap.put("register", new RegisterCommand(ServiceFactory.getUserService()));
        postCommandMap.put("payments", new PaymentCommand(ServiceFactory.getCardService(), ServiceFactory.getPaymentService()));
        postCommandMap.put("statements", new StatementsCommand(ServiceFactory.getPaymentService(), ServiceFactory.getCardService()));
        postCommandMap.put("cards", new CardsCommand(ServiceFactory.getCardService()));
        postCommandMap.put("users", new UsersCommand(ServiceFactory.getUserService()));
        postCommandMap.put("accounts", new AccountCommand(ServiceFactory.getAccountService()));
        postCommandMap.put("profile", new ProfileCommand());
        postCommandMap.put("pending-cards", new PendingCardsCommand(ServiceFactory.getCardService()));
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
