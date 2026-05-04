package servlets;

import dao.Database;
import entities.Vehicle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddVehicleServlet")
public class AddVehicleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String brandAndModel = request.getParameter("brandAndModel");
        int passengers = Integer.parseInt(request.getParameter("passengers"));
        int productionYear = Integer.parseInt(request.getParameter("productionYear"));
        double fuelConsumption = Double.parseDouble(request.getParameter("fuelConsumption"));
        String color = request.getParameter("color");
        String gearboxType = request.getParameter("gearboxType");

        Vehicle vehicle = new Vehicle();
        vehicle.setBrandAndModel(brandAndModel);
        vehicle.setPassengers(passengers);
        vehicle.setProductionYear(productionYear);
        vehicle.setFuelConsumption(fuelConsumption);
        vehicle.setColor(color);
        vehicle.setGearboxType(gearboxType);

        Database.addVehicle(vehicle);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='bg'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Добавено превозно средство</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Превозното средство е добавено успешно!</h1>");

        out.println("<p><b>ID:</b> " + vehicle.getId() + "</p>");
        out.println("<p><b>Марка и модел:</b> " + vehicle.getBrandAndModel() + "</p>");
        out.println("<p><b>Брой пътници:</b> " + vehicle.getPassengers() + "</p>");
        out.println("<p><b>Година:</b> " + vehicle.getProductionYear() + "</p>");
        out.println("<p><b>Разход:</b> " + vehicle.getFuelConsumption() + "</p>");
        out.println("<p><b>Цвят:</b> " + vehicle.getColor() + "</p>");
        out.println("<p><b>Скоростна кутия:</b> " + vehicle.getGearboxType() + "</p>");

        out.println("<hr>");
        out.println("<a href='index.html'>Добави друго превозно средство</a>");
        out.println("<br>");
        out.println("<a href='webapi/vehicles'>Виж всички като JSON</a>");

        out.println("</body>");
        out.println("</html>");
    }
}