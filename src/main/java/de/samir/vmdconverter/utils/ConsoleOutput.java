package de.samir.vmdconverter.utils;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.OutputStream;

public class ConsoleOutput extends OutputStream {

    private final TextArea consoleLog;

    public ConsoleOutput(TextArea consoleLog) {
        this.consoleLog = consoleLog;
    }

    @Override
    public void write(int b) {
        char character = (char) b;
        Platform.runLater(() -> consoleLog.appendText(String.valueOf(character)));
    }
}

