package ti;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "sugestieBackend", value = "/sugestieBackend")
public class sugestieBackend extends HttpServlet {

    String[] lista = { "ALFA ROMEO", "AUDI", "BMW", "CHRYSLER", "CITROEN", "DAIHATSU", "FIAT", "FORD", "HONDA", "ISUZU", "JAGUAR", "LADA", "LANCIA", "MAZDA", "MERCEDES", "MITSUBISHI", "NISSAN", "OPEL", "PEUGEOT", "PORSCHE", "RENAULT", "ROVER", "SAAB", "SEAT", "SKODA", "SUBARU", "SUZUKI", "TOYOTA", "VOLVO", "VW" };


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String query = ""+request.getParameter("wartosc");
        PrintWriter out = response.getWriter();

        for(String samochod: lista) {
            if(samochod.startsWith(query)) {
                out.print("<div class='lista'>"+samochod+"</div>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
