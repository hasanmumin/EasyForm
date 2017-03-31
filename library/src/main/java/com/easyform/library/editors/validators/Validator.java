package com.easyform.library.editors.validators;

import android.text.Editable;
import android.text.TextWatcher;

import com.easyform.library.Element;
import com.easyform.library.util.ObjectUtil;

public abstract class Validator implements TextWatcher {

    private Element element;
    private String errorMsg;

    public Validator(Element element, String errorMsg) {
        this.element = element;
        this.errorMsg = errorMsg;
    }


    protected abstract boolean isValid(String text);

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
    }

    public void showToolTip() {
        element.showToolTip(errorMsg);
    }

    public void hideToolTip() {
        element.hideToolTip();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!isValid(editable.toString())) {
            showToolTip();
        } else {
            hideToolTip();
        }
    }

    public boolean isValid() {
        CharSequence text = element.getEditorView().getText();
        if (ObjectUtil.isNotNull(text)) {
            return isValid(text.toString());
        } else {
            return isValid(null);
        }
    }
}
