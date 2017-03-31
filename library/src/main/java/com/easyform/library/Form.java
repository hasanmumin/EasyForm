package com.easyform.library;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.easyform.library.editors.Editor;
import com.easyform.library.editors.validators.Validator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Form extends LinearLayout {

    private List<Validator> validators = new LinkedList<>();


    public Form(Context context) {
        super(context);
        initializeProperties(context);
    }

    private void initializeProperties(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        setShowDividers(SHOW_DIVIDER_MIDDLE);
        setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.form_divider));
        setDividerPadding(2);
    }


    public HashMap<String, Object> collectValues() {

        HashMap<String, Object> map = new HashMap<>();

        int secCount = getChildCount();
        for (int secIndex = 0; secIndex < secCount; secIndex++) {

            View viewSection = getChildAt(secIndex);
            if (viewSection instanceof Section) {

                Section section = (Section) viewSection;

                int elCount = section.getChildCount();
                for (int elIndex = 0; elIndex < elCount; elIndex++) {
                    View viewElement = section.getChildAt(elIndex);
                    if (viewElement instanceof Element) {
                        Editor editor = ((Element) viewElement).getEditorView();
                        map.put(((View) editor).getTag().toString(), editor.getValue());
                    }
                }
            }
        }

        return map;
    }


    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        int secCount = getChildCount();
        for (int secIndex = 0; secIndex < secCount; secIndex++) {
            View viewSection = getChildAt(secIndex);
            if (viewSection instanceof Section) {
                Section section = (Section) viewSection;
                int elCount = section.getChildCount();
                for (int elIndex = 0; elIndex < elCount; elIndex++) {
                    View viewElement = section.getChildAt(elIndex);
                    viewElement.setEnabled(enabled);
                    if (viewElement instanceof Element) {
                        View editor = (View) ((Element) viewElement).getEditorView();
                        editor.setEnabled(enabled);
                    } else if (viewElement instanceof ButtonElement) {
                        viewElement.setEnabled(enabled);
                        viewElement.setVisibility(enabled ? VISIBLE : GONE);
                    }
                }
            }
        }
    }

    public void addValidator(Validator validator) {
        validators.add(validator);
    }

    public boolean isValid() {
        boolean result = true;
        for (Validator validator : validators) {
            if (!validator.isValid()) {
                validator.showToolTip();
                result = false;
            }
        }
        return result;
    }

    //TODO only works for EditText for now..!!!
    @SuppressWarnings("unchecked")
    public void resetFields() {
        Form form = this;
        for (int i = 0; i < form.getChildCount(); ++i) {
            View nextChild = form.getChildAt(i);
            if (nextChild instanceof Section) {
                Section section = ((Section) nextChild);
                for (int j = 0; j < section.getChildCount(); ++j) {
                    View nextChild2 = section.getChildAt(i);
                    if (nextChild2 instanceof Element) {

                        Element element = (Element) nextChild2;
                        View v = (View) element.getEditorView();

                        if (v instanceof EditText) {
                            EditText editText = (EditText) v;
                            editText.setText("");
                        }

                        if (v instanceof TextView) {
                            TextView textView = (TextView) v;
                            textView.setText("");
                        }

                        if (v instanceof Switch) {
                            Switch sc = (Switch) v;
                            sc.setText("");
                        }
                    }
                }
            }
        }
    }
}
