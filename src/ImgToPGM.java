import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class ImgToPGM extends Application {

    private Window myStage;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(this.initInterface(), 600., 700));
        primaryStage.show();

    }

    private VBox initInterface() {
        VBox vbMain = new VBox();

        FileChooser fileChooser = new FileChooser();
        Text text1 = new Text("Convert to .pgm format");
        Button b = new Button("Choose image");
        ImageView imgView = new ImageView();


        b.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(myStage);
            vbMain.getChildren().addAll(getFileName(selectedFile)); // GET TEXT FOR LABEL FROM FILE NAME

            try {
                Objects.requireNonNull(convertImage(selectedFile)).saveTo(selectedFile.getName() + ".pgm"); // CONVERT CHOOSEN IMAGE TO PGM FORMAT
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        });


        vbMain.getChildren().addAll(text1, b);
        return vbMain;
    }


    private Label getFileName(File myfile) {
        return new Label(myfile.getName());
    }

    private PGMImage convertImage(File myfile) {
        try {
            FileInputStream chosenImg = new FileInputStream(myfile);
            Image myImg = new Image(chosenImg); //, 512, 512, true, false); --- COMPRESS IMAGE

            PixelReader pr = myImg.getPixelReader();
            PGMImage convertedImg = new PGMImage((int)myImg.getWidth(), (int)myImg.getHeight());

            for (int i = 0; i < myImg.getWidth(); i++) {
                for (int j = 0; j < myImg.getHeight(); j++) {
                    Color imgColor = pr.getColor(i, j);
                    double newColor = (imgColor.getRed() * 0.2126) + (imgColor.getGreen() * 0.0722) + (imgColor.getBlue() * 0.7152);
                    double gray = newColor * 255;
                    convertedImg.setPixel(i, j, (int)gray);
                }
            }
            return convertedImg;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }





}
