package com.project.petbank.config;

import com.project.petbank.model.User;
import com.project.petbank.model.enums.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class SecurityConfig {

    private static Map<Role, List<String>> securityPages = new HashMap<>();

    static {
        securityPages.put(Role.ADMIN, Arrays.asList("/admin", "/user", "/payments","/statements","/cards","/users", "/accounts","/profile"));
        securityPages.put(Role.CUSTOMER, Arrays.asList("/user", "/payments","/statements","/cards", "/accounts","/profile"));

    }

    public static boolean isSecurePage(String page) {
        return securityPages.values().stream()
                .anyMatch(list -> list.stream()
                        .anyMatch(pageValue -> pageValue.equals(page)));
    }

    public static boolean hasPermission(String page, Role role) {
        return securityPages.getOrDefault(role, Collections.EMPTY_LIST)
                .stream()
                .anyMatch(securePage -> securePage.equals(page));
    }

    public static boolean hasPermission(HttpServletRequest request, Role role) {
        User currentUser = getCurrentUser(request);
        return currentUser != null && currentUser.getRole().equals(role);
    }

    public static User getCurrentUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

}
