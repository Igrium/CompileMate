package com.igrium.compilesource.util;

public class Platform {
    public static boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().contains("windows");
}
