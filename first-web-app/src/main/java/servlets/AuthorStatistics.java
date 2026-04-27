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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AuthorStatistics")
public class AuthorStatistics extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String statisticsTable = generateAuthorStatistics();

        String source =
                "<!DOCTYPE html>" +
                        "<html lang='bg'>" +
                        "<head>" +
                        "<meta charset='UTF-8'>" +
                        "<title>Статистика за автори</title>" +
                        "</head>" +
                        "<body>" +
                        "<h2>Брой книги по автори</h2>" +
                        statisticsTable +
                        "</body>" +
                        "</html>";

        out.print(source);
    }

    private String generateAuthorStatistics() throws IOException {
        Map<String, Integer> authorCounts = new HashMap<>();

        InputStream inputStream = AuthorStatistics.class
                .getClassLoader()
                .getResourceAsStream("books.txt");

        if (inputStream == null) {
            return "<p>Файлът books.txt не е намерен.</p>";
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        )) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookData = line.split("\t");
                if (bookData.length >= 2) {
                    String author = bookData[1].trim();
                    if (!author.isEmpty()) {
                        authorCounts.put(author, authorCounts.getOrDefault(author, 0) + 1);
                    }
                }
            }
        }

        if (authorCounts.isEmpty()) {
            return "<p>Няма данни за автори.</p>";
        }

        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("<table border='1' cellpadding='8' cellspacing='0'>");
        tableBuilder.append("<tr><th>Автор</th><th>Брой книги</th></tr>");

        for (Map.Entry<String, Integer> entry : authorCounts.entrySet()) {
            tableBuilder.append("<tr>");
            tableBuilder.append("<td>").append(entry.getKey()).append("</td>");
            tableBuilder.append("<td>").append(entry.getValue()).append("</td>");
            tableBuilder.append("</tr>");
        }

        tableBuilder.append("</table>");

        return tableBuilder.toString();
    }
}
