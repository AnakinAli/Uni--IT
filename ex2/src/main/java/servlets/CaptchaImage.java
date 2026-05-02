package servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@WebServlet("/CaptchaImage")
public class CaptchaImage extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException
    {

        // задаване на типа на данните,
        // изпращани към уеб браузъра
        response.setContentType("image/bmp");


        // генериране на текст от 4ри букви
        String captchaText = generateCaptchaText(4);


        // генериране на изображение от captchaText
        BufferedImage captchaImage =
                generateCaptchaImage(captchaText);

        // получаване на
        // „изходящ двоичен поток“ от response,
        // през който ще изпратим генерираното изображение
        OutputStream os = response.getOutputStream();


        // изпращане на данните към браузъра.
        // ImageIO изпраща изображението към
        // променливата os
        ImageIO.write(captchaImage, "bmp", os);
    }

        protected String generateCaptchaText(int length)
    {
        // Създаване на променлива за
        // captcha текста
        String captchaText = "";

        // Добавяне на length на брой произволни
        // букви към променливата captcha
        for (int i = 0; i < length; i++)
        {
            // генериране на произволно число в
            // интервала 65..90
            int rand = (int)(Math.random()*26)+65;

            // Преобразуване на числото в символ
            // Според ASCII 65=А, а 90=Z
            char randomChar= (char)rand;

            // Добавяне на символа към captcha текста
            captchaText += randomChar;
        }

        return captchaText;
    }
    protected Color getRandomColor()
    {
        int r = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);
        int b = (int)(Math.random()*256);

        Color clr = new Color(r,g,b);

        return clr;
    }
    protected BufferedImage generateCaptchaImage(String captcha)
    {
        // Размер на генерираното изображение
        int width = 100;
        int height = 40;


        // създаване на ново изображение
        BufferedImage img = new BufferedImage(width,
                height, BufferedImage.TYPE_INT_RGB);


        // получаване на Graphics обект, с помощта,
        // на който ще рисуваме в изображението
        Graphics g = img.createGraphics();

        // запълване на вътрешността на
        // изображението с бял цвят.
        // Оставена е рамка от 1-н пиксел по края
        g.setColor(Color.WHITE);
        g.fillRect(1, 1, width-2, height-2);

        // Нарисуване на всяка буква по различен начин
        for(int i=0; i<captcha.length() ; i++)
        {
            // Задаване на произволен цвят на рисуване
            Color clr = getRandomColor();
            g.setColor(clr);

            // Генериране на произволен размер от 15..30
            int fontSize = (int)(Math.random()*16)+15;

            // Създаване на удебелен Arial шрифт,
            // с произволна големина, в интервала
            // от 15 до 30 pt.
            Font font =
                    new Font("Arial", Font.BOLD, fontSize);

            // Задаване на новия шрифт, за текущата буква
            g.setFont(font);

            // Нарисуване на буквата върху изображението
            // на позиция x, y
            int x = 10 + 20*i;
            int y = 30;
            g.drawString(captcha.charAt(i)+"", x, y);
        }


        return img;
    }

}
