package projektswiat.Rosliny;

import java.util.Random;

import projektswiat.Organizm;
import projektswiat.Organizmy;
import projektswiat.Swiat;

public abstract class Roslina extends Organizm{
    
    static final int szansa = 3;
   
    public Roslina(int sila, char symbol_na_planszy, Organizmy nazwa, int polozenie_x, int polozenie_y, Swiat swiat)
    {
        super(sila, 0, symbol_na_planszy, nazwa, polozenie_x, polozenie_y, swiat);
    }
    
    @Override
    public void akcja()
    {
        Random generator = new Random();
        int czy_rozmnozyc = generator.nextInt(100);
        if (czy_rozmnozyc < szansa)
            rozmnazaj();
    }
    
    @Override
    public int kolizja(Organizm atakujacy)
    {
        return 1;
    }
}
