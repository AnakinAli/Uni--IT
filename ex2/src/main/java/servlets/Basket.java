package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet("/Basket")
public class Basket extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException,IOException
    {
        // Указване на начина на кодиране на данните
        // изпратени от уеб браузъра
        request.setCharacterEncoding("UTF-8");

        // Получаване на текущата сесия
        HttpSession session = request.getSession();

        // Инициализиране на кошницата, ако това е
        // първата заявка на този клиент, към сървлета
        initBasketIfNeeded(session);

        // Получаване на кошницата, от сесията
        ArrayList<String> basket =
                (ArrayList<String>)session.getAttribute("basket");

        // Прочитане на поръчаната стока
        String orderedItem= request.getParameter("item");

        // Ако има направена поръчка,
        // тя се добавя към кошницата
        if( orderedItem != null)
            basket.add(orderedItem);

        // Генериране на HTML код, показващ
        // добавените в кошницата стоки
        String source = generateSourceCode(basket);

        // Изпращане на готовия HTML код към уеб браузъра
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(source);
    }

    protected void initBasketIfNeeded(HttpSession session)
    {
        if(session.getAttribute("basket")==null)
            session.setAttribute("basket",
                    new ArrayList<String>());
    }
    protected String generateSourceCode(
            ArrayList<String> basket)
    {
        String source =
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "<meta charset='UTF-8'>" +
                        "</head>" +
                        "<body>" +
                        "<h1>Магазин</h1>" +
                        "<hr>" +
                        "<a href='shop.html'>Магазин</a> |" +
                        "<hr> <br>" +
                        "Съдържание, на потребителската кошница: <br>";

        for (int i=0 ; i<basket.size() ; i++)
        {
            String item = basket.get(i);
            source += " - " + item + "<br>";
        }

        source +=	"</body>" +
                "</html>";

        return source;
    }

}
