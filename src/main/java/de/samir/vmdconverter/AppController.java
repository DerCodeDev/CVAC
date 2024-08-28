package de.samir.vmdconverter;

import de.samir.vmdconverter.converter.VMDConverter;
import de.samir.vmdconverter.utils.ConsoleOutput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class AppController {

    private static String fileName;
    public Button startButton;
    @FXML
    private TextField inputField;

    @FXML
    private TextField outputField;

    @FXML
    private ComboBox<String> formatBox;

    private final ObservableList<String> formatsList = FXCollections.observableArrayList("WAV (22050)", "WAV (44100)", "MP3", "MP4");

    @FXML
    private TextArea consoleLog;
    @FXML
    private ComboBox<String> comboBox;
    private final ObservableList<String> languagesList = FXCollections.observableArrayList("Deutsch (German)",
            "English (US)", "עִברִית (Hebrew)", "Русский (RU)", "Shqiptare (ALB)");

    @FXML
    public void inputFieldBrowseButton(final ActionEvent actionEvent) {
        actionEvent.consume();
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select your File!");
        // Filter here for the allowed File extensions
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video and Music Data (*.vmd)", "*.vmd"));
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("IMD File (*.imd)", "*.imd"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DAT File (*.dat)", "*.dat"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("STB File (*.stb)", "*.stb"));
        final File selectedFile = fileChooser.showOpenDialog(App.getScene().getWindow());
        if (selectedFile != null) {
            inputField.setText(selectedFile.getAbsolutePath());
            fileName = selectedFile.getName()
                    .replaceAll("(?i).vmd", "")
                    //.replaceAll("(?i).imd", "")
                    .replaceAll("(?i).dat", "")
                    .replaceAll("(?i).stb", "");

        }
    }

    @FXML
    public void outputFieldBrowseButton(final ActionEvent actionEvent) {
        actionEvent.consume();
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select your output file location!");
        final File selectedDirectory = directoryChooser.showDialog(App.getScene().getWindow());

        if (selectedDirectory != null) {
            outputField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    public void initialize() {
        ConsoleOutput consoleOutput = new ConsoleOutput(consoleLog);
        PrintStream consolePrintStream = new PrintStream(consoleOutput);
        System.setOut(consolePrintStream);
        comboBox.setItems(languagesList);
        formatBox.setItems(formatsList);

    }

    @FXML
    public void switchLanguage(final ActionEvent actionEvent) throws IOException {
        actionEvent.consume();
        if (comboBox.getValue().equals(languagesList.get(0))) {
            App.setRoot("german");
        } else if (comboBox.getValue().equals(languagesList.get(1))) {
            App.setRoot("english");
        } else if (comboBox.getValue().equals(languagesList.get(2))) {
            App.setRoot("hebrew");
        } else if (comboBox.getValue().equals(languagesList.get(3))) {
            App.setRoot("russian");
        } else if (comboBox.getValue().equals(languagesList.get(4))) {
            App.setRoot("albanian");
        }
    }

    @FXML
    public void startButton(final ActionEvent actionEvent) {
        actionEvent.consume();
        if (inputField.getText().isEmpty() || inputField.getText() == null) {
            showErrorMessage();
            return;
        }
        if (outputField.getText().isEmpty() || outputField.getText() == null) {
            showErrorMessage();
            return;
        }


        if (formatBox.getSelectionModel().isEmpty()) {
            showErrorMessage();
            return;
        }
        if (formatBox.getValue().equals(formatsList.get(0))) {
            Task<Void> conversionTask = new Task<>() {
                @Override
                protected Void call() {
                    VMDConverter.convertAudioWAV22050(inputField.getText(), outputField.getText());
                    return null;
                }
            };
            ConsoleOutput consoleOutput = new ConsoleOutput(consoleLog);
            PrintStream consolePrintStream = new PrintStream(consoleOutput);
            System.setOut(consolePrintStream);

            Thread conversionThread = new Thread(conversionTask);
            conversionThread.setDaemon(true);
            conversionThread.start();
            return;
        }
        if (formatBox.getValue().equals(formatsList.get(1))) {

            Task<Void> conversionTask = new Task<>() {
                @Override
                protected Void call() {
                    VMDConverter.convertAudioWAV44100(inputField.getText(), outputField.getText());
                    return null;
                }
            };
            ConsoleOutput consoleOutput = new ConsoleOutput(consoleLog);
            PrintStream consolePrintStream = new PrintStream(consoleOutput);
            System.setOut(consolePrintStream);

            Thread conversionThread = new Thread(conversionTask);
            conversionThread.setDaemon(true);
            conversionThread.start();


            return;
        }
        if (formatBox.getValue().equals(formatsList.get(2))) {
            Task<Void> conversionTask = new Task<>() {
                @Override
                protected Void call() {
                    VMDConverter.convertAudioMP3(inputField.getText(), outputField.getText());
                    return null;
                }
            };
            ConsoleOutput consoleOutput = new ConsoleOutput(consoleLog);
            PrintStream consolePrintStream = new PrintStream(consoleOutput);
            System.setOut(consolePrintStream);

            Thread conversionThread = new Thread(conversionTask);
            conversionThread.setDaemon(true);
            conversionThread.start();
            return;
        }
        if (formatBox.getValue().equals(formatsList.get(3))) {
            Task<Void> conversionTask = new Task<>() {
                @Override
                protected Void call() {
                    VMDConverter.convertVideoMP4(inputField.getText(), outputField.getText());
                    return null;
                }
            };
            ConsoleOutput consoleOutput = new ConsoleOutput(consoleLog);
            PrintStream consolePrintStream = new PrintStream(consoleOutput);
            System.setOut(consolePrintStream);

            Thread conversionThread = new Thread(conversionTask);
            conversionThread.setDaemon(true);
            conversionThread.start();
        }
    }

    private void showErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText("Please fill out all fields properly.");
        alert.showAndWait();
    }

    public static String getFilename() {
        return fileName;
    }
}

