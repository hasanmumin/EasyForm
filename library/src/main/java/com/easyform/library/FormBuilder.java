package com.easyform.library;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.easyform.library.editors.AutoCompleteElement;
import com.easyform.library.editors.BigDecimalElement;
import com.easyform.library.editors.DateElement;
import com.easyform.library.editors.DateTimeElement;
import com.easyform.library.editors.DoubleElement;
import com.easyform.library.editors.Editor;
import com.easyform.library.editors.EmailElement;
import com.easyform.library.editors.IntegerElement;
import com.easyform.library.editors.MultiSelectElement;
import com.easyform.library.editors.OnlyIntegerStringElement;
import com.easyform.library.editors.PhotoElement;
import com.easyform.library.editors.SpinnerElement;
import com.easyform.library.editors.SwitchElement;
import com.easyform.library.editors.TextElement;
import com.easyform.library.editors.TimeElement;
import com.easyform.library.editors.model.PairModel;
import com.easyform.library.editors.validators.AutoCompletionValidator;
import com.easyform.library.editors.validators.DateMinMaxValidator;
import com.easyform.library.editors.validators.DateTimeMinMaxValidator;
import com.easyform.library.editors.validators.DoubleMinMaxValidator;
import com.easyform.library.editors.validators.IntegerMinMaxValidator;
import com.easyform.library.editors.validators.NotEqValidator;
import com.easyform.library.editors.validators.RegexValidator;
import com.easyform.library.editors.validators.Validator;
import com.easyform.library.util.ValidationUtil;

