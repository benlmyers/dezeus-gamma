package com.benmyers.dezeus.util;

public class Debug {

    public static final Boolean USE_LOG = true;

    public static void log(Object message) {
        if (!USE_LOG)
            return;
        System.out.println(message.toString());
    }
}
