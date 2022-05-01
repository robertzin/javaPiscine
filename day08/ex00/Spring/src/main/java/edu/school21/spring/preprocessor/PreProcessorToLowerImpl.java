package edu.school21.spring.preprocessor;

import java.util.HashMap;

public class PreProcessorToLowerImpl implements PreProcessor{

    @Override
    public String manageString(String message) {
        return message.toLowerCase();
    }
}
