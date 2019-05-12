package projektswiat;

import java.util.Vector;
import projektswiat.Rosliny.Roslina;

public class Komentator {
    
    static final int INFO_O_ROZMNAZANIU = 0;
    private Vector<String> rejestr;
    
    public Komentator()
    {
        this.rejestr = new Vector<String>();
    }
    
    public void komentuj(int wynik, Organizm broniacy, Organizm atakujacy)
    {
        String atak = odmienAtakujacego(atakujacy.getNazwa());
	String obrona = odmienBroniacego(broniacy.getNazwa());
	if (atak == " " || obrona == " ")
		return;
        String zdanie = atak;
        
        if (broniacy instanceof Roslina)
            zdanie += " zjada ";
        else if (atakujacy.getNazwa().equals(broniacy.getNazwa()))
        {
            if (INFO_O_ROZMNAZANIU == 1)
                zdanie += " sporyka ";
            return;
        }
        else
        {
            zdanie += " atakuje ";
        }
        
        zdanie += obrona;
        
        if (wynik == 1)
            zdanie += " i zyje dalej (";
	else if (wynik == 0)
            zdanie += " i umiera (";
	else
            zdanie += " i pozostaje na swoim miejscu (";

	zdanie += atakujacy.getY() + 1;
	zdanie += ',';
	zdanie += atakujacy.getX() + 1;
	zdanie += ')';
        
        rejestr.add(zdanie);
    }
    
    private void czyscRejestr()
    {
        rejestr.clear();
    }
    
    public Vector<String> getRejestr()
    {
        return rejestr;
    }
    
    private String odmienAtakujacego(Organizmy atakujacy)
    {
        String odmieniona_nazwa;
	switch (atakujacy)
	{
	case WILK:
		odmieniona_nazwa = "Wilk";
		break;
	case OWCA:
		odmieniona_nazwa = "Owca";
		break;
	case LIS:
		odmieniona_nazwa = "Lis";
		break;
	case ZOLW:
		odmieniona_nazwa = "Zolw";
		break;
	case ANTYLOPA:
		odmieniona_nazwa = "Antylopa";
		break;
	case CZLOWIEK:
		odmieniona_nazwa = "Czlowiek";
		break;
	case BARSZCZSOSNOWSKIEGO:
		odmieniona_nazwa = "Barszcz Sosnowskiego";
		break;
	default:
		odmieniona_nazwa = " ";
	}

	return odmieniona_nazwa;
    }
    
    private String odmienBroniacego(Organizmy broniacy)
    {
        String odmieniona_nazwa;
	switch (broniacy)
	{
	case WILK:
		odmieniona_nazwa = "Wilka";
		break;
	case OWCA:
		odmieniona_nazwa = "Owce";
		break;
	case LIS:
		odmieniona_nazwa = "Lisa";
		break;
	case ZOLW:
		odmieniona_nazwa = "Zolwia";
		break;
	case ANTYLOPA:
		odmieniona_nazwa = "Antylope";
		break;
	case CZLOWIEK:
		odmieniona_nazwa = "Czlowieka";
		break;
	case TRAWA:
		odmieniona_nazwa = "Trawe";
		break;
	case MLECZ:
		odmieniona_nazwa = "Mlecza";
		break;
	case GUARANA:
		odmieniona_nazwa = "Guarane";
		break;
	case WILCZEJAGODY:
		odmieniona_nazwa = "Wilcze Jagody";
		break;
	case BARSZCZSOSNOWSKIEGO:
		odmieniona_nazwa = "Barszcz Sosnowskiego";
		break;
	default:
		odmieniona_nazwa = " ";
	}

	return odmieniona_nazwa;
    }
}
