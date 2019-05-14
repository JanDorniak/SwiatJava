package projektswiat.rosliny;

import projektswiat.Organizmy;
import projektswiat.Swiat;
import projektswiat.Organizm;

public class Wilczejagody extends Roslina{
    
    public Wilczejagody(int polozenie_x, int polozenie_y, Swiat swiat)
    {
        super(99,'J',Organizmy.WILCZEJAGODY,polozenie_x,polozenie_y,swiat);
    }
    
    @Override
    public int kolizja(Organizm atakujacy)
    {
        zabij();
        return 0;
    }
}
