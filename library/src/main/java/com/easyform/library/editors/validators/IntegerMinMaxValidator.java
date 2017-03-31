package com.easyform.library.editors.validators;

import android.text.TextWatcher;

import com.easyform.library.Element;

public class IntegerMinMaxValidator extends Validator implements TextWatcher {

    private int min, max;

    public IntegerMinMaxValidator(Element element, int min, int max, String errorMsg) {
        super(element, errorMsg);
        this.min = min;
        this.max = max;
    }


    protected boolean isValid(String text) {
        try {
            int i = Integer.parseInt(text);
            return ((i >= min) && (i <= max));
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
