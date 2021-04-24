<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<form action="KK?akcja=ustawienia" method="post">
  Imię: <input type="text" name="imie" value="${uzytkownik.imie}"/><br/>
  Nazwisko: <input type="text" name="nazwisko" value="${uzytkownik.nazwisko}"/><br/>
  Wiek: <input type="number" name="wiek" value="${uzytkownik.wieks}"/><br/>
  <input type="submit" value="Zapisz"/>
</form>