package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@WebServlet("/NewBooks")
public class NewBooks extends HttpServlet {

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
                        "<meta charset='UTF-8'>" +
                        "<title>Нови книги</title>" +
                        "</head>" +
                        "<body>" +
                        "<h2>Книги от последните 2 години</h2>" +
                        generateTableFromFile() +
                        "</body>" +
                        "</html>";

        out.print(source);
    }

    protected String generateTableFromFile() throws IOException {
        String tableCode =
                "<table border='1' cellpadding='8' cellspacing='0'>" +
                        "<tr>" +
                        "<th>Заглавие</th>" +
                        "<th>Автор</th>" +
                        "<th>Страници</th>" +
                        "<th>Година</th>" +
                        "</tr>";

        InputStream inputStream = NewBooks.class
                .getClassLoader()
                .getResourceAsStream("books.txt");

        if (inputStream == null) {
            return "<p>Файлът books.txt не е намерен.</p>";
        }

        int currentYear = LocalDate.now().getYear();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        )) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] bookData = line.split("\t");

                if (bookData.length < 4) {
                    continue;
                }

                String title = bookData[0];
                String author = bookData[1];
                String pages = bookData[2];
                String yearStr = bookData[3];

                try {
                    int year = Integer.parseInt(yearStr.trim());
                if (year >= currentYear - 2) {
                        tableCode += "<tr>";
                        tableCode += "<td>" + title + "</td>";
                        tableCode += "<td>" + author + "</td>";
                        tableCode += "<td>" + pages + "</td>";
                        tableCode += "<td>" + year + "</td>";
                        tableCode += "</tr>";
                    }
                } catch (NumberFormatException e) {
                    // Игнорираме редове с невалидна година
                }
            }
        }

        tableCode += "</table>";

        return tableCode;
    }
}
