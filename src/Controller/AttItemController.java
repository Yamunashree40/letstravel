package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.MyListenerA;
import model.Attraction;

import java.util.logging.Level;

public class AttItemController {
    @FXML
    private Label AttnameLabel;

    @FXML
    private Label RatingLabel;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        Main.logger.log(Level.INFO, "Attraction Listener Started...");
        myListener.onClickListener(Att);
    }

    private Attraction Att;
    private MyListenerA myListener;

    public void setData(Attraction Att, MyListenerA myListener) {
        Main.logger.log(Level.INFO, "Attraction SetData Initialized...");

        this.Att = Att;
        this.myListener = myListener;
        AttnameLabel.setText(Att.getName());
        RatingLabel.setText(" " + Att.getRating());
        Image image = new Image(Att.getImgSrc());
        img.setImage(image);
        Main.logger.log(Level.INFO, "Attraction SetData Completed...");

    }
}
