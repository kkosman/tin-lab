<%@ page import="ti.Narzedzia" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<!DOCTYPE html>
<html style="background-color: ${ (applicationScope.kolorTla != null) ? applicationScope.kolorTla : 'white' }">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1"/>
    <title>TI-Lab1</title>
    <link rel="stylesheet" type="text/css" href="styl.css"/>
    <script type="text/javascript" src="skrypt.js"></script>
    <script type="text/javascript" src="kwadratowe.js"></script>
</head>
<jsp:useBean id="uzytkownik" class="ti.model.KKuzytkownik" scope="session"/>
<%
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


    HashMap<String,Integer> listaUzytkownikow = (HashMap<String,Integer>) application.getAttribute("listaUzytkownikow");
    if(listaUzytkownikow == null) {
        listaUzytkownikow = new HashMap<>();
        listaUzytkownikow.put("admin", 2);
        listaUzytkownikow.put("user1", 1);
        listaUzytkownikow.put("user2", 0);
        listaUzytkownikow.put("user3", 0);
        listaUzytkownikow.put("user4", 0);
        application.setAttribute("listaUzytkownikow", listaUzytkownikow);
    }
%>

<body onload="funkcje();zegarek();setInterval(zegarek, 1000);">
<div id="kontener">
    <div id="naglowek">
        <jsp:include page="/WEB-INF/widok/naglowek.jsp"/>
    </div>
    <div id="srodek">
        <div id="menu">
            <jsp:include page="/WEB-INF/widok/menu.jsp"/>
        </div>
        <div id="tresc">
            <jsp:include page="/WEB-INF/widok/tresc.jsp">
                <jsp:param name="jaka_strona" value="<%=strona%>"/>
            </jsp:include>
        </div>
    </div>
    <div id="stopka">
        <jsp:include page="/WEB-INF/widok/stopka.html"/>
    </div>
</div>
</body>
</html>
