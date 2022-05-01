package edu.school21.spring.printer;

import edu.school21.spring.renderer.Renderer;
import edu.school21.spring.renderer.RendererErrImpl;

public class PrinterWithPrefixImpl implements Printer {
    private Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String message) {
        String toOperate = prefix + " " + message;
        if (renderer instanceof RendererErrImpl) {
            System.out.println("\u001B[31m" + renderer.manageString(toOperate));
        } else
            System.out.println(renderer.manageString(toOperate));
    }
}
