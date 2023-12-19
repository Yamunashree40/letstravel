package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import main.Main;
import main.MyListenerR;
import model.Restaurant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class RestaurantController implements Initializable {
    @FXML
    private VBox chosenRestCard;

    @FXML
    private Text RestNameLabel;
    @FXML
    private Text RestTimeLabel;

    private String RestHref;
    @FXML
    private Label RestCuisineLabel;

    @FXML
    private ImageView RestImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    void Attclicked(javafx.scene.input.MouseEvent event) throws IOException {

        Main.logger.log(Level.INFO, "Attraction Button Clicked...");
        Main.logger.log(Level.INFO, "Redirecting to Attraction Page...");
        Parent root = FXMLLoader.load(getClass().getResource("../views/Attraction/AttM.fxml"));
        Stage stage= new Stage();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setTitle("Attractions");
        stage.setScene(scene);
        stage.show();
        Main.logger.log(Level.INFO, "Attraction Page Displayed...");
    }

    @FXML
    void Expclicked(javafx.scene.input.MouseEvent event) throws IOException {
        Main.logger.log(Level.INFO, "Explore Button Clicked...");
        Main.logger.log(Level.INFO, "Redirecting to Web Page...");
        Stage stage= new Stage();
        stage.setTitle(RestNameLabel.getText());
        WebView webView = new WebView();
        webView.getEngine().load(RestHref);
        VBox vBox = new VBox(webView);
        Scene scene = new Scene(vBox, 960, 600);
        stage.setScene(scene);
        stage.show();
        Main.logger.log(Level.INFO, "Web Page Displayed...");

    }
    @FXML
    void Hotelclicked(javafx.scene.input.MouseEvent event) throws IOException {
        Main.logger.log(Level.INFO, "Hotel Button Clicked...");
        Main.logger.log(Level.INFO, "Redirecting to Hotel Page...");
        Parent root = FXMLLoader.load(getClass().getResource("../views/Hotel/HotelM.fxml"));
        Stage stage= new Stage();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setTitle("Hotel");
        stage.setScene(scene);
        stage.show();
        Main.logger.log(Level.INFO, "Hotel Page Displayed...");
    }

    private List<Restaurant> rests = new ArrayList<>();
    private Image image;
    private MyListenerR myListener;
    private List<Restaurant> getData() {
        Main.logger.log(Level.INFO, "Data Gathering Initialized...");
        RestaurantController scraper1 = new RestaurantController();
        List<Restaurant> rests = new ArrayList<>();
        Restaurant rest;
        List<String[]> rlist = scraper1.getRestaurantsData(HelloController.place);
        int i;
        for (String[] RestData : rlist) {
            i=0;
            rest = new Restaurant();
            for (String value : RestData) {
                switch(i){
                    case 0: rest.setName(value);
                            break;
                    case 1:rest.setImgSrc(value);
                            break;
                    case 2:rest.setLocation(value);
                            break;
                    case 3:rest.setCuisine(value);
                            break;
                    case 4:rest.setTimings(value);
                        break;
                    case 5:rest.setPrice(value);
                        break;
                    case 6:rest.setHref(value);
                        break;
                }
                i=i+1;
            }
            Random random = new Random();
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color randomColor = new Color(red, green, blue);
            rest.setColor(String.format("#%02x%02x%02x", randomColor.getRed(), randomColor.getGreen(), randomColor.getBlue()));
            rests.add(rest);
        }
        Main.logger.log(Level.INFO, "Data Gathering Completed...");

        return rests;
    }

    private void setChosenRest(Restaurant rest) {
        RestNameLabel.setText(rest.getName());;
        RestCuisineLabel.setText(" " + rest.getCuisine());
        Image image = new Image(rest.getImgSrc());
        RestImg.setImage(image);
        chosenRestCard.setStyle("-fx-background-color: #" + rest.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
        RestTimeLabel.setText(rest.getTimings());
        RestHref = rest.getHref();
    }

    public List<String[]> getRestaurantsData(String place) {
        Main.logger.log(Level.INFO, "Restaurant Webscrape Initialized...");
        List<String[]> restaurants = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.holidify.com/places/" + place.toLowerCase() + "/restaurants-places-to-eat-local-cuisine.html").get();
            Elements cards = doc.select(".card.content-card");
            for (Element card : cards) {
                String title = card.select("h3.card-heading").text();
                title = title.replaceAll("^\\d+\\.\\s+", "");
                String imageUrl = card.select("div[data-hotel-position] img").attr("data-original");
                String location = card.select("div.restaurantItems > div:nth-child(1)").text();
                String cuisine = card.select("div.restaurantItems > div:nth-child(2)").text();
                String timings = card.select("div.restaurantItems > div:nth-child(3)").text();
                String price = card.select("div.restaurantItems > div:nth-child(4)").text();
                String detailsUrl = "https://www.holidify.com/" + card.select("div.content-card-footer").attr("data-href");

                String[] RestData = new String[7];
                RestData[0] = title;
                RestData[1] = imageUrl;
                RestData[2] = location;
                RestData[3] = cuisine;
                RestData[4] = timings;
                RestData[5] = price;
                RestData[6] = detailsUrl;

                restaurants.add(RestData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.logger.log(Level.INFO, "Restaurant Webscrape Completed...");
        return restaurants;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.logger.log(Level.INFO, "Hotel Display Initialized...");
        rests.addAll(getData());
        for(int i=0;i<5;i++){

            Main.logger.log(Level.INFO, "Restaurant "+i+" Initialized...");
            setChosenRest(rests.get(i));
            Main.logger.log(Level.INFO, "Restaurant "+i+" Gathered Successfully...");
            myListener = new MyListenerR() {
                @Override
                public void onClickListener(Restaurant rest) {
                    setChosenRest(rest);
                }

            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < 5; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/Restaurant/Restitem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                RestItemController itemController = fxmlLoader.getController();
                itemController.setData(rests.get(i),myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.logger.log(Level.INFO, "Hotel Displayed Successfully...");
    }

}
