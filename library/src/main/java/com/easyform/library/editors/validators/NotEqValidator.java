package com.easyform.library.editors.validators;

import android.text.TextWatcher;

import com.easyform.library.Element;
import com.easyform.library.util.ObjectUtil;

public class NotEqValidator extends Validator implements TextWatcher {

    private String value;

    public NotEqValidator(Element element, String value, String errorMsg) {
        super(element, errorMsg);
        this.value = value;
    }

    @Override
    protected boolean isValid(String text) {
        if (ObjectUtil.isNull(text) && ObjectUtil.isNull(value))
            return false;
        else if (ObjectUtil.isNull(value)) {
            return true;
        } else if (ObjectUtil.isNull(text)) {
            return true;
        }
        return !value.equals(text);
    }
}
