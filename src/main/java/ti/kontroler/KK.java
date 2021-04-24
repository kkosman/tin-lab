package ti.kontroler;

import ti.Narzedzia;
import ti.model.KKuzytkownik;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "KK", value = "/KK")
public class KK extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        ServletContext aplikacja = getServletContext();
        HttpSession sesja = request.getSession();

        KKuzytkownik uzytkownik = (KKuzytkownik) sesja.getAttribute("uzytkownik");
        if(uzytkownik == null) {
            uzytkownik = new KKuzytkownik();
            sesja.setAttribute("uzytkownik", uzytkownik);
        }
        String kolorTla = (String) aplikacja.getAttribute("kolorTla");
        if(kolorTla == null) kolorTla = "white";
        HashMap<String,Integer> listaUzytkownikow = (HashMap<String,Integer>) aplikacja.getAttribute("listaUzytkownikow");
        if(listaUzytkownikow == null) {
            listaUzytkownikow = new HashMap<>();
            listaUzytkownikow.put("admin", 2);
            listaUzytkownikow.put("user1", 1);
            listaUzytkownikow.put("user2", 0);
            listaUzytkownikow.put("user3", 0);
            listaUzytkownikow.put("user4", 0);
            aplikacja.setAttribute("listaUzytkownikow", listaUzytkownikow);
        }

        String strona = request.getParameter("strona");
        if(uzytkownik.getUprawnienia() == 0) {
            strona = Narzedzia.filtrujStrone(strona, "glowna;kwadratowe;link3");
        } else if(uzytkownik.getUprawnienia() == 1) {
            strona = Narzedzia.filtrujStrone(strona, "glowna;kwadratowe;link3;ustawienia");
        } else if(uzytkownik.getUprawnienia() == 2) {
            strona = Narzedzia.filtrujStrone(strona, "glowna;kwadratowe;link3;ustawienia;administracja");
        } else {
            strona = Narzedzia.filtrujStrone(strona, "glowna;kwadratowe;link3");
        }

        String szablon = Narzedzia.pobierzSzablon("index.html", aplikacja);
        szablon = Narzedzia.uzupelnij(szablon, "NAGLOWEK", "naglowek", aplikacja);
        if(uzytkownik.getUprawnienia() == 0) {
            szablon = Narzedzia.uzupelnij(szablon, "MENU", "menu0", aplikacja);
            szablon = szablon.replace("[[LOGIN]]", uzytkownik.getLogin());
        } else if(uzytkownik.getUprawnienia() == 1) {
            szablon = Narzedzia.uzupelnij(szablon, "MENU", "menu2", aplikacja);
            szablon = szablon.replace("[[LOGIN]]", uzytkownik.getLogin());
        } else if(uzytkownik.getUprawnienia() == 2) {
            szablon = Narzedzia.uzupelnij(szablon, "MENU", "menu3", aplikacja);
            szablon = szablon.replace("[[LOGIN]]", uzytkownik.getLogin());
        } else {
            szablon = Narzedzia.uzupelnij(szablon, "MENU", "menu", aplikacja);
        }
        szablon = Narzedzia.uzupelnij(szablon, "TRESC", strona, aplikacja);
        szablon = Narzedzia.uzupelnij(szablon, "STOPKA", "stopka", aplikacja);

        if(strona.equals("ustawienia")) {
            szablon = szablon.replace("[[IMIE]]", uzytkownik.getImie());
            szablon = szablon.replace("[[NAZWISKO]]", uzytkownik.getNazwisko());
            if (uzytkownik.getWiek() != -1) {
                szablon = szablon.replace("[[WIEK]]", "" + uzytkownik.getWiek());
            } else {
                szablon = szablon.replace("[[WIEK]]", "");
            }
        }

        szablon = szablon.replace("[[KOLORTLA]]", kolorTla);
        szablon = szablon.replace("[[ADMIN]]", ""+listaUzytkownikow.get("admin"));
        szablon = szablon.replace("[[USER1]]", ""+listaUzytkownikow.get("user1"));
        szablon = szablon.replace("[[USER2]]", ""+listaUzytkownikow.get("user2"));
        szablon = szablon.replace("[[USER3]]", ""+listaUzytkownikow.get("user3"));
        szablon = szablon.replace("[[USER4]]", ""+listaUzytkownikow.get("user4"));

        out.println(szablon);
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        ServletContext aplikacja = getServletContext();
        HttpSession sesja = request.getSession();

        KKuzytkownik uzytkownik = (KKuzytkownik) sesja.getAttribute("uzytkownik");
        if(uzytkownik == null) {
            uzytkownik = new KKuzytkownik();
            sesja.setAttribute("uzytkownik", uzytkownik);
        }
        String kolorTla = (String) aplikacja.getAttribute("kolorTla");
        if(kolorTla == null) kolorTla = "white";
        HashMap<String,Integer> listaUzytkownikow = (HashMap<String,Integer>) aplikacja.getAttribute("listaUzytkownikow");


        String komunikat = "Niepoprawny login i hasło";

        String akcja = request.getParameter("akcja");
        if(akcja == null) akcja = "";

        if(akcja.equals("zaloguj")) {

            String login = request.getParameter("login");
            String haslo = request.getParameter("haslo");

            if(login == null) login = "";
            if(haslo == null) haslo = "";

            if(listaUzytkownikow.containsKey(login) & login.equals(haslo)) {
                uzytkownik.setLogin(login);
                uzytkownik.setUprawnienia(listaUzytkownikow.get(login));
                sesja.setAttribute("uzytkownik", uzytkownik);
                if(uzytkownik.getUprawnienia() == 0) {
                    komunikat = "Zostałeś zalogowany jako <b>" + login + "</b> (nieaktywny)";
                } else if (uzytkownik.getUprawnienia() == 2) {
                    komunikat = "Zostałeś zalogowany jako <b>" + login + "</b> (administrator)";
                } else {
                    komunikat = "Zostałeś zalogowany jako <b>" + login + "</b>";
                }
            }

        } else if (akcja.equals("wyloguj")) {
            uzytkownik = new KKuzytkownik();
            sesja.setAttribute("uzytkownik", uzytkownik);

            komunikat = "Zostałeś prawidłowo wylogowany";
        } else if (akcja.equals("ustawienia")) {
            String imie = request.getParameter("imie");
            String nazwisko = request.getParameter("nazwisko");
            if(imie == null) imie = "";
            if(nazwisko == null) nazwisko = "";

            int wiek = Narzedzia.parsujInteger(request.getParameter("wiek"),-1);

            uzytkownik.setImie(imie);
            uzytkownik.setNazwisko(nazwisko);
            uzytkownik.setWiek(wiek);

            sesja.setAttribute("uzytkownik", uzytkownik);
            komunikat = "Ustawienia zostały prawidłowo zapisane.";
        } else if (akcja.equals("administracja")) {
            if(uzytkownik.getUprawnienia() == 2) {
                String kolor = request.getParameter("kolor");
                if (kolor == null) kolor = "white";

                aplikacja.setAttribute("kolorTla", kolor);
                kolorTla = kolor;

                komunikat = "Kolor tła został ustawiony";
            } else {
                komunikat = "Brak uprawnień do zmiany koloru tła";
            }
        } else if (akcja.equals("uprawnienia")) {
            if(uzytkownik.getUprawnienia() == 2) {
                String user = request.getParameter("uzytkownik");
                String uprawnienia = request.getParameter("uprawnienia");
                if (user == null | uprawnienia == null){
                    komunikat = "Niepoprawne parametry";
                } else {
                    listaUzytkownikow.replace(user,Narzedzia.parsujInteger(uprawnienia,-1));
                    aplikacja.setAttribute("listaUzytkownikow", listaUzytkownikow);
                    komunikat = "Zmieniono uprawnienia użytkownika: " + user;
                }
            } else {
                komunikat = "Brak uprawnień do zmiany koloru tła";
            }
        } else {
            komunikat = "Nieprawidłowe wywołanie";
        }


        String szablon = Narzedzia.pobierzSzablon("komunikat.html", aplikacja);
        szablon = szablon.replace("[[TRESC]]", komunikat);
        szablon = szablon.replace("[[KOLORTLA]]", kolorTla);

        out.println(szablon);
        out.close();
    }
}
