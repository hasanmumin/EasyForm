package com.easyform.library.editors.validators;

import android.text.TextWatcher;

import com.easyform.library.Element;

public class DoubleMinMaxValidator extends Validator implements TextWatcher {

    private double min, max;

    public DoubleMinMaxValidator(Element element, double min, double max, String errorMsg) {
        super(element, errorMsg);
        this.min = min;
        this.max = max;
    }

    protected boolean isValid(String text) {

        try {
            double i = Double.parseDouble(text);
            return ((i >= min) && (i <= max));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}