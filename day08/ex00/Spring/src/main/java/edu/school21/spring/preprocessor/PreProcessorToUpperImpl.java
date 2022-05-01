package edu.school21.spring.preprocessor;

import java.util.HashMap;

public class PreProcessorToUpperImpl implements PreProcessor {

    @Override
    public String manageString(String message) {
        return message.toUpperCase();
    }
}
