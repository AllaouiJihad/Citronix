package com.example.citronix.utils;

import com.example.citronix.model.enums.Season;

import java.time.LocalDate;
import java.time.Month;

public class SeasonUtils {
    public static Season getSeasonFromDate(LocalDate date) {
        Month month = date.getMonth();

        return switch (month) {
            case DECEMBER, JANUARY, FEBRUARY -> Season.WINTER;
            case MARCH, APRIL, MAY -> Season.SPRING;
            case JUNE, JULY, AUGUST -> Season.SUMMER;
            case SEPTEMBER, OCTOBER, NOVEMBER -> Season.AUTUMN;
            default -> throw new IllegalArgumentException("Invalid month in date: " + date);
        };
    }
}
