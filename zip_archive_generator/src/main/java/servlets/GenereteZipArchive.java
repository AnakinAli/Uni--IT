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
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@WebServlet("/GenereteZipArchive")
public class GenereteZipArchive extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

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
                "attachment; filename=\"MyArchive.zip\""
        );

        try (OutputStream outputStream = response.getOutputStream()) {
            sendArchiveToStream(filesDirectory, outputStream);
        }
    }

    private void sendArchiveToStream(Path filesDirectory,
                                     OutputStream outputStream)
            throws IOException {

        try (ZipOutputStream zipArchive = new ZipOutputStream(outputStream)) {

            if (!Files.exists(filesDirectory) || !Files.isDirectory(filesDirectory)) {
                return;
            }

            List<Path> files = Files.list(filesDirectory).toList();

            for (Path file : files) {
                if (!Files.isRegularFile(file)) {
                    continue;
                }

                ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());

                zipArchive.putNextEntry(zipEntry);

                Files.copy(file, zipArchive);

                zipArchive.closeEntry();
            }
        }
    }
}