import static com.easyform.library.util.ObjectUtil.isNotNull;
import static com.easyform.library.util.ObjectUtil.isNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FormBuilder {

    private final Context context;
    private Form form;
    private Section section;
    private Element element;

    public FormBuilder(Context context) {
        this.context = context;
        form = new Form(context);
    }

    public FormBuilder createSection(String title) {
        return createSection(null, title);
    }

    public FormBuilder createSection(String tag, String title) {
        section = new Section(context, title);
        if (isNotNull(tag)) {
            section.setTag(tag);
        }
        form.addView(section);
        element = null;

        return this;
    }

    public FormBuilder createElement(String tag, String title, Editor.EditorTypes type, Object value) {
        createElement(tag, title, type, value, null);
        return this;
    }

    public FormBuilder createElement(String tag, String title, Editor.EditorTypes type, Object value, View.OnKeyListener onKeyListener) {
        createElement(tag, title, type, value, onKeyListener, true);
        return this;
    }

    public FormBuilder createElement(String tag, String title, Editor.EditorTypes type, Object value, View.OnKeyListener onKeyListener, boolean editable) {
        if (isNull(section))
            createSection("");

        Editor editor = null;
        switch (type) {
            case TEXT:
                editor = new TextElement(context, value);
                break;
            case TEXT_UPPERCASE:
                editor = new TextElement(context, value, true);
                break;
            case BOOLEAN:
                editor = new SwitchElement(context, value);
                break;
            case DATE:
                editor = new DateElement(context, value);
                break;
            case EMAIL:
                editor = new EmailElement(context, value);
                break;
            case INTEGER:
                editor = new IntegerElement(context, value);
                break;
            case ONLY_INTEGER_STRING:
                editor = new OnlyIntegerStringElement(context, value);
                break;
            case DOUBLE:
                editor = new DoubleElement(context, value);
                break;
            case TIME:
                editor = new TimeElement(context, value);
                break;
            case DATETIME:
                editor = new DateTimeElement(context, value);
                break;
            case BIG_DECIMAL:
                editor = new BigDecimalElement(context, value);
                break;
            case PHOTO:
                editor = new PhotoElement(context, value);
                break;
        }
        View editorView = (View) editor;

        if (isNotNull(editorView)) {
            editorView.setTag(tag);
            editorView.setEnabled(editable);

            if (isNotNull(onKeyListener)) {
                editorView.setOnKeyListener(onKeyListener);
            }
        }


        element = new Element(context, title, editorView);
        section.addView(element);

        return this;
    }

    public FormBuilder createMultiSelectElement(String tag, String title, String values, PairModel[] models) {

        if (isNull(section)) {
            createSection("");
        }

        MultiSelectElement editor = new MultiSelectElement(context, models);
        editor.setTag(tag);
        editor.setValue(values);
        element = new Element(context, title, editor);
        section.addView(element);

        return this;
    }

    public FormBuilder createSpinnerElement(String tag, String title, List<?> values, Object value) {
        return createSpinnerElement(tag, title, values, value, null, null);
    }


    public FormBuilder createSpinnerElement(String tag, String title, List<?> values, View.OnClickListener onClickListener) {
        return createSpinnerElement(tag, title, values, null, null, onClickListener);
    }

    public FormBuilder createSpinnerElement(String tag, String title, List<?> values, Object value, View.OnClickListener onClickListener) {
        return createSpinnerElement(tag, title, values, value, null, onClickListener);
    }

    public FormBuilder createSpinnerElement(String tag, String title, List<?> values, AdapterView.OnItemSelectedListener onItemSelectedListener) {
        return createSpinnerElement(tag, title, values, null, onItemSelectedListener, null);
    }

    public FormBuilder createSpinnerElement(String tag, String title, List<?> values, Object value, AdapterView.OnItemSelectedListener onItemSelectedListener) {
        return createSpinnerElement(tag, title, values, value, onItemSelectedListener, null);
    }

    public FormBuilder createSpinnerElement(String tag, String title, List<?> values, String value, AdapterView.OnItemSelectedListener onItemSelectedListener) {
        return createSpinnerElement(tag, title, values, value, onItemSelectedListener, null);
    }

    public FormBuilder createSpinnerElement(String tag, String title, List<?> values, AdapterView.OnItemSelectedListener onItemSelectedListener, View.OnClickListener onClickListener) {
        return createSpinnerElement(tag, title, values, null, onItemSelectedListener, onClickListener);
    }

    public FormBuilder createSpinnerElement(String tag, String title, List<?> values, Object value, AdapterView.OnItemSelectedListener onItemSelectedListener, View.OnClickListener onClickListener) {
        if (isNull(section)) {
            createSection("");
        }

        SpinnerElement editor = new SpinnerElement(context, values);

        editor.setTag(tag);

        if (isNotNull(onClickListener)) {
            editor.setOnClickListener(onClickListener);
        }

        if (isNotNull(onItemSelectedListener)) {
            editor.setOnItemSelectedListener(onItemSelectedListener);
        }

        if (isNotNull(value)) {
            editor.setValue(value);
        }

        element = new Element(context, title, editor);
        section.addView(element);

        return this;
    }

    public FormBuilder createSpinnerElementWithValidation(String tag, String title, List<?> values, Object value) {
        return createSpinnerElementWithValidation(tag, title, values, value, null, null);
    }

    public FormBuilder createSpinnerElementWithValidation(String tag, String title, List<?> values, Object value, AdapterView.OnItemSelectedListener onItemSelectedListener, View.OnClickListener onClickListener) {

        if (isNull(section)) {
            createSection("");
        }

        List<Object> tempValues = new ArrayList<>(values.size() + 1);
        tempValues.addAll(values);
        tempValues.add(0, ValidationUtil.PLEASE_SELECT);

        SpinnerElement editor = new SpinnerElement(context, tempValues);

        editor.setTag(tag);

        if (isNotNull(onClickListener)) {
            editor.setOnClickListener(onClickListener);
        }

        if (isNotNull(onItemSelectedListener)) {
            editor.setOnItemSelectedListener(onItemSelectedListener);
        }

        if (isNotNull(value)) {
            editor.setValue(value);
        }

        element = new Element(context, title, editor);
        section.addView(element);

        return this;
    }


    public FormBuilder createAutoCompleteElement(String tag, String title, List<? extends PairModel> values, Object value) {
        return createAutoCompleteElement(tag, title, values, value, null, null);
    }


    public FormBuilder createAutoCompleteElement(String tag, String title, List<? extends PairModel> values, Object value, AdapterView.OnItemClickListener onItemClickListener, View.OnClickListener onClickListener) {
        if (isNull(section)) {
            createSection("");
        }

        AutoCompleteElement editor = new AutoCompleteElement(context, values);

        editor.setTag(tag);

        if (isNotNull(onClickListener)) {
            editor.setOnClickListener(onClickListener);
        }

        if (isNotNull(onItemClickListener)) {
            editor.setOnItemClickListener(onItemClickListener);
        }

        if (isNotNull(value)) {
            editor.setValue(value);
        }

        element = new Element(context, title, editor);
        section.addView(element);

        return this;
    }

    public FormBuilder createButtonElement(String tag, String title, View.OnClickListener listener) {
        if (isNull(section))
            createSection("");

        ButtonElement buttonElement = new ButtonElement(context, title, listener);
        buttonElement.setTag(tag);
        section.addView(buttonElement);
        element = null;
        return this;
    }

    public FormBuilder createValidator(int min, int max, String errorMsg) {

        checkIsCalledBeforeCreateElement();

        if (element.getEditorView() instanceof IntegerElement) {
            IntegerElement ie = (IntegerElement) element.getEditorView();
            Validator vl = new IntegerMinMaxValidator(element, min, max, errorMsg);
            ie.addTextChangedListener(vl);
            form.addValidator(vl);
        }

        if (element.getEditorView() instanceof DoubleElement) {
            DoubleElement ie = (DoubleElement) element.getEditorView();
            Validator vl = new DoubleMinMaxValidator(element, min, max, errorMsg);
            ie.addTextChangedListener(vl);
            form.addValidator(vl);
        }

        return this;
    }

    public FormBuilder createValidator(Date min, Date max, String errorMsg) {

        checkIsCalledBeforeCreateElement();

        if (element.getEditorView() instanceof DateElement) {
            DateElement ie = (DateElement) element.getEditorView();
            Validator vl = new DateMinMaxValidator(element, min, max, errorMsg);
            ie.addTextChangedListener(vl);
            form.addValidator(vl);
        } else if (element.getEditorView() instanceof DateTimeElement) {
            DateTimeElement ie = (DateTimeElement) element.getEditorView();
            Validator vl = new DateTimeMinMaxValidator(element, min, max, errorMsg);
            ie.addTextChangedListener(vl);
            form.addValidator(vl);
        }
        return this;
    }

    public FormBuilder createValidator(String regex, String errorMsg) {

        checkIsCalledBeforeCreateElement();

        if (element.getEditorView() instanceof TextElement) {
            TextElement te = (TextElement) element.getEditorView();
            Validator vl = new RegexValidator(element, regex, errorMsg);
            te.addTextChangedListener(vl);
            form.addValidator(vl);
        }

        if (element.getEditorView() instanceof OnlyIntegerStringElement) {
            OnlyIntegerStringElement te = (OnlyIntegerStringElement) element.getEditorView();
            Validator vl = new RegexValidator(element, regex, errorMsg);
            te.addTextChangedListener(vl);
            form.addValidator(vl);

            return this;
        }

        return this;
    }

    public FormBuilder createNotNullValidator() {

        checkIsCalledBeforeCreateElement();

        if (element.getEditorView() instanceof EditText) {
            TextView editor = (TextView) element.getEditorView();
            Validator vl = new RegexValidator.NotNullValidator(element, "^null|$|\\s+", "Bu alan boş olamaz.");
            editor.addTextChangedListener(vl);
            form.addValidator(vl);

            return this;
        }

        if (element.getEditorView() instanceof SpinnerElement) {

            final SpinnerElement te = (SpinnerElement) element.getEditorView();

            final Validator vl = new RegexValidator.NotNullValidator(element, "^null|$|\\s+", "Bu alan boş olamaz.");

            te.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    vl.afterTextChanged(new SpannableStringBuilder(te.getText()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    vl.isValid();
                }
            });
            form.addValidator(vl);

            return this;
        }

        return this;
    }

    public FormBuilder createNotEqValidator(String value, String errorMsg) {

        checkIsCalledBeforeCreateElement();

        if (element.getEditorView() instanceof TextElement) {

            TextElement te = (TextElement) element.getEditorView();
            Validator vl = new NotEqValidator(element, value, errorMsg);
            te.addTextChangedListener(vl);
            form.addValidator(vl);

            return this;
        }

        if (element.getEditorView() instanceof IntegerElement) {

            IntegerElement te = (IntegerElement) element.getEditorView();
            Validator vl = new NotEqValidator(element, value, errorMsg);
            te.addTextChangedListener(vl);
            form.addValidator(vl);

            return this;
        }

        if (element.getEditorView() instanceof SpinnerElement) {

            final SpinnerElement te = (SpinnerElement) element.getEditorView();

            final Validator vl = new NotEqValidator(element, value, errorMsg);

            te.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    vl.afterTextChanged(new SpannableStringBuilder(te.getText()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    vl.isValid();
                }
            });
            form.addValidator(vl);

            return this;
        }

        if (element.getEditorView() instanceof AutoCompleteElement) {

            final AutoCompleteElement te = (AutoCompleteElement) element.getEditorView();

            final AutoCompletionValidator vl = new AutoCompletionValidator(element, errorMsg, te.getData());

            te.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    vl.setWrittenValue(s.toString());
                    vl.afterTextChanged(new SpannableStringBuilder(te.getText()));
                }
            });

            te.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Object selectedValue = parent.getItemAtPosition(position);
                    vl.setSelectedValue(selectedValue);
                    vl.afterTextChanged(new SpannableStringBuilder(te.getText()));
                }
            });

            form.addValidator(vl);

            return this;
        }

        return this;
    }

    private void checkIsCalledBeforeCreateElement() {

        if (isNull(element) || isNull(element.getEditorView())) {
            throw new RuntimeException("createValidator call must be after createElement call");
        }
    }

    public Form build() {
        return form;
    }
}