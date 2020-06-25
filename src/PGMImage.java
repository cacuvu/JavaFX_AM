import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.rmi.server.ExportException;

public class PGMImage {

    private int[][] pixels;
    int width;
    int height;

    public PGMImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width][height];
    }

    public void setPixel(int x, int y, int color) {
        this.pixels[x][y] = color;
    }

    public void saveTo(String filename) throws Exception {
        PrintStream outImage = new PrintStream(filename, StandardCharsets.UTF_8);

        outImage.println("P2");
        outImage.println("" + this.width + " " + this.height);
        outImage.println("255");

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                outImage.print("" + this.pixels[j][i] + " ");
            }
        }

        outImage.close();
    }



}
