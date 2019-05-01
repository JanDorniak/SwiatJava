package projektswiat;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;

import projektswiat.zwierzeta.Owca;
import projektswiat.zwierzeta.Wilk;
import projektswiat.zwierzeta.Lis;
import projektswiat.zwierzeta.Zolw;
import projektswiat.zwierzeta.Antylopa;
import projektswiat.zwierzeta.Czlowiek;
import projektswiat.Rosliny.Trawa;
import projektswiat.Rosliny.Mlecz;
import projektswiat.Rosliny.Guarana;
import projektswiat.Rosliny.WilczeJagody;
import projektswiat.Rosliny.BarszczSosnowskiego;

public class Swiat {
    private int tura;
    List<Organizm> lista = new LinkedList<>();
    List<Organizm> listaNowych = new LinkedList<>();
    Okno okno;
    
    
    public Swiat(int x, int y)
    {
        this.tura = 0;
        plansza = new Plansza(x,y);
        stworzOkno();
        inicjujOrganizmy();
        //wyswietl
    }
    
    private void stworzOkno()
    {
        okno = new Okno(this);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
    }
    
    public void inicjujOrganizmy()// na dol
    {
        for (Organizmy org : Organizmy.values())
        {
            if (org.equals(Organizmy.CZLOWIEK))
                continue;
            for (int i = 0; i < 2; i++)
                dodajOrganizm(org);
        }
        dodajOrganizm(Organizmy.CZLOWIEK);
    }
    
    private Organizm stworzPoNazwie(Organizmy nazwa, int x, int y)
    {
        switch (nazwa)
        {
            case OWCA:
                return new Owca(x,y,this);
            case WILK:
                return new Wilk(x,y,this);
            case LIS:
                return new Lis(x,y,this);
            case ZOLW:
                return new Zolw(x,y,this);
            case ANTYLOPA:
                return new Antylopa(x,y,this);
            case TRAWA:
                return new Trawa(x,y,this);
            case MLECZ:
                return new Mlecz(x,y,this);
            case GUARANA:
                return new Guarana(x,y,this);
            case WILCZEJAGODY:
                return new WilczeJagody(x,y,this);
            case BARSZCZSOSNOWSKIEGO:
                return new BarszczSosnowskiego(x,y,this);
            case CZLOWIEK:
                return new Czlowiek(x,y,this);
            default: 
                return null;
        }
    }
    
    private void rysuj()
    {
        System.out.println(lista.size());
        int ile_wolnych = 0;
        okno.rysuj();
        for (int i = 0; i < plansza.getX(); i++)
        {
            for (int j = 0; j < plansza.getY(); j++)
            {
                Organizm aktualny = plansza.getOrganizm(i, j);
                if (aktualny != null)
                    System.out.print(aktualny.getSymbol());
                else
                {
                    System.out.print('.');
                    ile_wolnych++;
                }
            }
            System.out.println();
        }
        System.out.println(ile_wolnych);
        System.out.println();
        System.out.println();
    }
    
    private void dodajDoListy(Organizm organizm)
    {
        if (lista.isEmpty())
        {
            lista.add(organizm);
            return;
        }
        
        boolean flaga = false;
        int pozycja = 0;
        
        for (Organizm i : lista)
        {
            if(i == null)
                continue;
            if (i.getInicjatywa() < organizm.getInicjatywa())
            {
                lista.add(pozycja, organizm);
                flaga = true;
                break;
            }
            pozycja++;
        }
        if (flaga == false)
            lista.add(lista.size(), organizm);
    }
    
    /*private void losujMiejsce(Integer x, Integer y)
    {
        Random generator = new Random();
        do 
	{
		x = generator.nextInt(plansza.getX());
		y = generator.nextInt(plansza.getY());
	} while (!(plansza.czyWolne(x.intValue(), y.intValue())));
    }*/
    /**/
    
    
    public Plansza plansza;
    //komentator
    
    public void dodajOrganizm(Organizmy nazwa, int x, int y)
    {
        if (x < 0 || y < 0)
        {
            Random generator = new Random();
            do 
            {
                    x = generator.nextInt(plansza.getX());
                    y = generator.nextInt(plansza.getY());
            } while (!(plansza.czyWolne(x, y)));
        }
        

        
        Organizm nowy = stworzPoNazwie(nazwa, x, y);
        if (nowy == null)
            return;  
        
        dodajDoListy(nowy);
        plansza.umiesc(nowy, x, y);
    }
    
    public void dodajOrganizm(Organizmy nazwa)
    {
        dodajOrganizm(nazwa, -1, -1);
    }
    
    public void przygotujDoUsuniecia(int x, int y)
    {
        int pozycja = 0;
        for (Organizm i : lista)
        {
            if (i == null)
                continue;
            if (i.getX() == x && i.getY() == y)
            {
                lista.set(pozycja, null);
                break;
            }
            pozycja++;
        }
        
        /*pozycja = 0;
        for (Organizm i : listaNowych)
        {
            if (i == null)
                continue;
            if (i.getX() == x && i.getY() == y)
            {
                listaNowych.set(pozycja, null);
                break;
            }
            pozycja++;
        }*/
        
        pozycja = 0;
        for (Organizm i : listaNowych)
        {
            if (i == null)
                continue;
            if (i.getX() == x && i.getY() == y)
            {
                listaNowych.remove(i);
                break;
            }
            pozycja++;
        }
    }
    
    public void przygotujDoDodania(Organizmy nazwa, int x, int y)
    {
        Organizm nowy = stworzPoNazwie(nazwa, x, y);
        plansza.umiesc(nowy, x, y);
        listaNowych.add(nowy);
    }
    
    public void wykonajTure()
    {
        rysuj();
        for (Organizm i : lista)
        {
            if (i == null || tura == 0)
                continue;
            else
                i.akcja();
        }
        
        lista.removeAll(Collections.singleton(null));
        listaNowych.removeAll(Collections.singleton(null));
        
        for (Organizm i : listaNowych) //dodanie do listy rozmnozonych
        {
            dodajDoListy(i);
        }
        listaNowych.clear();
             
        tura++;
        
        rysuj();
    }

    public int getTura()
    {
        return tura;
    }
}