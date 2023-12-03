package com.uadybank.uadybankbackend.Util;

public class CookieUtil {

    public static String getMatricula(String userCookie) {
        String[] parts = userCookie.split("-");
        return parts[1];
    }

    public static Long getId(String userCookie) {
        String[] parts = userCookie.split("-");
        return Long.parseLong(parts[1]);
    }

}
