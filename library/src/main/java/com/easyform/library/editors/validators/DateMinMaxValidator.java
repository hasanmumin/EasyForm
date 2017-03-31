package com.easyform.library.editors.validators;

import android.text.TextWatcher;

import com.easyform.library.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateMinMaxValidator extends Validator implements TextWatcher {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private Date min;
    private Date max;

    public DateMinMaxValidator(Element element, Date min, Date max, String errorMsg) {
        super(element, errorMsg);
        this.min = clearTime(min);
        this.max = clearTime(max);
        this.max.setDate(this.max.getDate() + 1);

    }

    private Date clearTime(Date org) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(org);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    protected boolean isValid(String text) {
        try {
            Date date = DATE_FORMAT.parse(text);
            return ((date.getTime() >= min.getTime()) && (date.getTime() <= max.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
