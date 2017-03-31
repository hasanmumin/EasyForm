package com.easyform.library.editors;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;

import com.easyform.library.R;
import com.easyform.library.editors.model.PairModel;

import java.util.LinkedList;
import java.util.List;


public class MultiSelectElement extends android.support.v7.widget.AppCompatTextView implements View.OnClickListener, Editor<String> {


    private AlertDialog alertDialog;
    private PairModel[] items;
    private boolean[] results;


    public MultiSelectElement(Context context) {
        super(context);
        setTextSize(16);
        setTextColor(Color.BLACK);
        setBackgroundResource(R.drawable.bottom_line);
        setPadding(10, 5, 0, 0);
        setOnClickListener(this);
    }

    public MultiSelectElement(Context context, PairModel[] items) {
        this(context);
        setItems(items);
    }


    public AlertDialog getSelectionDialog() {
        if (alertDialog == null)
            createAlertDialog();
        return alertDialog;
    }

    public String[] getItems() {
        String[] newItems = new String[items.length];
        for (int i = 0; i < newItems.length; i++) {
            newItems[i] = items[i].getPairValue();
        }
        return newItems;
    }

    public void setItems(PairModel[] items) {

        if (items == null) {
            items = new PairModel[0];
        }
        this.items = items;
        this.results = new boolean[items.length];

        alertDialog = null;
    }

    private void createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Select");


        builder.setMultiChoiceItems(getItems(), results, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // Save to pereferences.
                setText(mergeSelections());
            }
        }).setNegativeButton("Cancel", null);
        alertDialog = builder.create();
    }


    @Override
    public String getValue() {
        List<String> selections = new LinkedList<>();
        for (int i = 0; i < items.length; i++) {
            if (results[i])
                selections.add(items[i].getPairKey());
        }
        String[] arr = new String[selections.size()];
        selections.toArray(arr);
        return TextUtils.join(",", arr);

    }


    @Override
    public void setValue(String value) {
        if (!TextUtils.isEmpty(value)) {

            String[] values = TextUtils.split(value, ",");
            for (int i = 0; i < items.length; i++) {
                results[i] = false;
                for (String item : values) {
                    if (items[i].getPairKey().equals(item)) {
                        results[i] = true;
                        break;
                    }
                }
            }
            setText(mergeSelections());

        }

    }

    private String mergeSelections() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            if (results[i]) {
                builder.append(items[i]).append(",");
            }

        }

        String result = builder.toString();
        if (TextUtils.isEmpty(result)) {
            return null;
        }
        return result.substring(0, result.length() - 1);
    }

    @Override
    public void onClick(View view) {
        getSelectionDialog().show();
    }

}
