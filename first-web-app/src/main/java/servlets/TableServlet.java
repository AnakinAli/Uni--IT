package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/TableServlet")
public class TableServlet extends HttpServlet {

    public static final int MAX_ROWS = 20;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String source =
                "<!DOCTYPE html>" +
                        "<html lang='bg'>" +
                        "<head>" +
                        "    <meta charset='UTF-8'>" +
                        "    <title>Random Table</title>" +
                        "</head>" +
                        "<body>" +
                        "    <h2>Random Colored Table</h2>" +
                        "    <table border='1' cellpadding='10' cellspacing='0'>";

        Random rand = new Random();
        int rows = rand.nextInt(MAX_ROWS + 1);

        for (int i = 0; i < rows; i++) {
            source +=
                    "<tr>" +
                            "<td style='background-color:" + getRandomColor() + "'>test</td>" +
                            "<td style='background-color:" + getRandomColor() + "'>test</td>" +
                            "</tr>";
        }

        source +=
                "    </table>" +
                        "</body>" +
                        "</html>";

        out.print(source);
    }

    protected String getRandomColor() {
        String digits = "0123456789ABCDEF";
        String color = "#";

        for (int i = 0; i < 6; i++) {
            int randomPosition = (int) (Math.random() * 16);
            color += digits.charAt(randomPosition);
        }

        return color;
    }
}