package com.easyform.library.editors.validators;

import android.text.TextWatcher;

import com.easyform.library.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.easyform.library.util.ObjectUtil.isNull;

public class RegexValidator extends Validator implements TextWatcher {

    private Pattern pattern;

    public RegexValidator(Element element, String regex, String errorMsg) {
        super(element, errorMsg);
        pattern = Pattern.compile(regex);
    }

    @Override
    protected boolean isValid(String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    public static class NotNullValidator extends RegexValidator {

        private Pattern pattern;

        public NotNullValidator(Element element, String regex, String errorMsg) {
            super(element, regex, errorMsg);
            this.pattern = Pattern.compile(regex);
        }

        @Override
        protected boolean isValid(String text) {
            if (isNull(text)) {
                return false;
            }
            Matcher matcher = pattern.matcher(text);
            return !matcher.matches();
        }
    }
}
