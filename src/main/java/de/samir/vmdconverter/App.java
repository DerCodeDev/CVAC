package de.samir.vmdconverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

/**
 * JavaFX App
 */
public class App extends Application {

    private final Locale locale = Locale.getDefault();
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        switch (locale.getDisplayLanguage()) {
            default:
                scene = new Scene(loadFXML("english"));
                break;
            case "Deutsch":
                scene = new Scene(loadFXML("german"));
                break;
            case "עִברִית":
                scene = new Scene(loadFXML("hebrew"));
                break;
            case "Русский":
                scene = new Scene(loadFXML("russian"));
                break;
        }

        stage.setResizable(false);
        stage.setTitle("Coktel Vision AIO Converter 1.4");
        stage.setScene(scene);
        stage.getIcons().add(new Image("https://required.icon/insert_here.png"));
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Scene getScene() {
        return scene;
    }
}
