package projektswiat.zwierzeta;

import projektswiat.Kierunki;
import projektswiat.Organizm;
import projektswiat.Organizmy;
import projektswiat.Swiat;

public class Antylopa extends Zwierze{
    
    public Antylopa(int polozenie_x, int polozenie_y, Swiat swiat)
    {
       super(4,4,'A', Organizmy.ANTYLOPA, polozenie_x,polozenie_y,swiat); 
    }
    
    @Override
    public void akcja()
    {
        super.akcja();
        if (swiat.plansza.getOrganizm(polozenie_x, polozenie_y) == this)
            super.akcja();
    }
}
