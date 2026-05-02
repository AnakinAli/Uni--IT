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
@WebServlet("/Login")
public class Login extends HttpServlet{
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException
    {
        // Прочитане на потребителското име и паролата,
        // изпратени от html формата
        String userName= request.getParameter("userName");
        String password= request.getParameter("password");

        // Проверка за валидни потребител и парола
        if( userName.equals("admin") &&
                password.equals("123"))
        {
            // Получаване на потребителската сесия
            HttpSession session = request.getSession();

            // Запис на променливата user със стойност
            // admin в потребителската сесия
            session.setAttribute("user", "admin");

            // Показване на съобщение в браузъра
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            response.getWriter().print(
                    "Влязохте успешно");
        }
        else
        {
            // Показване на съобщение в браузъра
            // при невалидни потребител и парола
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            response.getWriter().print(
                    "Невалидно потребителско име или парола");
        }
    }

}