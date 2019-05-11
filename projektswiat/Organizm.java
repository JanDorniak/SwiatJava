package projektswiat;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public abstract class Organizm {
    protected int sila, inicjatywa;
    protected int narodziny;
    protected char symbol_na_planszy;
    protected Organizmy nazwa;
    protected int polozenie_x, polozenie_y;
    protected Swiat swiat;
    
    public Organizm(int sila, int inicjatywa, char symbol_na_planszy, Organizmy nazwa, int polozenie_x, int polozenie_y, Swiat swiat)
    {
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.symbol_na_planszy = symbol_na_planszy;
        this.nazwa = nazwa;
        this.polozenie_x = polozenie_x;
        this.polozenie_y = polozenie_y;
        this.swiat = swiat;
    }
    
    protected void rozmnazaj()
    {
        int zmianax = 0, zmianay = 0;

	if (!swiat.plansza.czyWolneWokol(polozenie_x, polozenie_y)) //jesli nie ma miejsca na potomka wokol
		return;

        Random generator = new Random();
        
	do
	{
            zmianax = generator.nextInt(3)-1;
            if (zmianax == 0)
                zmianay = generator.nextInt(3)-1;
	} while (!swiat.plansza.czyWolne(polozenie_x + zmianax, polozenie_y + zmianay));

        swiat.przygotujDoDodania(nazwa, polozenie_x+zmianax, polozenie_y+zmianay);
    }
  
    public abstract void akcja();
    public abstract int kolizja(Organizm atakujacy);
    
    //zapisz wczytaj sie
    
    public void zabij()
    {
        swiat.przygotujDoUsuniecia(polozenie_x, polozenie_y);
        swiat.plansza.usun(polozenie_x, polozenie_y);
    }
    
    public int getSila()
    {
        return sila;
    }
    
    public int getInicjatywa()
    {
        return inicjatywa;
    }
    
    public int getX()
    {
        return polozenie_x;
    }
    
    public int getY()
    {
        return polozenie_y;
    }
    
    public int getWiek()
    {
        return narodziny;
    }
    
    public char getSymbol()
    {
        return symbol_na_planszy;
    }
    
    public Organizmy getNazwa()
    {
        return nazwa;
    }
    
    public void zapiszSie(FileWriter zapis) throws IOException
    {
        zapis.write(nazwa+"\n"+polozenie_x+"\n"+polozenie_y+"\n"+narodziny+"\n"+sila+"\n");
    }
    
    public void wczytajSie(Vector<String> dane)
    {
        this.narodziny = Integer.parseInt(dane.remove(0));
        this.sila = Integer.parseInt(dane.remove(0));
    }
}
