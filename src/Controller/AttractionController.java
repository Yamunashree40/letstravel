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
import main.MyListenerA;
import model.Attraction;
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

public class AttractionController implements Initializable {
    @FXML
    private VBox chosenAttCard;

    @FXML
    private Text AttNameLabel;
    @FXML
    private Text AttCotLabel;

    @FXML
    private Label AttRatLabel;
    private String AttHref;

    @FXML
    private ImageView AttImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Attraction> Atts = new ArrayList<>();
    private Image image;
    private MyListenerA myListener;
    @FXML
    void Expclicked(javafx.scene.input.MouseEvent event) throws IOException {
        Main.logger.log(Level.INFO, "Explore Button Clicked...");
        Main.logger.log(Level.INFO, "Redirecting to Web Page...");
        Stage stage= new Stage();
        stage.setTitle(AttNameLabel.getText());
        WebView webView = new WebView();
        webView.getEngine().load(AttHref);
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
        stage.setTitle("Hotels");
        stage.setScene(scene);
        stage.show();
        Main.logger.log(Level.INFO, "Hotel Page Displayed...");

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

    private List<Attraction> getData() {
        Main.logger.log(Level.INFO, "Data Gathering Initialized...");
        AttractionController scraper1 = new AttractionController();
        List<Attraction> Atts = new ArrayList<>();
        Attraction Att;
        List<String[]> alist = scraper1.getAttractionsData(HelloController.place);
        int i;
        for (String[] RestData : alist) {
            i=0;
            Att = new Attraction();
            for (String value : RestData) {
                switch(i){
                    case 0: Att.setName(value);
                            break;
                    case 1:Att.setHref(value);
                            break;
                    case 2:Att.setRating(value);
                            break;
                    case 3:Att.setLocation(value);
                            break;
                    case 4:Att.setCardText(value);
                        break;
                    case 5:Att.setImgSrc(value);
                        break;

                }
                i=i+1;
            }
            Random random = new Random();
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color randomColor = new Color(red, green, blue);
            Att.setColor(String.format("#%02x%02x%02x", randomColor.getRed(), randomColor.getGreen(), randomColor.getBlue()));
            Atts.add(Att);
        }

        Main.logger.log(Level.INFO, "Data Gathering Completed...");
        return Atts;
    }

    private void setChosenAtt(Attraction Att) {
        AttNameLabel.setText(Att.getName());;
        AttRatLabel.setText(" " + Att.getRating());
        AttCotLabel.setText(Att.getCardText());
        Image image = new Image(Att.getImgSrc());
        AttImg.setImage(image);
        chosenAttCard.setStyle("-fx-background-radius: 30;");
        scroll.setStyle("-fx-background-color: null");
        AttHref = Att.getHref();
    }

    public List<String[]> getAttractionsData(String place) {
        Main.logger.log(Level.INFO, "Hotel Webscrape Initialized...");
        List<String[]> attractions = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.holidify.com/places/" + place.toLowerCase() + "/sightseeing-and-things-to-do.html").get();
            Elements attractionList = doc.select("#attractionList .content-card");
            for (Element attraction : attractionList) {
                String dataHref = attraction.select("a").attr("data-href");
                String[] cardHeading = attraction.select("h3.card-heading").text().split("\\.");
                String ratingBadge = attraction.select("span.rating-badge").text();
                String objective = attraction.select("p.objective").text();
                String cardText = attraction.select("p.card-text").text();
                String imgSrc = attraction.select("img").attr("data-original");

                String[] attractionData = new String[6];
                attractionData[0] = cardHeading[1].trim();
                attractionData[1] = dataHref;
                attractionData[2] = ratingBadge;
                attractionData[3] = objective;
                attractionData[4] = cardText;
                attractionData[5] = imgSrc;

                attractions.add(attractionData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.logger.log(Level.INFO, "Hotel Webscrape Completed...");
        return attractions;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.logger.log(Level.INFO, "Attraction Display Initialized...");
        try {
        Atts.addAll(getData());
            int column = 0;
            int row = 1;
        for(int i=0;i<5;i++){
            Main.logger.log(Level.INFO, "Attraction "+i+" Initialized...");
            setChosenAtt(Atts.get(i));
            Main.logger.log(Level.INFO, "Attraction "+i+" Gathered Successfully...");
            myListener = new MyListenerA() {
                @Override
                public void onClickListener(Attraction Att) {
                    setChosenAtt(Att);
                }

            };
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/Attraction/Attitem.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            AttItemController itemController = fxmlLoader.getController();
            itemController.setData(Atts.get(i),myListener);

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
        Main.logger.log(Level.INFO, "Attraction Displayed Successfully...");

    }

}
