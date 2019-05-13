package projektswiat.Rosliny;

import projektswiat.Organizmy;
import projektswiat.Swiat;
import projektswiat.Organizm;
import projektswiat.zwierzeta.Zwierze;

public class BarszczSosnowskiego extends Roslina{
        
    public BarszczSosnowskiego(int polozenie_x, int polozenie_y, Swiat swiat)
    {
        super(10,'B',Organizmy.BARSZCZSOSNOWSKIEGO,polozenie_x,polozenie_y,swiat);
    }
    
    @Override
    public void akcja()
    {
        Organizm[] sasiedzi = swiat.plansza.getSasiedzi(polozenie_x, polozenie_y);
        for (int i = 0; i < 4; i++)
	{
            if (sasiedzi[i] != null && sasiedzi[i] instanceof Zwierze) //zaabija sasiadow (oprocz roslin)
            { 
                swiat.komentator.komentuj(1, sasiedzi[i], this);
		sasiedzi[i].zabij();
            }
	}
        
        super.akcja();
    }
    
    @Override
    public int kolizja(Organizm atakujacy)
    {
        zabij();
        return 0;
    }
}
