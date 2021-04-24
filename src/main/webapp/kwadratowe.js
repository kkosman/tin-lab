
kwadratowe = function(a,b,c) {
    this.A = a;
    this.B = b;
    this.C = c;
    this.delta = null;
    this.x1 = null;
    this.x2 = null;
}

kwadratowe.prototype.liczDelta = function () {
    this.delta = (this.B * this.B) - 4 * this.A * this.C;
    return this.delta;
}

kwadratowe.prototype.miejscaZerowe = function () {
    var d = this.liczDelta();

    if( d > 0 ) {
        this.x1 = ((this.B * -1) - Math.sqrt(d)) / (2 * this.A);
        this.x2 = ((this.B * -1) + Math.sqrt(d)) / (2 * this.A);
    } else if (d == 0) {
        this.x1 = (this.B * -1) / (2 * this.A);
        this.x2 = null;
    } else {
        this.x1 = null;
        this.x2 = null;
    }
}

kwadratowe.prototype.getX1 = function () {
    return this.x1;
}
kwadratowe.prototype.getX2 = function () {
    return this.x2;
}
kwadratowe.prototype.getDelta = function () {
    return this.delta;
}

kwadratowe.prototype.waliduj = function () {
    if(this.A == NaN || this.A == null || !this.A || this.A == 0 )
        return false;
    if(this.B == NaN || this.B == null || !this.B )
        return false;
    if(this.C == NaN || this.C == null || !this.C)
        return false;

    return true;
}

kwadratowe.prototype.licz = function() {
    if( this.waliduj() ) {
        this.miejscaZerowe();
    } else {
        alert("Nie podane prawidłowych danych");
    }
}

function podpiecie() {
    var przycisk = document.getElementById("licz")
    przycisk.addEventListener("click", function (event) {
        var a = document.getElementById("wsp_a").value;
        var b = document.forms['dane'].elements['wb'].value;
        var c = document.getElementById("wsp_c").value;

        var rownanie = new kwadratowe(a,b,c);
        rownanie.licz();

        if(rownanie.getDelta() == null) {
            alert("coś poszło nie tak")
        }

        var lista = document.getElementById("lista");
        var x1 = null;
        var x2 = null;

        if(lista.hasChildNodes()) {
            while(lista.hasChildNodes())
                lista.removeChild(lista.firstChild);
        }

        var wynik = document.createTextNode("Wynik: ")
        if( rownanie.getDelta() > 0) {
            x1 = 'x\u2081 = ' + rownanie.getX1();
            x2 = 'x\u2082 = ' + rownanie.getX2();
        } else if( rownanie.getDelta() == 0) {
            x1 = 'x = ' + rownanie.getX1();
        } else {
            x1 = 'brak miejsc zerowych, delta < 0';
        }

        var ul = document.createElement("ul")
        var li = document.createElement("li")

        var liText = document.createTextNode(x1);

        li.appendChild(liText);
        ul.appendChild(li);
        lista.appendChild(wynik);
        lista.appendChild(ul);

        if (x2) {
            var li2 = document.createElement("li")
            var liText2 = document.createTextNode(x2)

            li2.appendChild(liText2)
            ul.appendChild(li2)
        }
    });

}