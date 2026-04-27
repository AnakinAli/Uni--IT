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

@WebServlet("/BookTableServlet")
public class BookTableGenerator extends HttpServlet {

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
                        "<title>Books Table</title>" +
                        "</head>" +
                        "<body>" +
                        "<h2>Книги</h2>" +
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

        InputStream inputStream = BookTableGenerator.class
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

                if (bookData.length < 4) {
                    continue;
                }

                String title = bookData[0];
                String author = bookData[1];
                String pages = bookData[2];
                String year = bookData[3];

                tableCode += "<tr>";
                tableCode += "<td>" + title + "</td>";
                tableCode += "<td>" + author + "</td>";
                tableCode += "<td>" + pages + "</td>";
                tableCode += "<td>" + year + "</td>";
                tableCode += "</tr>";
            }
        }

        tableCode += "</table>";

        return tableCode;
    }
}