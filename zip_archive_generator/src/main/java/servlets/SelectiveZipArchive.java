package servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@WebServlet("/SelectiveZipArchive")
public class SelectiveZipArchive extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String[] fileNames = request.getParameterValues("file");

        if (fileNames == null || fileNames.length == 0) {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().print("""
                    <!DOCTYPE html>
                    <html lang="bg">
                    <head>
                        <meta charset="UTF-8">
                        <title>Няма избрани файлове</title>
                    </head>
                    <body>
                        <h1>Няма избрани файлове</h1>
                        <p>Моля, изберете поне един файл.</p>
                        <a href="index.html">Назад</a>
                    </body>
                    </html>
                    """);
            return;
        }

        ServletContext context = request.getServletContext();

        String filesPath = context.getRealPath("/files");

        if (filesPath == null) {
            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Папката files не може да бъде намерена."
            );
            return;
        }

        Path filesDirectory = Path.of(filesPath);

        response.setContentType("application/zip");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=\"SelectedArchive.zip\""
        );

        try (OutputStream outputStream = response.getOutputStream();
             ZipOutputStream zipArchive = new ZipOutputStream(outputStream)) {

            addSelectedFilesToArchive(filesDirectory, fileNames, zipArchive);
        }
    }

    private void addSelectedFilesToArchive(Path filesDirectory,
                                           String[] fileNames,
                                           ZipOutputStream zipArchive)
            throws IOException {

        for (String fileName : fileNames) {
            Path filePath = filesDirectory.resolve(fileName).normalize();

            if (!filePath.startsWith(filesDirectory)) {
                continue;
            }

            if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
                continue;
            }

            ZipEntry zipEntry = new ZipEntry(filePath.getFileName().toString());

            zipArchive.putNextEntry(zipEntry);

            Files.copy(filePath, zipArchive);

            zipArchive.closeEntry();
        }
    }
}