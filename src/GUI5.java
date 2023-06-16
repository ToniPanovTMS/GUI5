import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GUI5 extends Canvas {
    static int width = 1000, height=1000,yСenter = 0, xСenter = 0, orbitR =400;//размеры окна начальная позиция и векторы
    static double drawX=xСenter + orbitR,drawY=yСenter;

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; //преобразуем Graphics в Graphics2D (для сглаживания)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //сглаживание
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        BufferedImage imageSan;
        BufferedImage image;
        try {
            imageSan = ImageIO.read(new File("src/san.jpg"));
            g2.drawImage(imageSan, width/2-200, height/2-200, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            image = ImageIO.read(new File("src/STR.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int imageWidth = image.getWidth(), imageHeight=image.getHeight();
        xСenter = width / 2 - imageWidth/2;
        yСenter = height / 2 - imageHeight/2;
        double count = 0;
        while (true) {
            g2.drawImage(image, (int)drawX, (int)drawY, this);
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            double orbitSpeed = Math.PI / 16;
            double radian = orbitSpeed * (count * 0.9);
            g2.clearRect((int)drawX, (int)drawY,imageWidth,imageHeight);
            drawX = xСenter + orbitR * Math.cos(radian);
            drawY = yСenter + orbitR * Math.sin(radian);
            count += 0.1;
        }
/* while (true) {
            x=0;y=R;delta = 2 - 2 * R;
            while (y >= 0) {
                g2.drawImage(image, xСenter + x, yСenter - y, this);
                g2.clearRect(xСenter + x, yСenter - y, imageWidth, imageHeight);
                error = 2 * (delta + y) - 1;
                if ((delta < 0) && (error <= 0)) {
                    delta += 2 * ++x + 1;
                    continue;
                }
                error = 2 * (delta - x) - 1;
                if ((delta > 0) && (error > 0)) {
                    delta += 1 - 2 * --y;
                    continue;
                }
                x++;
                delta += 2 * (x - y);
                y--;
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            x=0;y=R;delta = 2 - 2 * R;
            while (y >= 0) {

                g2.drawImage(image, xСenter + x, yСenter + y, this);
                g2.clearRect(xСenter + x, yСenter + y, imageWidth, imageHeight);
                error = 2 * (delta + y) - 1;
                if ((delta < 0) && (error <= 0)) {
                    delta += 2 * ++x + 1;
                    continue;
                }
                error = 2 * (delta - x) - 1;
                if ((delta > 0) && (error > 0)) {
                    delta += 1 - 2 * --y;
                    continue;
                }
                x++;
                delta += 2 * (x - y);
                y--;
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            x=0;y=R;delta = 2 - 2 * R;
            while (y >= 0) {

g2.drawImage(image, xСenter - x, yСenter + y, this);
                g2.clearRect(xСenter - x, yСenter + y, imageWidth, imageHeight);
                error = 2 * (delta + y) - 1;
                if ((delta < 0) && (error <= 0)) {
                    delta += 2 * ++x + 1;
                    continue;
                }
                error = 2 * (delta - x) - 1;
                if ((delta > 0) && (error > 0)) {
                    delta += 1 - 2 * --y;
                    continue;
                }
                x++;
                delta += 2 * (x - y);
                y--;
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            x=0;y=R;delta = 2 - 2 * R;
            while (y >= 0) {

                g2.drawImage(image, xСenter - x, yСenter - y, this);
                g2.clearRect(xСenter - x, yСenter - y, imageWidth, imageHeight);
                error = 2 * (delta + y) - 1;
                if ((delta < 0) && (error <= 0)) {
                    delta += 2 * ++x + 1;
                    continue;
                }
                error = 2 * (delta - x) - 1;
                if ((delta > 0) && (error > 0)) {
                    delta += 1 - 2 * --y;
                    continue;
                }
                x++;
                delta += 2 * (x - y);
                y--;
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
       /* BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/m_str.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int imageWidth = image.getWidth(), imageHeight=image.getHeight();
        while (true) {
            if(x>=width-imageWidth+-15||x<=0){
                vX*=-1;
            }else if(y>=height-imageHeight-5||y<=-50){
                vY*=-1;
            }
            g2.clearRect(x, y, imageWidth, imageHeight);
            g2.drawImage(image, x, y, this);
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            x+=vX;
            y+=vY;
        }
        */
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("DVD");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);

        GUI5 m = new GUI5();
        frame.add(m);
        frame.setVisible(true);
    }}