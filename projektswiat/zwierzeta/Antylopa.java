package projektswiat.zwierzeta;

import projektswiat.Kierunki;
import projektswiat.Organizm;
import projektswiat.Organizmy;
import projektswiat.Swiat;


public class Antylopa extends Zwierze{
    
    public Antylopa(int polozenie_x, int polozenie_y, Swiat swiat)
    {
       super(4,4,'A', Organizmy.ANTYLOPA, polozenie_x,polozenie_y,swiat); 
    }
    
    @Override
    public void akcja()
    {
        Kierunki kierunek = wybierzKierunek();
        Organizm przeciwnik = swiat.plansza.przesun(polozenie_x, polozenie_y, kierunek);
        if (przeciwnik != null)
        {
            int wynik_walki = przeciwnik.kolizja(this);
            interpretujWynik(wynik_walki, kierunek, przeciwnik);
            if (wynik_walki == 1 || wynik_walki == -1)
                super.akcja();
        }
        else
        {
            zmienWspolrzedne(kierunek);
            super.akcja();
        }
    }
}
