package com.dicapisar.inventory_api_core.utils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static com.dicapisar.inventory_api_core.commons.APIInventoryCoreConstants.ROL;
import static com.dicapisar.inventory_api_core.commons.APIInventoryCoreConstants.ID;

public class SessionUtil {

    public static boolean isSessionExist(HttpSession session) {
        return session.getAttribute(ROL) != null;
    }

    public static boolean isSessionWithPermissions(HttpSession session, ArrayList<String> permissionList) {
        for (String permission : permissionList) {
            if (session.getAttribute(ROL).equals(permission)) {
                return true;
            }
        }
        return false;
    }

    public static Long getUserId(HttpSession session) {
        return (Long) session.getAttribute(ID);
    }
}
