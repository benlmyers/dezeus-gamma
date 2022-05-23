package com.benmyers.dezeus.util;

import java.util.Set;

public class StatementUtil {

    public static <T> String setToString(Set<T> set) {
        if (set.isEmpty())
            return "";
        String str = "";
        for (T item : set) {
            str += item.toString() + ". ";
        }
        str = str.substring(0, str.length() - 2);
        return str;
    }
}
