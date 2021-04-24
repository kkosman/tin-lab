<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<jsp:useBean id="uzytkownik" class="ti.model.KKuzytkownik" scope="session"/>
<div id="menuToggle">
    <input type="checkbox" />
    <span></span>
    <span></span>
    <span></span>
    <ul>
        <li><a href="KK.jsp?strona=glowna">Strona główna</a></li>
        <li><a href="KK.jsp?strona=kwadratowe">ax<sup>2</sup>+bx+c=0</a></li>
        <li><a href="KK.jsp?strona=link3">Link3</a></li>
        ${ (uzytkownik.uprawnienia > 0) ? '<li><a href="KK.jsp?strona=ustawienia">Ustawienia</a></li>' : '' }
        <% if (uzytkownik.getUprawnienia() == 2) { %>
        <li><a href="KK.jsp?strona=administracja">Administracja</a></li>
        <% } %>
    </ul>

</div>
<% if  (uzytkownik.getUprawnienia() < 0) { %>
<form action="KK?akcja=zaloguj" method="post">
    Login: <input type="text" name="login" /><br/>
    Hasło: <input type="text" name="haslo" /><br/>
    <input type="submit" value="Zaloguj" /><br/>
</form>
<% } else { %>
<form action="KK?akcja=wyloguj" method="post">
    Jesteś zalogowany jako administrator: <b>${uzytkownik.login}</b>
    <input type="submit" value="Wyloguj" /><br/>
</form>
<% } %>
<div id="newsy">
    <p id="news1"></p>
    <p id="news2"></p>
</div>