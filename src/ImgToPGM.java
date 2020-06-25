import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

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
        Text text2 = new Text("Convert .pgm image to .png format");
        Button b2 = new Button("Choose image");

        b.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(myStage);
            vbMain.getChildren().addAll(getFileName(selectedFile)); // GET TEXT FOR LABEL FROM FILE NAME

            try {
                Objects.requireNonNull(convertImageToPGM(selectedFile)).saveTo(selectedFile.getName() + ".pgm"); // CONVERT CHOOSEN IMAGE TO PGM FORMAT
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        b2.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(myStage);
            vbMain.getChildren().addAll(getFileName(selectedFile));

            convertPGMToPNG(selectedFile, "png"); // CONVERT PGM IMAGE TO PNG. В условии не сказано какой нужен формат. Пользователь его выбрать сам не может, но я оставил в функции поле для его ввода(не знаю зачем).
            //В наилучшем варианте нужно добавить ввод формата для пользователя, но я очень устал.
        });


        vbMain.getChildren().addAll(text1, b, text2, b2);
        return vbMain;
    }


    private Label getFileName(File myfile) {
        return new Label(myfile.getName());
    }

    private PGMImage convertImageToPGM(File myfile) {
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

    private void convertPGMToPNG(File myfile, String format) {
        try {
            Path path = Path.of(myfile.getName());
            Scanner sc = new Scanner(path, StandardCharsets.UTF_8);

            sc.next();
            int width = Integer.parseInt(sc.next());
            int height = Integer.parseInt(sc.next());
            int grayBr = Integer.parseInt(sc.next());

            WritableImage newImage = new WritableImage(width, height);
            PixelWriter pw = newImage.getPixelWriter();

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    pw.setColor(j, i, Color.gray((double)Integer.parseInt(sc.next())/grayBr));
                }
            }

            File out = new File(String.valueOf("output.png"));
            BufferedImage bimg = SwingFXUtils.fromFXImage(newImage, null);
            ImageIO.write(bimg, format, out);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



}
