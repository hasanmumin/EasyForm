package com.easyform.library.editors;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.easyform.library.util.ObjectUtil;

import java.util.LinkedList;
import java.util.List;

public class SpinnerElement extends android.support.v7.widget.AppCompatSpinner implements Editor<Object>, AdapterView.OnItemSelectedListener {

    List<OnItemSelectedListener> listeners = new LinkedList<>();
    private List<?> data;


    public SpinnerElement(Context context, List<?> data) {
        super(context);
        this.data = data;
        this.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, data));
        super.setOnItemSelectedListener(this);
    }

    @Override
    public Object getValue() {
        return getSelectedItem();
    }

    @Override
    public void setValue(Object value) {
        if (ObjectUtil.isNotNull(value)) {
            int position = data.indexOf(value);
            if (position > -1) {
                setSelection(position, true);
            } else {
                if (data.size() > 0) {
                    setSelection(0);
                }
            }
        }
    }

    @Override
    public CharSequence getText() {
        Object selectedText = getSelectedItem();
        if (ObjectUtil.isNotNull(selectedText)) {
            return selectedText.toString();
        } else {
            return null;
        }
    }


    @Override
    public void setOnItemSelectedListener(OnItemSelectedListener l) {
        if (l != null)
            listeners.add(l);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        for (OnItemSelectedListener listener : listeners)
            listener.onItemSelected(parent, view, position, id);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        for (OnItemSelectedListener listener : listeners)
            listener.onNothingSelected(parent);
    }
}
