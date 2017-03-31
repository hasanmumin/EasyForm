package com.easyform.library.editors;

import android.content.Context;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.easyform.library.editors.model.PairModel;
import com.easyform.library.util.ObjectUtil;

import java.util.LinkedList;
import java.util.List;

public class AutoCompleteElement extends AppCompatAutoCompleteTextView implements Editor<Object>, AdapterView.OnItemClickListener {

    private List<AdapterView.OnItemClickListener> listeners = new LinkedList<>();
    private List<? extends PairModel> data;
    private String value;

    public AutoCompleteElement(Context context, List<? extends PairModel> data) {
        super(context);
        this.data = data;
        this.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, data));
        super.setOnItemClickListener(this);
        this.value = null;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if (ObjectUtil.isNotNull(value)) {
            int position = data.indexOf(value);
            if (position > -1) {
                PairModel item = data.get(position);
                this.value = item.getPairValue();
                setListSelection(position);
                setText(item.getPairKey());
            }
        } else {
            this.value = null;
            setText("");
        }
    }

    @Override
    public void setOnItemClickListener(AdapterView.OnItemClickListener l) {
        listeners.add(l);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
        PairModel selected = (PairModel) parent.getItemAtPosition(p);
        int position = data.indexOf(selected);
        if (position > -1) {
            setValue(data.get(position));
        }
        for (AdapterView.OnItemClickListener listener : listeners) {
            listener.onItemClick(parent, view, p, id);
        }


    }

    public List<?> getData() {
        return data;
    }
}
