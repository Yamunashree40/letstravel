package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.Main;
import javax.crypto.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.security.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.Statement;
import java.util.logging.Level;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static java.lang.Thread.sleep;

public class HelloController {

    @FXML
    private ImageView iv_exlpore;

    @FXML
    private TextField tf_search;
    private String search;
    public static String place;
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
    private Button bt_clear;

    @FXML
    private Button bt_submit;

    @FXML
    private Label l_signup;

    @FXML
    private ImageView iv_arrowexplore;

    @FXML
    private PasswordField pf_loginpass;

    @FXML
    private TextField tf_loginuser;

    @FXML
    private Label l_inidication;

    @FXML
    void logclearclicked(ActionEvent event) {
        Main.logger.log(Level.INFO, "Cleared TextFields....");
        pf_loginpass.setText("");
        tf_loginuser.setText("");
    }

    @FXML
    void SearchClick(MouseEvent event) {
        System.out.println(tf_search.getText().isEmpty());
        Main.logger.log(Level.INFO, "Search Button Clicked...");
        Main.logger.log(Level.INFO, "City Name is Assigned...");
        if(!tf_search.getText().isEmpty()) {
            iv_exlpore.setVisible(false);
        }
        place = tf_search.getText();
    }

    @FXML
    private ImageView iv_globe;

