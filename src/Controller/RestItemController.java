package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.MyListenerR;
import model.Restaurant;

import java.util.logging.Level;

public class RestItemController {
    @FXML
    private Label RestnameLabel;

    @FXML
    private Label cuisineLabel;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        Main.logger.log(Level.INFO, "Restaurant Listener Started...");
        myListener.onClickListener(rest);
    }

    private Restaurant rest;
    private MyListenerR myListener;

    public void setData(Restaurant rest, MyListenerR myListener) {
        Main.logger.log(Level.INFO, "Restaurant SetData Initialized...");
        this.rest = rest;
        this.myListener = myListener;
        RestnameLabel.setText(rest.getName());
        cuisineLabel.setText(" " + rest.getCuisine());
        Image image = new Image(rest.getImgSrc());
        img.setImage(image);
        Main.logger.log(Level.INFO, "Restaurant SetData Completed...");
    }
}
