<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<form action="KK?akcja=administracja" method="post">
  Kolor tła: <input type="text" name="kolor" value="${applicationScope.kolorTla}"/><br/>
  <input type="submit" value="Zapisz"/><br/>
</form>
<form action="KK?akcja=uprawnienia" method="post">
  <input type="hidden" name="uzytkownik" value="admin">
  <label for="uprawnienia">admin:</label>
  <select name="uprawnienia" id="uprawnienia" data-selected="${applicationScope.listaUzytkownikow['admin']}">
    <option value="0">Nieaktywny</option>
    <option value="1">Aktywny</option>
    <option value="2">Administrator</option>
  </select>
  <input type="submit" value="Nadaj uprawnienia"/><br/>
</form>
<form action="KK?akcja=uprawnienia" method="post">
  <input type="hidden" name="uzytkownik" value="user1">
  <label for="uprawnienia">user1:</label>
  <select name="uprawnienia" id="uprawnienia" data-selected="${applicationScope.listaUzytkownikow['user1']}">
    <option value="0">Nieaktywny</option>
    <option value="1">Aktywny</option>
    <option value="2">Administrator</option>
  </select>
  <input type="submit" value="Nadaj uprawnienia"/><br/>
</form>
<form action="KK?akcja=uprawnienia" method="post">
  <input type="hidden" name="uzytkownik" value="user2">
  <label for="uprawnienia">user2:</label>
  <select name="uprawnienia" id="uprawnienia" data-selected="${applicationScope.listaUzytkownikow['user2']}">
    <option value="0">Nieaktywny</option>
    <option value="1">Aktywny</option>
    <option value="2">Administrator</option>
  </select>
  <input type="submit" value="Nadaj uprawnienia"/><br/>
</form>
<form action="KK?akcja=uprawnienia" method="post">
  <input type="hidden" name="uzytkownik" value="user3">
  <label for="uprawnienia">user3:</label>
  <select name="uprawnienia" id="uprawnienia" data-selected="${applicationScope.listaUzytkownikow['user3']}">
    <option value="0">Nieaktywny</option>
    <option value="1">Aktywny</option>
    <option value="2">Administrator</option>
  </select>
  <input type="submit" value="Nadaj uprawnienia"/><br/>
</form>
<form action="KK?akcja=uprawnienia" method="post">
  <input type="hidden" name="uzytkownik" value="user4">
  <label for="uprawnienia">user4:</label>
  <select name="uprawnienia" id="uprawnienia" data-selected="${applicationScope.listaUzytkownikow['user4']}">
    <option value="0">Nieaktywny</option>
    <option value="1">Aktywny</option>
    <option value="2">Administrator</option>
  </select>
  <input type="submit" value="Nadaj uprawnienia"/><br/>
</form>
<script type="application/javascript">
  (function(){
    var selects = document.getElementsByTagName("select");
    for(var i = 0; i < selects.length ; i++) {
      if(selects[i].getAttribute('data-selected')) {
        selects[i].value = selects[i].getAttribute('data-selected');
      }
    }
  })();
</script>