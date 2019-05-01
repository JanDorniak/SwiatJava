package projektswiat.Rosliny;

import projektswiat.Organizmy;
import projektswiat.Swiat;
import projektswiat.Organizm;
import projektswiat.zwierzeta.Zwierze;


public class Guarana extends Roslina{
            
    public Guarana(int polozenie_x, int polozenie_y, Swiat swiat)
    {
        super(0,'G',Organizmy.GUARANA,polozenie_x,polozenie_y,swiat);
    }
    
    @Override
    public int kolizja(Organizm atakujacy)
    {
        Zwierze nowy = (Zwierze)atakujacy;
        
        nowy.setSila(nowy.getSila() + 3);
        
        return 1;
    }
}
