package edu.school21.spring.renderer;

import edu.school21.spring.preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer{
    PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public String manageString(String message) {
        return preProcessor.manageString(message);
    }
}
