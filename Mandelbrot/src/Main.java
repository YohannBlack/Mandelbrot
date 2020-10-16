import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //Dimension of the picture and maximum iterations
        int width = 3840, height = 2160, maxIteration = 50000;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        int black = 0x000000, white = 0xFFFFFF;
        int colors[] = new int[maxIteration];
        //Calculating the colors
        for(int i = 0; i < maxIteration; i++){
            colors[i] = Color.HSBtoRGB(i/255f, 1, i/2f);
        }

        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++){
                //Mapping
                double cRe = (i - width/2f)*4f/width;
                double cIm = (j - height/2f)*4f/width;
                double a = 0f, b = 0f;

                int iterations = 0;

                do {
                    //Calculating f(f(z))
                    double z = a*a - b*b + cRe;
                    b = 2*a*b + cIm;
                    a = z;
                    iterations++;
                } while(a*a + b*b < 4 && iterations < maxIteration);

                if(iterations < maxIteration) image.setRGB(i, j, colors[iterations]);
                else image.setRGB(i, j, 0);
            }
        }
        ImageIO.write(image, "png", new File("mandelbrot.png"));
    }
}
