var miesiace = ['stycznia', 'lutego', 'marca', 'kwietnia', 'maja', 'czerwca', 'lipca', 'sierpnia',  'września', 'października', 'listopada', 'grudnia' ];

function funkcje() {
    var news1 = document.getElementById("news1");
    var news2 = document.getElementById("news2");
    news1.innerHTML = powitanie()+"<br/>"+data()+"<br/>";
    news2.innerHTML = dniDoUrodzin();
}

function powitanie() {
    var dzisiaj = new Date();
    var godzina = dzisiaj.getHours();
    if( (godzina<18) && (godzina>6) ) {
        return 'Dzień dobry,';
    } else {
        return 'Dobry wieczór,';
    }
}

function data() {
    var dzisiaj = new Date();
    var dzien =  dzisiaj.getDate();
    var miesiac = miesiace[dzisiaj.getMonth()];
    var rok = dzisiaj.getFullYear();

    return 'dzisiaj jest '+ dzien + ' ' +  miesiac + ' ' + ' '  + rok + ' r.';
}

function zegarek() {
    var data = new Date();
    var godzina = data.getHours();
    var minuta = data.getMinutes();
    var sekunda = data.getSeconds();

    if(minuta < 10) minuta = "0"+minuta;
    if(sekunda < 10) sekunda = "0"+sekunda;
    var stopka = document.getElementById("stopka");
    stopka.innerHTML = "&copy; 2021 KK | "+godzina+":"+minuta+":"+sekunda;
}


function dniDoUrodzin() {
    var month = 3;
    var day = 26;

    var date2 = new Date();
    if(date2.getMonth() <= month) {
        var date1 = new Date(date2.getFullYear(), month, day);
    } else {
        var date1 = new Date(date2.getFullYear()+1, month, day);
    }
    var difference = date1.getTime() - date2.getTime();
    var days = Math. ceil(difference / (1000 * 3600 * 24));


    return 'Urodziny za ' + days + ' dni';
}