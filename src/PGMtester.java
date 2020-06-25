public class PGMtester {

    public static void main(String[] args) throws Exception {
        PGMImage myImage = new PGMImage(80, 60);
        PGMImage myImage2 = new PGMImage(80, 60);

        for (int i = 0; i < myImage.height; i++) {
            for (int j = 0; j < myImage.width; j++) {
                myImage.setPixel(j, i, ((int)(Math.random() * 256)));
            }
        }

        myImage.saveTo("testImage1.pgm");

        for (int i = 0; i < myImage2.height; i++) {
            for (int j = 0; j < myImage2.width; j++) {
                myImage2.setPixel(j, i, (j + i) % 256);
            }
        }

        myImage2.saveTo("testImage2.pgm");

    }

}
