package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/CaptchaImage")
public class LastVisited extends HttpServlet {
    public static String LAST_VISITED_COOKIE = "lastVisited";

    public static int ONE_MINUTE = 60;   // 60 секунди
    public static int ONE_HOUR = 60 * ONE_MINUTE;
    public static int ONE_DAY = 24 * ONE_HOUR;
    public static int ONE_MONTH = 30 * ONE_DAY;
    public static int ONE_YEAR = 12 * ONE_MONTH;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        // Задаване на типа на връщаните от сървлета данни
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Получаване на "изходящ текстов поток"
        PrintWriter out = response.getWriter();


        // Получаване на времето на последното посещение
        // или null, ако е нямало такова
        String lastVisitedData =
                getLastVisitedCookie(request);


        // Създаване на началото на HTML кода
        String source = "<!DOCTYPE html>" +
                "<html>" +
                "<head><meta charset='UTF-8'></head>" +
                "<body>" +
                "<h1> Моят уеб сайт</h1> " +
                "<hr><br>" +
                "Последно посещение на: ";

        if (lastVisitedData != null) {
            // Добавяне на времето на последното
            // посещение към HTML кода
            source += lastVisitedData;
        }

        // Добавяне на затварящите етикети, на HTML кода
        source += "</body></html>";

        // Задаване на нова стойност, на бисквитката,
        // пазеща датата на последнотото посещение
        setLastVisitedCookie(response);

        // Изпращане на генерирания
        // HTML код към уеб браузъра
        out.println(source);
    }

    protected void setLastVisitedCookie(
            HttpServletResponse response) {
        // Получаване на текущата дата и време
        LocalDateTime currentDate = LocalDateTime.now();

        // Създаване на форматиращ обект
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy_в_HH:mm:ss");

        // Получаване на символен низ,
        // на базата на форматиращия обект
        String now = currentDate.format(formatter);

        // Създаване на бисквитката
        Cookie c = new Cookie(LAST_VISITED_COOKIE, now);

        // Задаване на продължителността
        // на живот на бисквитката
        c.setMaxAge(ONE_MONTH);

        // Изпращане на бисквитката към уеб браузъра
        response.addCookie(c);
    }

    protected String getLastVisitedCookie(
            HttpServletRequest request) {
        // Получаване на всички бисквитки,
        // изпратени от уеб браузъра
        Cookie[] cookies = request.getCookies();

        // Преминаване през всички получени бисквитки
        if (cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                // Получаване на поредната бисквитка
                Cookie c = cookies[i];

                // Проверка дали поредната бисквитка е тази,
                // която указва времето на последното посещение
                if (c.getName().equals(LAST_VISITED_COOKIE)) {
                    // Връщане на времето на последното
                    // посещение на уеб сайта
                    return c.getValue();
                }
            }


        // Ако браузърът не е изпратил бисквитки или не сме
        // намерили тази, указваща времето на последното
        // посещение, връщаме null
        return null;
    }

}