    @FXML
    void globeclicked(MouseEvent event) throws IOException {
        Main.logger.log(Level.INFO, "Globe is Clicked");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));

            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
            Main.logger.log(Level.INFO, "Redirecting to Sign In Page");
        } catch (Exception e) {
            Main.logger.log(Level.WARNING, "Exception ::", e);
        }
        Main.logger.log(Level.INFO, "Sign In Page Displayed");
    }

    @FXML
    void signupclicked(ActionEvent event) throws Exception {
        Main.logger.log(Level.INFO, "Sign Up Button is Clicked");
        try {

            Parent root = FXMLLoader.load(getClass().getResource("../views/signup.fxml"));

            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Signup");
            stage.setScene(scene);
            stage.show();
            Main.logger.log(Level.INFO, "Redirecting to Sign Up Page");
        } catch (Exception e) {
            Main.logger.log(Level.WARNING, "Exception ::", e);
        }
        Main.logger.log(Level.INFO, "Sign Up Page Displayed");
    }

    @FXML
    void submitclicked(ActionEvent event) throws InvalidAlgorithmParameterException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        Main.logger.log(Level.INFO, "Submit Button is Clicked");
        if (pf_loginpass.getText().isBlank() != false || tf_loginuser.getText().isBlank() != false) {
            Main.logger.log(Level.WARNING, "Fields are not filled");
            l_inidication.setText("Please fill all fields");
        } else {
            Boolean check;

            check = validate();
            if (check == Boolean.TRUE) {
                try {
                    Main.logger.log(Level.INFO, "Redirecting to Explore Page");
                    Parent root = FXMLLoader.load(getClass().getResource("../views/scene2.fxml"));
                    sleep(1000);
                    Stage stage = new Stage();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setTitle("Explore");
                    stage.setScene(scene);
                    stage.show();
                    Main.logger.log(Level.INFO, "Explore Page Displayed...");
                } catch (Exception e) {
                    Main.logger.log(Level.WARNING, "Exception :", e);
                }
            }
        }
    }

    @FXML
    void backexplore(MouseEvent event) throws Exception {
        Main.logger.log(Level.INFO, "Back Button Clicked...");
        Main.logger.log(Level.INFO, "Redirecting to Sign In Page...");
        Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));

        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        Main.logger.log(Level.INFO, "Sign In Page Displayed...");
    }

    @FXML
    void foodclicked(MouseEvent event) throws IOException {
        Main.logger.log(Level.INFO, "Restaurant Button Clicked...");
        Main.logger.log(Level.INFO, "Redirecting to Restaurant Page...");
        Parent root = FXMLLoader.load(getClass().getResource("../views/Restaurant/RestM.fxml"));
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Restaurants");
        stage.setScene(scene);
        stage.show();
        Main.logger.log(Level.INFO, "Restaurant Page Displayed...");
    }

    @FXML
    void hotelsclicked(MouseEvent event) throws IOException {
        Main.logger.log(Level.INFO, "Hotel Button Clicked...");
        Main.logger.log(Level.INFO, "Redirecting to Hotel Page...");
        Parent root = FXMLLoader.load(getClass().getResource("../views/Hotel/HotelM.fxml"));
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Hotels");
        stage.setScene(scene);
        stage.show();
        Main.logger.log(Level.INFO, "Restaurant Button Clicked...");
        Main.logger.log(Level.INFO, "Hotel Page Displayed...");
    }

    @FXML
    void placesclicked(MouseEvent event) throws IOException {
        Main.logger.log(Level.INFO, "Attraction Button Clicked...");
        Main.logger.log(Level.INFO, "Redirecting to Attraction Page...");
        Parent root = FXMLLoader.load(getClass().getResource("../views/Attraction/AttM.fxml"));
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Attractions");
        stage.setScene(scene);
        stage.show();
        Main.logger.log(Level.INFO, "Attraction Page Displayed...");
    }

    @FXML
    private Button bt_signclear;

    @FXML
    private Button bt_signcreate;

    @FXML
    private TextField tf_signcurrpass;

    @FXML
    private TextField tf_signemail;

    @FXML
    private ImageView iv_arrow;

    @FXML
    private TextField tf_signpass;

    @FXML
    private TextField tf_signuser;

    @FXML
    private Label s_indication;

    @FXML
    void clearclicked(ActionEvent event) {
        Main.logger.log(Level.INFO, "Cleared TextFields....");
        tf_signuser.setText("");
        tf_signpass.setText("");
        tf_signemail.setText("");
        tf_signcurrpass.setText("");
    }

    @FXML
    void createclicked(ActionEvent event) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException, NoSuchProviderException {
        if (tf_signuser.getText().isBlank() != false || tf_signpass.getText().isBlank() != false || tf_signcurrpass.getText().isBlank() != false || tf_signemail.getText().isBlank() != false) {
            s_indication.setText("Please fill all fields");
        } else {
            enterdatabase();
        }
    }

    @FXML
    void signback(MouseEvent event) throws Exception {
        Main.logger.log(Level.INFO, "Back Button Clicked...");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Signup");
            stage.setScene(scene);
            stage.show();
            Main.logger.log(Level.INFO, "Redirecting to Sign In Page...");
        } catch (Exception e) {
            Main.logger.log(Level.WARNING, "Exception ::", e);
        }
        Main.logger.log(Level.INFO, "Sign In Page Displayed");

    }

    public Boolean validate() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        Main.logger.log(Level.INFO, "Validate Function Initialized");
        Main.logger.log(Level.INFO, "Initializing Connection to database...");
        Database connect = new Database();
        Connection connectdb = connect.getConnection();
        Main.logger.log(Level.INFO, "Connected to database successfully...");
        byte[] user = tf_loginuser.getText().getBytes();
        byte[] pass = pf_loginpass.getText().getBytes();
        String suser=encrypt(Arrays.toString(user),stringKey);
        String spass=encrypt(Arrays.toString(pass),stringKey);
        String verify = "SELECT count(1) FROM useraccounts WHERE username='" + suser + "' AND passwordd = '" + spass + "'";
        try {
            Statement statement = connectdb.createStatement();
            ResultSet queryresult = statement.executeQuery(verify);
            Main.logger.log(Level.INFO, "Checking Credentials....");
            while (queryresult.next()) {
                if (queryresult.getInt(1) == 1) {

                    l_inidication.setText("Welcome!");
                    Main.logger.log(Level.INFO, "Successfully Signed In....");
                    return Boolean.TRUE;
                } else {
                    l_inidication.setText("Invalid login");
                    Main.logger.log(Level.WARNING, "Invalid Credentials");
                    return Boolean.FALSE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
     public final byte[] IV = { 0x01, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
     String stringKey = "65 12 12 12 12 12 12 12 12 12 12 12 12 12 12 12";
    public String encrypt(String input, String secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(stringToByteArray(secretKey),"AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV));
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }
    public String decrypt(String cipherText, String secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(stringToByteArray(secretKey),"AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV));
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }
    public byte[] stringToByteArray(String keyString){
        String[] keyFragments = keyString.split(" ");

        byte[] key = new byte[16];
        for (int i = 0; i < keyFragments.length; i++) {
            key[i] = Byte.parseByte(keyFragments[i]);
        }
        return key;
    }

    public void enterdatabase() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        {
            byte[] user = tf_signuser.getText().getBytes();
            byte[] pass = tf_signcurrpass.getText().getBytes();
            byte[] encryptionKey = {65, 12, 12, 12, 12, 12, 12, 12, 12,
                    12, 12, 12, 12, 12, 12, 12 };
            String suser=encrypt(Arrays.toString(user),stringKey);
            String spass=encrypt(Arrays.toString(pass),stringKey);
            Main.logger.log(Level.INFO, "Connecting Database...");
            Database connect = new Database();
            Connection connectdb = connect.getConnection();
            Main.logger.log(Level.INFO, "Database Connected...");
            String add = "INSERT INTO useraccounts(username,passwordd) VALUES('" + suser + "','" + spass + "');";
            try {
                Statement statement = connectdb.createStatement();
                statement.execute(add);
                s_indication.setText("Successfully signed up");
                Main.logger.log(Level.INFO, "Successfully signed up...");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Main.logger.log(Level.INFO, "Back Button Clicked...");
        }
    }
}
