package com.easyform.library;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyform.library.editors.Editor;

public class Element extends LinearLayout {

    private TextView labelView;
    private View editorView;
    private TextView toolTipView;


    public Element(Context context) {
        super(context);
        initializeProperties();
    }

    public Element(Context context, String label) {
        this(context);
        LinearLayout tooltipContainer = new LinearLayout(context);
        tooltipContainer.setHorizontalGravity(Gravity.FILL_HORIZONTAL);
        tooltipContainer.addView(createLabelView(label));
        tooltipContainer.addView(createTooltipView(context));
        addView(tooltipContainer);
    }

    public Element(Context context, String label, final View editorView) {
        this(context, label);
        this.editorView = editorView;
        addView(editorView);
    }

    private View createTooltipView(Context context) {
        toolTipView = new TextView(context);
        toolTipView.setTextSize(16);
        toolTipView.setBackgroundResource(R.drawable.bg_tooltip);
        toolTipView.setTextColor(Color.WHITE);
        toolTipView.setPadding(10, 5, 20, 5);
        toolTipView.setShadowLayer(1, 1, 1, Color.GRAY);
        toolTipView.setGravity(Gravity.RIGHT);
        toolTipView.setTextAlignment(TEXT_ALIGNMENT_GRAVITY);
        toolTipView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.RIGHT));

        toolTipView.setVisibility(INVISIBLE);
        return toolTipView;
    }

    private void initializeProperties() {
        setOrientation(LinearLayout.VERTICAL);
        setShowDividers(SHOW_DIVIDER_MIDDLE);
        setPadding(5, 0, 5, 0);
    }

    private TextView createLabelView(String title) {
        labelView = new TextView(getContext());
        labelView.setText(title);
        labelView.setTextSize(16);
        labelView.setPadding(5, 5, 5, 0);
        return labelView;
    }

    public View showToolTip(String message) {
        toolTipView.setText(message);
        toolTipView.setVisibility(VISIBLE);
        return toolTipView;
    }

    public View hideToolTip() {
        toolTipView.setVisibility(INVISIBLE);
        return toolTipView;
    }

    public Editor getEditorView() {
        return (Editor) editorView;
    }

    public TextView getLabelView() {
        return labelView;
    }
}
