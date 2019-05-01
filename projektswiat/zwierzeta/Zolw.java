package projektswiat.zwierzeta;

import java.util.Random;

import projektswiat.Organizmy;
import projektswiat.Swiat;
import projektswiat.Organizm;


public class Zolw extends Zwierze{
    
    public Zolw(int polozenie_x, int polozenie_y, Swiat swiat)
    {
       super(2,1,'Z', Organizmy.ZOLW, polozenie_x,polozenie_y,swiat); 
    }
    
    @Override
    public void akcja()
    {
        Random generator = new Random();
        int czy_ruszac = generator.nextInt(4);
        if (czy_ruszac == 3)
            super.akcja();
    }
    
    @Override
    public int kolizja(Organizm atakujacy)
    {
        if (atakujacy.getSila() < 5)
            return -1;
	else
            super.kolizja(atakujacy);
        return -1; ////??????????
    }
}
