package edu.school21.spring.printer;

import edu.school21.spring.renderer.Renderer;
import edu.school21.spring.renderer.RendererErrImpl;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    private final Renderer renderer;
    private LocalDateTime date = LocalDateTime.now();

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {
        String toOperate = date.toString() + " " + message;
        if (renderer instanceof RendererErrImpl) {
            System.out.println("\u001B[31m" + toOperate + " " + renderer.manageString(message));
        } else
            System.out.println(toOperate + " " + renderer.manageString(message));
    }
}
