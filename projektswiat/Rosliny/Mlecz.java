package projektswiat.Rosliny;

import projektswiat.Organizmy;
import projektswiat.Swiat;

public class Mlecz extends Roslina{
        
    public Mlecz(int polozenie_x, int polozenie_y, Swiat swiat)
    {
        super(0,'M',Organizmy.MLECZ,polozenie_x,polozenie_y,swiat);
    }
    
    @Override
    public void akcja()
    {
        super.akcja();
        super.akcja();
        super.akcja();
    }
}
