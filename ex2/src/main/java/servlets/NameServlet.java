package servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/NameServlet")
public class NameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException,
            IOException
    {
        // Задаване  на типа на данните, изпращани
        // от сървлета към браузъра
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");


        // Прочитане наимето, изпратено от формата
        String name = request.getParameter("ime");

        // Получаване на „изходящ текстов поток”
        PrintWriter out = response.getWriter();

        // Създаване на началото на html кода,
        // който ще бъде изпратен на браузъра
        String source = "<!DOCTYPE html>"+
                "<html>" +
                "<head><meta charset='UTF-8'></head>" +
                "<body>";

        // Проверка дали променливата name има стойност
        if(name!=null && name.length()>0)
        {
            // Преминаване през всички букви от името
            for(int i=0 ; i<name.length() ; i++)
            {
                // Генериране на число от 15 до 55,
                // за размера на буквата
                int size = (int)(Math.random()*41)+15;

                // Получаване на произволен цвят
                String color = getRandomColor();

                // Вземане на поредната буква от името
                char leter = name.charAt(i);

                // Генериране на html код.
                // „Обличаме” буквата в CSS стил
                source += 	"<span style='color:" + color +
                        "; font-size:" + size + "pt;'>"+
                        leter + "</span>";
            }
        }
        else
        {
            // Ако променливата name няма стойност
            // Добавяме съобщение за грешка
            source += "Грешка. Заредете html файла.";
        }

        // Добавяне на затварящи етикети в края на
        // генерирания html код
        source += "</body></html>";

        // Изпращане на генерираният HTML код към браузъра
        out.print(source);
    }

    protected String getRandomColor()
    {
        String digits = "0123456789ABCDEF";
        String color = "#";
        for(int i=0;i<6;i++)
        {
            int randomPosition = (int)(Math.random()*16);
            color += digits.charAt(randomPosition);
        }

        return color;
    }

}
