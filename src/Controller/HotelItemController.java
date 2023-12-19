package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.MyListenerH;
import model.Hotel;

import java.util.logging.Level;

public class HotelItemController {
    @FXML
    private Label HotelnameLabel;

    @FXML
    private Label ratingLabel;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        Main.logger.log(Level.INFO, "Hotel Listener Started...");
        myListener.onClickListener(hotel);
    }

    private Hotel hotel;
    private MyListenerH myListener;

    public void setData(Hotel hotel, MyListenerH myListener) {
        Main.logger.log(Level.INFO, "Hotel SetData Initialized...");
        this.hotel = hotel;
        this.myListener = myListener;
        HotelnameLabel.setText(hotel.getName());
        ratingLabel.setText(" " + hotel.getRat());
        Image image = new Image(hotel.getImgSrc());
        img.setImage(image);
        Main.logger.log(Level.INFO, "Hotel SetData Completed...");
    }
}
