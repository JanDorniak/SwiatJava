package projektswiat.zwierzeta;

import java.util.Random;
import projektswiat.Kierunki;
import projektswiat.Organizm;
import projektswiat.Swiat;
import projektswiat.Organizmy;

public abstract class Zwierze extends Organizm {
    
    public Zwierze(int sila, int inicjatywa, char symbol_na_planszy, Organizmy nazwa, int polozenie_x, int polozenie_y, Swiat swiat)
    {
        super(sila, inicjatywa, symbol_na_planszy, nazwa, polozenie_x, polozenie_y, swiat);
    }
    
    protected Kierunki wybierzKierunek()
    {
        Random generator = new Random();
        Kierunki kierunek = Kierunki.values()[generator.nextInt(Kierunki.values().length)];
	if (kierunek == Kierunki.LEWO && polozenie_x == 0)
		return Kierunki.PRAWO;
	else if (kierunek == Kierunki.PRAWO && polozenie_x == swiat.plansza.getX() - 1)
		return Kierunki.LEWO;
	else if (kierunek == Kierunki.GORA && polozenie_y == swiat.plansza.getY() - 1)
		return Kierunki.DOL;
	else if (kierunek == Kierunki.DOL && polozenie_y == 0)
		return Kierunki.GORA;

	return kierunek;
    }
    
    protected void interpretujWynik(int wynik, Kierunki kierunek, Organizm przeciwnik)
    {
        swiat.komentator.komentuj(wynik, przeciwnik, this);
        if (wynik == 0) //walka przegrana
        {
            zabij();
            return;
        }
        else if (wynik == 1) // walka wygrana
        {
            przeciwnik.zabij();
            swiat.plansza.przesun(polozenie_x, polozenie_y, kierunek);
            zmienWspolrzedne(kierunek);
        }
        else if (wynik == -1)
        {
            return;
        }
    }
    
    protected void zmienWspolrzedne(Kierunki kierunek)
    {
        if (kierunek == Kierunki.LEWO)
		polozenie_x--;
	else if (kierunek == Kierunki.PRAWO)
		polozenie_x++;
	else if (kierunek == Kierunki.DOL)
		polozenie_y--;
	else
		polozenie_y++;
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
        }
        else
        {
            zmienWspolrzedne(kierunek);
        }
    }
    
    @Override
    public int kolizja(Organizm atakujacy)
    {
        if (atakujacy.getSymbol() == symbol_na_planszy) // jesli atakuje ten sam gatunek
	{
		rozmnazaj();
		return -1;
	}
	else if (atakujacy.getSila() >= sila) // jesli atakujacy wygrywa
	{
		return 1;
	}
	else // jesli atakujacy przegrywa
	{
		return 0;
	}
    }
    
    public void setSila(int n)
    {
        this.sila = n;
    }
}