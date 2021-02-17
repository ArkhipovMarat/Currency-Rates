package com.example1.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DataUtil {
    private DataUtil() {
    }

    private final static String DATEFORMATE = "yyyy-MM-dd";

    public static String today() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DATEFORMATE));
    }

    public static String yesterday() {
        return LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern(DATEFORMATE));
    }
}
