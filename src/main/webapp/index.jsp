<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css" type="text/css" />
    <title>TI-Lab3 Sugestie</title>
    <script type="text/javascript">

        function wyslijAsynchronicznie(url, funkcjeZwrotne, metoda, wysylaneDane, typDanych) {
            metoda = metoda || "GET";
            wysylaneDane = wysylaneDane || null;
            typDanych = typDanych || "text/plain";

            if(!window.XMLHttpRequest) {
                return null;
            }

            var requester = new XMLHttpRequest();
            requester.open(metoda, url);
            requester.setRequestHeader("Content-Type", typDanych);

            requester.onreadystatechange = function () {
                el = document.getElementById('wyniki');
                el.style.display = 'block';
                el.innerHTML = "Ładowanie...";

                if(requester.readyState === 4) {
                    if(requester.status === 200){
                        funkcjeZwrotne.success(requester);
                    } else {
                        funkcjeZwrotne.failure(requester);
                    }
                }
            }

            requester.send(wysylaneDane);
            return requester;
        }

        function pobierzSugestie() {
            var wartosc = { wartosc: document.getElementById("pole").value };
            var wynik = {
                success: function (requester) {
                    var rezultat = "";
                    var odpowiedz = JSON.parse(requester.responseText);

                    for(var i=0;i<odpowiedz.sugestia.length;i++) {
                        rezultat += "<div class='lista'>" + odpowiedz.sugestia[i] + "</div>";
                    }
                    el.innerHTML = rezultat;
                },
                failure: function (requester) {
                    alert("Wystąpił błąd: " + requester.status);
                }
            };
            var typDanych = "application/json";
            wyslijAsynchronicznie('sugestieJSON', wynik, 'POST', JSON.stringify(wartosc), typDanych);
        }

    </script>
</head>
<body>
<h4>Wpisz markę samochodu</h4>
<input id="pole" type="text" onkeyup="javascript:pobierzSugestie();" />
<div id="wyniki"></div>
</body>
</html>