package projektswiat.zwierzeta;

import projektswiat.Organizmy;
import projektswiat.Swiat;
import projektswiat.Organizm;
import projektswiat.Kierunki;


public class Lis extends Zwierze{
    
    public Lis(int polozenie_x, int polozenie_y, Swiat swiat)
    {
       super(3,7,'L', Organizmy.LIS, polozenie_x,polozenie_y,swiat); 
    }
    
    private boolean znajdzSlabszego()
    {
        Organizm[] sasiedzi = swiat.plansza.getSasiedzi(polozenie_x, polozenie_y);
        for (int i = 0; i < 4; i++)
	{
            if (sasiedzi[i] != null && sasiedzi[i].getSila() <= sila)
            { 
                return true;
            }
	}
        return false;
    }
    
    @Override
    public void akcja()
    {
        if (!(swiat.plansza.czyWolneWokol(polozenie_x, polozenie_y) || znajdzSlabszego()))
            return;
        
        Kierunki kierunek;
	Organizm przeciwnik;
	do
	{
		kierunek = wybierzKierunek();
		przeciwnik = swiat.plansza.przesun(polozenie_x, polozenie_y, kierunek);
	} while (przeciwnik != null && przeciwnik.getSila() > sila); //jesli ma mozliwy ruch szuka az znajdzie dogodne miejsce

	if (przeciwnik != null)
	{
		int wynik_walki = przeciwnik.kolizja(this);
		interpretujWynik(wynik_walki, kierunek, przeciwnik);
	}
	else
		zmienWspolrzedne(kierunek);
    }
    
}
