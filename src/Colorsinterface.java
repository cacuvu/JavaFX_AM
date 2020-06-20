import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Colorsinterface extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(this.initInterface(), 650.0D, 712.0D));
        primaryStage.show();
    }


    private VBox initInterface() {
        VBox vbMain = new VBox();
        HBox hbTop = new HBox();
        HBox hbBot = new HBox();

        // ---- IMAGE 1
        ImageView imgView1 = new ImageView();
        WritableImage img1 = new WritableImage(120,100);
        PixelWriter pw1 = img1.getPixelWriter();

        for (int x = 0; x < 120; x++) {
            for (int y = 0; y < 100; y++) {
                pw1.setColor(x, y, Color.GREEN);
            }
        }
        imgView1.setImage(img1);
        // ---- IMAGE 1

        // ---- IMAGE 2
        ImageView imgView2 = new ImageView();
        WritableImage img2 = new WritableImage(256, 256);
        PixelWriter pw2 = img2.getPixelWriter();

        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                pw2.setColor(x, y, Color.rgb(0, x, y));
            }
        }
        imgView2.setImage(img2);
        // ---- IMAGE 2


        // ---- IMAGE 3
        ImageView imgView3 = new ImageView();
        WritableImage img3 = new WritableImage(256, 256);
        PixelWriter pw3 = img3.getPixelWriter();

        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                pw3.setColor(x, y, Color.rgb(x, x, y));
            }
        }
        imgView3.setImage(img3);
        // ---- IMAGE 3

        // ---- IMAGE 4
        ImageView imgView4 = new ImageView();
        WritableImage img4 = new WritableImage(360, 100);
        PixelWriter pw4 = img4.getPixelWriter();

        for (int x = 0; x < 360; x++) {
            for (int y = 0; y < 100; y++) {
                pw4.setColor(x, y, Color.hsb(x, (float)y / 99, 1));
            }
        }
        imgView4.setImage(img4);
        // ---- IMAGE 4

        // ---- IMAGE 5
        ImageView imgView5 = new ImageView();
        WritableImage img5 = new WritableImage(360, 100);
        PixelWriter pw5 = img5.getPixelWriter();

        for (int x = 0; x < 360; x++) {
            for (int y = 0; y < 100; y++) {
                pw5.setColor(x, y, LCH.colorFromLCH(80, y, x));
            }
        }
        imgView5.setImage(img5);
        // ---- IMAGE 5

        // ---- IMAGE 6
        ImageView imgView6 = new ImageView();
        Image img6 = new Image("img1.png");
        imgView6.setImage(img6);
        // ---- IMAGE 6

        // ---- IMAGE 7
        ImageView imgView7 = new ImageView();
        PixelReader pr7 = img6.getPixelReader();
        WritableImage img7 = new WritableImage(256, 256);
        PixelWriter pw7 = img7.getPixelWriter();

        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                Color pixelColor = pr7.getColor(x, y);
                double red = (pixelColor.getRed() * 0.2126);
                double green = (pixelColor.getGreen() * 0.0722);
                double blue = (pixelColor.getBlue() * 0.7152);
                double newColor = red + green + blue;
                //pw7.setColor(x, y, Color.gray(newColor)); Данный метод возвращает более красивый черно-белый эффект. В задание не совсем понятно какой именно нужно было использовать, по-этому я оставил этот за комментированным.
                pw7.setColor(x, y, Color.rgb(128, 128, 128, newColor));
            }
        }
        imgView7.setImage(img7);
        // ---- IMAGE 7


        hbTop.getChildren().addAll(imgView1, imgView2, imgView3);
        hbBot.getChildren().addAll(imgView6, imgView7);
        vbMain.getChildren().addAll(hbTop, imgView4, imgView5, hbBot);

        return vbMain;
    }
}
