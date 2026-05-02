package servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/FileList")
public class FileList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

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

        if (!Files.exists(filesDirectory) || !Files.isDirectory(filesDirectory)) {
            response.getWriter().print("");
            return;
        }

        List<String> fileNames = Files.list(filesDirectory)
                .filter(Files::isRegularFile)
                .map(path -> path.getFileName().toString())
                .toList();

        String result = fileNames.stream()
                .collect(Collectors.joining(","));

        response.getWriter().print(result);
    }
}