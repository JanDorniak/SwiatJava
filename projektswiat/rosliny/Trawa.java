package projektswiat.rosliny;

import projektswiat.Organizmy;
import projektswiat.Swiat;

public class Trawa extends Roslina{
    
    public Trawa(int polozenie_x, int polozenie_y, Swiat swiat)
    {
        super(0,'T',Organizmy.TRAWA,polozenie_x,polozenie_y,swiat);
    }
}
