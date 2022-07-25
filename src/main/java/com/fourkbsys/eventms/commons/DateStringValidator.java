package com.fourkbsys.eventms.commons;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class DateStringValidator {
    public static boolean isValid(String dateString){
        try {
            LocalDate.parse(dateString, DateTimeFormatter.BASIC_ISO_DATE);
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }
}
