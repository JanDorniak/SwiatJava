package projektswiat.zwierzeta;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import projektswiat.Organizmy;
import projektswiat.Swiat;
import projektswiat.Organizm;
import projektswiat.Kierunki;


public class Czlowiek extends Zwierze{
        
    private int tura_uzycia = 0;
    
    public Czlowiek(int polozenie_x, int polozenie_y, Swiat swiat)
    {
       super(5,4,'C', Organizmy.CZLOWIEK, polozenie_x,polozenie_y,swiat); 
    }
    
    private void calopalenie()
    {
        Organizm[] sasiedzi = swiat.plansza.getSasiedzi(polozenie_x, polozenie_y);
	for (int i = 0; i < 4; i++)
	{
            if (sasiedzi[i] != null)
            {
                swiat.komentator.komentuj(1, sasiedzi[i], this);
		sasiedzi[i].zabij();
            }
	}
    }
    
    @Override
    public void akcja()
    {
        if (swiat.getTura() - tura_uzycia < 5 && tura_uzycia != 0) //jesli uzyto nie dawniej niz 5 tur temu to uzyj umiejetnosci
            calopalenie();
        
        Kierunki kierunek = swiat.okno.getKierunek();
        int wynik_walki = -2;
        Organizm przeciwnik = swiat.plansza.przesun(polozenie_x, polozenie_y, kierunek);
	if (przeciwnik != null)
	{
            wynik_walki = przeciwnik.kolizja(this);
            interpretujWynik(wynik_walki, kierunek, przeciwnik);
	}
	else 
	{
            zmienWspolrzedne(kierunek);
	}

	if ((wynik_walki == 1 || wynik_walki == -2) && swiat.getTura() - tura_uzycia < 5 && tura_uzycia != 0) //uzycie umiejetnosci po ruchu
            calopalenie();
    }
    
    public void uzyjUmiejetnosc()
    {
        if (tura_uzycia == 0 || swiat.getTura() - tura_uzycia >= 10)
        {
            tura_uzycia = swiat.getTura();
            calopalenie();
        }
    }
    
    @Override
    public void zapiszSie(FileWriter zapis) throws IOException
    {
        zapis.write(nazwa+"\n"+polozenie_x+"\n"+polozenie_y+"\n"+narodziny+"\n"+sila+"\n"+tura_uzycia+"\n");
    }
    
    @Override
    public void wczytajSie(Vector<String> dane)
    {
        this.narodziny = Integer.parseInt(dane.remove(0));
        this.sila = Integer.parseInt(dane.remove(0));
        this.tura_uzycia = Integer.parseInt(dane.remove(0));
    }
    
}
