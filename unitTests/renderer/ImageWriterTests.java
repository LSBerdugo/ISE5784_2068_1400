package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTests {

    @Test
    void writeToImageTest(){

    }


    @Test
    void writeToImageTest1(){

        ImageWriter imageWriter = new ImageWriter("myFirstImage",800,500);

        Color Blue=new Color( 0,0, 255);
        Color Red=new Color( 255,0, 0);
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if (i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(i, j, Blue);
                } else {
                    imageWriter.writePixel(i, j, Red);
                }
            }
        }
        imageWriter.writeToImage();
    }

}