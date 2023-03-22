package com.example.letstravel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button bt_go;

    @FXML
    private ImageView iv_bg1;


    @FXML
    private ImageView iv_plane;

    @FXML
    private Label l_lets;

    @FXML
    private Label l_travel;


    @FXML
    void goclicked(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        Stage stage= new Stage();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setTitle("LetsTravel");
        stage.setScene(scene);
        stage.show();
    }
        @FXML
        void foodclicked(MouseEvent event) {

        }
        @FXML
        void hotelsclicked(MouseEvent event) {

        }
        @FXML
        void placesclicked(MouseEvent event) {

        }
    }


