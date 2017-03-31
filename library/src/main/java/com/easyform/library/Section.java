package com.easyform.library;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Section extends LinearLayout {

    private TextView titleView;

    public Section(Context context, String title) {
        super(context);
        initializeProperties();
        addView(createTitleView(title));
    }

    private void initializeProperties() {
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundResource(R.drawable.section_bg);
    }

    private TextView createTitleView(String title) {
        titleView = new TextView(getContext());
        titleView.setText(title);
        titleView.setTextColor(Color.BLUE);
        titleView.setTextSize(18);
        titleView.setPadding(5, 5, 0, 0);

        return titleView;
    }

}
