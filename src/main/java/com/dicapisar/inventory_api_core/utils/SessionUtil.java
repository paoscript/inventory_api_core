package com.dicapisar.inventory_api_core.utils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class SessionUtil {

    public static boolean isSessionExist(HttpSession session) {
        return session.getAttribute("Rol") != null;
    }

    public static boolean isSessionWithPermissions(HttpSession session, ArrayList<String> permissionList) {
        for (String permission : permissionList) {
            if (session.getAttribute("Rol").equals(permission)) {
                return true;
            }
        }
        return false;
    }
}
