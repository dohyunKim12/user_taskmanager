package com.bos.usertaskmanager.constant;

public class UtmConstant {
    public static final String SYSTEM_API_KEY = System.getProperty("SYSTEM_API_KEY") == null ? (System.getenv("SYSTEM_API_KEY") == null ? "bos-utm" : System.getenv("SYSTEM_API_KEY")) : System.getProperty("SYSTEM_API_KEY");
}
