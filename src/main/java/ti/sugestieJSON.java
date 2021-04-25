package ti;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

@WebServlet(name = "sugestieJSON", value = "/sugestieJSON")
public class sugestieJSON extends HttpServlet {

    String[] lista = { "ALFA ROMEO", "AUDI", "BMW", "CHRYSLER", "CITROEN", "DAIHATSU", "FIAT", "FORD", "HONDA", "ISUZU", "JAGUAR", "LADA", "LANCIA", "MAZDA", "MERCEDES", "MITSUBISHI", "NISSAN", "OPEL", "PEUGEOT", "PORSCHE", "RENAULT", "ROVER", "SAAB", "SEAT", "SKODA", "SUBARU", "SUZUKI", "TOYOTA", "VOLVO", "VW" };

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String jsonText = "";
        if (br != null) {
            jsonText = br.readLine();
        }

        JSONObject json = new JSONObject();
        JSONParser jsonParser = new JSONParser();

        if (jsonText != null & !(jsonText.isEmpty())){
            try {
                json = (JSONObject) jsonParser.parse(jsonText);
            }
            catch(org.json.simple.parser.ParseException e) {
                e.printStackTrace();
                json = new JSONObject();
            }
        }

        String query = (String) json.get("wartosc");

        PrintWriter out = response.getWriter();

        ArrayList<String> sugestie = new ArrayList<>();
        for(String samochod: lista) {

            Pattern pattern = Pattern.compile("^"+query+".*", Pattern.CASE_INSENSITIVE);
            if(pattern.matcher(samochod).find()) {
                sugestie.add(samochod);
            }
        }

        json.put("sugestia", sugestie);
        out.print(json.toJSONString());
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
