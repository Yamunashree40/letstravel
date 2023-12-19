package Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import main.Main;
import main.MyListenerH;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Hotel;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
public class HotelController implements Initializable {
    @FXML
    private VBox chosenHotelCard;
    private String HrefLinkHotel;
    @FXML
    private Text hotelNameLabel;
    @FXML
    private Text hotelCotLabel;
    @FXML
    private Text hotelPriceLabel;
    @FXML
    private ImageView hotelImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private List<Hotel> hotels = new ArrayList<>();
    private Image image;
    private MyListenerH myListener;
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
    void Bookclicked(javafx.scene.input.MouseEvent event) throws IOException {
        Main.logger.log(Level.INFO, "Book Button Clicked...");
        Main.logger.log(Level.INFO, "Redirecting to Web Page...");
        Stage stage= new Stage();
        stage.setTitle(hotelNameLabel.getText());
        System.out.println(HrefLinkHotel);
        WebView webView = new WebView();
        System.out.println(HrefLinkHotel);
        webView.getEngine().load(HrefLinkHotel);
        VBox vBox = new VBox(webView);
        Scene scene = new Scene(vBox, 960, 600);
        stage.setScene(scene);
        stage.show();
        Main.logger.log(Level.INFO, "Web Page Displayed...");
    }
    @FXML
    void Restclicked(javafx.scene.input.MouseEvent event) throws IOException {
        Main.logger.log(Level.INFO, "Restaurant Button Clicked...");
        Main.logger.log(Level.INFO, "Redirecting to Restaurant Page...");
        Parent root = FXMLLoader.load(getClass().getResource("../views/Restaurant/RestM.fxml"));
        Stage stage= new Stage();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setTitle("Restaurants");
        stage.setScene(scene);
        stage.show();
        Main.logger.log(Level.INFO, "Restaurant Page Displayed...");
    }
    private List<Hotel> getData() {
        Main.logger.log(Level.INFO, "Data Gathering Initialized...");
        HotelController scraper = new HotelController();
        List<Hotel> hotels = new ArrayList<>();
        Hotel hotel;
        List<String[]> hlist = scraper.getHotelsData(HelloController.place);
        int i;
        for (String[] hotelData : hlist) {
            i=0;
            hotel = new Hotel();
            for (String value : hotelData) {
                switch(i){
                    case 0: hotel.setName(value);
                            break;
                    case 1:hotel.setImgSrc(value);
                            break;
                    case 2:hotel.setHref(value);
                            break;
                    case 3:hotel.setRat(value);
                            break;
                    case 4:hotel.setPrice(value);
                        break;
                    case 5:int index = value.indexOf("#");
                        if (index != -1) {
                            value = value.substring(0, index);
                        }
                        hotel.setNeighborhood(value);
                        break;
                }
                i=i+1;
            }
            Random random = new Random();
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color randomColor = new Color(red, green, blue);
            hotel.setColor(String.format("#%02x%02x%02x", randomColor.getRed(), randomColor.getGreen(), randomColor.getBlue()));
            hotels.add(hotel);
        }
        Main.logger.log(Level.INFO, "Data Gathering Completed...");
        return hotels;
    }

    private void setChosenHotel(Hotel hotel) {
        hotelNameLabel.setText(hotel.getName());;
        hotelPriceLabel.setText("Starting From " + hotel.getPrice());
        Image image = new Image(hotel.getImgSrc());
        hotelImg.setImage(image);
        chosenHotelCard.setStyle("-fx-background-color: #" + hotel.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
        hotelCotLabel.setText(hotel.getNeighborhood());
        HrefLinkHotel = hotel.getHref();
    }


    public List<String[]> getHotelsData(String place) {
        Main.logger.log(Level.INFO, "Hotel Webscrape Initialized...");
        List<String[]> hotels = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.holidify.com/places/" + place.toLowerCase() + "/hotels-where-to-stay.html").get();
            Elements cards = doc.select("div.card.content-card");
            for (Element card : cards) {
                String hotelName = card.select("h3.card-heading").text();
                hotelName = hotelName.replaceAll("^\\d+\\.\\s+", "");
                String imageLink = card.select("div.collection-scrollable img.card-img-top").attr("data-original");
                String dataHref1 = "https://www.holidify.com/" + card.select("a").attr("href");
                String rating = card.select("span.rating-badge b").text();
                String price = card.select("p.hotel-price span.price").text();
                String neighborhood = card.select("p.hotel-neighbourhood").text();
                String[] HotelData = new String[6];
                HotelData[0] = hotelName;
                HotelData[1] = imageLink;
                HotelData[2] = dataHref1;
                HotelData[3] = rating;
                HotelData[4] = price;
                HotelData[5] = neighborhood;
                hotels.add(HotelData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.logger.log(Level.INFO, "Hotel Webscrape Completed...");
        return hotels;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.logger.log(Level.INFO, "Hotel Display Initialized...");
        hotels.addAll(getData());
        for(int i=0;i<5;i++){
            Main.logger.log(Level.INFO, "Hotel "+i+" Initialized...");
            setChosenHotel(hotels.get(i));
            Main.logger.log(Level.INFO, "Hotel "+i+" Gathered Successfully...");
            myListener = new MyListenerH() {
                @Override
                public void onClickListener(Hotel hotel) {
                    setChosenHotel(hotel);
                }

            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < 5; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/Hotel/Hotelitem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                HotelItemController itemController = fxmlLoader.getController();
                itemController.setData(hotels.get(i),myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
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
