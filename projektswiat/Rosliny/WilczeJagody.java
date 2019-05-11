package projektswiat.Rosliny;

import projektswiat.Organizmy;
import projektswiat.Swiat;
import projektswiat.Organizm;


public class WilczeJagody extends Roslina{
    
    public WilczeJagody(int polozenie_x, int polozenie_y, Swiat swiat)
    {
        super(99,'J',Organizmy.WILCZEJAGODY,polozenie_x,polozenie_y,swiat);
    }
    
    @Override
    public int kolizja(Organizm atakujacy)
    {
        swiat.komentator.komentuj(0, this, atakujacy);
        zabij();
        return 0;
    }
}
