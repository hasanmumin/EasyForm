package com.easyform.library.editors.validators;

import android.text.TextWatcher;

import com.easyform.library.Element;

import java.util.List;

public class AutoCompletionValidator extends Validator implements TextWatcher {

    private Object selectedValue;
    private List<?> data;
    private String writtenValue;

    public AutoCompletionValidator(Element element, String errorMsg, List<?> data) {
        super(element, errorMsg);
        this.data = data;
    }

    public void setSelectedValue(Object selectedValue) {
        this.selectedValue = selectedValue;
    }

    @Override
    protected boolean isValid(String text) {
        return data.contains(selectedValue) && selectedValue.toString().equals(writtenValue);
    }

    public void setWrittenValue(String writtenValue) {
        this.writtenValue = writtenValue;
    }
}