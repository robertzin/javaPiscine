package edu.school21.spring.renderer;

import edu.school21.spring.preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer{
    PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public String manageString(String message) {
        return preProcessor.manageString(message);
    }
}
