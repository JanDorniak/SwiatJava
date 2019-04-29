package projektswiat.zwierzeta;

import projektswiat.Organizmy;
import projektswiat.Swiat;


public class Owca extends Zwierze {
    
    public Owca(int polozenie_x, int polozenie_y, Swiat swiat)
    {
       super(4,4,'O', Organizmy.OWCA, polozenie_x,polozenie_y,swiat); 
    }
}
