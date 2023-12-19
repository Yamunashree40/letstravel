package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class Main extends Application {
    public static Logger logger = Logger.getLogger("MyLog");
    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.log(Level.INFO, "Start Function Initialized");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/hello-view.fxml"));
        Scene scene = null;
        boolean connected = false;
        while (!connected) {
            try {
                scene = new Scene(fxmlLoader.load());
                connected = true;
            } catch (Exception e) {
                Main.logger.log(Level.WARNING, "Exception :", e);
                Thread.sleep(5000);
                logger.log(Level.INFO, "Restarting...");
            }
        }
        primaryStage.setTitle("LetsTravel");
        primaryStage.setScene(scene);
        primaryStage.show();
        logger.log(Level.INFO, "Welcome Screen Displayed");
    }
    public static void main(String[] args) throws IOException {
        try {
            FileHandler fh = new FileHandler("C:\\Users\\hp\\Desktop\\LetsTravel\\src\\Log\\MyLogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.info("Logger Intialized");
            launch(args);
        }
        catch(Exception e){
            Main.logger.log(Level.WARNING,"Exception ::",e);
        }
        logger.info("End of Program");
    }
}

