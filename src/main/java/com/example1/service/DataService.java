package com.example1.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataService {
    private final static String DATEFORMATE = "yyyy-MM-dd";

    public static String today () {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DATEFORMATE));
    }

    public static String yesterday () {
        return LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern(DATEFORMATE));
    }
}
