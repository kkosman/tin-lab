package ti.model;

public class KKuzytkownik {
    private String login = "";
    private int uprawnienia = -1;
    // -1 użytkownik niezalogowany
    // 1 użytkownik zalogowany
    // 2 administrator

    private String imie = "";
    private String nazwisko = "";
    private int wiek = -1;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = filtruj(imie);
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = filtruj(nazwisko);
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        if(wiek >= 0)
            this.wiek = wiek;
    }

    public String getWieks() {
        if (this.wiek>=0)
            return ""+wiek;
        else return "";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(int uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

    public static String filtruj(String wejscie) {

        StringBuffer filtrowane = new StringBuffer();
        char c;

        for (int i=0; i<wejscie.length(); i++)
        {
            c = wejscie.charAt(i);
            switch(c)
            {
                case '<': filtrowane.append("&lt;"); break;
                case '>': filtrowane.append("&gt;"); break;
                case '"': filtrowane.append("&quot;"); break;
                case '&': filtrowane.append("&amp;"); break;
                default: filtrowane.append(c);
            }
        }
        return filtrowane.toString();
    }
}
