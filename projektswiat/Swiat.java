package projektswiat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;
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
    private List<Organizm> lista; //lista zyjacych organizmow
    private List<Organizm> listaNowych; //lista organizmow do dodania po turze
    public Komentator komentator;
    public Okno okno;
    public Plansza plansza;
    
    public Swiat(int x, int y)
    {
        this.tura = 0;
        this.lista = new LinkedList<>();
        this.listaNowych = new LinkedList<>();
        plansza = new Plansza(x,y);
        komentator = new Komentator();
        stworzOkno();
        inicjujOrganizmy();
        okno.rysuj();
    }
    
    private void stworzOkno() //utworzenie okna graficznego
    {
        okno = new Okno(this);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
    }
    
    private void inicjujOrganizmy()
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
    
    private Organizm stworzPoNazwie(Organizmy nazwa, int x, int y) //stworz i zwroc organizm o okreslonej nazwie
    {

        ///////////////////////////////////////*/
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
            {
                pozycja++;
                continue;
            }
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
            {
                pozycja++;
                continue;
            }
            if (i.getX() == x && i.getY() == y)
            {
                lista.set(pozycja, null);
                return;
            }
            pozycja++;
        }
        
        pozycja = 0;
        for (Organizm i : listaNowych)
        {
            if (i == null)
            {
                pozycja++;
                continue;
            }
            if (i.getX() == x && i.getY() == y)
            {
                listaNowych.set(pozycja, null);
                return;
            }
            pozycja++;
        }
    }
    
    public void przygotujDoDodania(Organizmy nazwa, int x, int y)//dodanie do "oczekujacych" do dodania w nastepnej turze
    {
        Organizm nowy = stworzPoNazwie(nazwa, x, y);
        plansza.umiesc(nowy, x, y);
        listaNowych.add(nowy);
    }
    
    public void wykonajTure()
    {
        for (Organizm i : lista)
        {
            if (i == null)
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
        
        okno.rysuj();
    }

    public int getTura()
    {
        return tura;
    }
    
    public boolean czyZyjeCzlowiek()
    {
        for (Organizm i : lista)
            if (i instanceof Czlowiek)
            {
                return true;
            }
        return false;
    }
    
    public void aktywujUmiejetnosc() //znajduje czlowieka i aktywuje skilla
    {
        for (Organizm i : lista)
            if (i instanceof Czlowiek)
            {
                Czlowiek czl = (Czlowiek)i;
                czl.uzyjUmiejetnosc();
            }
    }
    
    public void zapiszGre() throws IOException
    {
        FileWriter zapis = null;
        try
        {
            zapis = new FileWriter("zapis.txt");
            zapis.write(tura+"\n"+plansza.getX()+"\n"+plansza.getY()+"\n");
            
            lista.removeAll(Collections.singleton(null));
            for (Organizm i : lista)
            {
                i.zapiszSie(zapis);
            }
        }
        finally
        {
            if (zapis != null)
                zapis.close();
        }
        
    }
    
    public void wczytajGre() throws IOException
    {
        BufferedReader zapis = null;
        try
        {
            lista.clear();
            listaNowych.clear();
            
            int x, y;
            Organizmy nazwa;
            Vector<String> dane = new Vector();
            zapis = new BufferedReader(new FileReader("zapis.txt"));
            
            String linia;
            while((linia = zapis.readLine()) != null)
            {
                dane.add(linia);
            }
            
            tura = Integer.parseInt(dane.remove(0));
            x = Integer.parseInt(dane.remove(0));
            y = Integer.parseInt(dane.remove(0));
            
            plansza = new Plansza(x,y);
            
            while (dane.size() > 0)
            {
                nazwa = Organizmy.valueOf(dane.remove(0));
                x = Integer.parseInt(dane.remove(0));
                y = Integer.parseInt(dane.remove(0));
                if (plansza.czyWolne(x, y))
                    dodajOrganizm(nazwa, x, y);
                plansza.getOrganizm(x, y).wczytajSie(dane);
            }
        }
        catch (FileNotFoundException ex) 
        {

        }        
        finally
        {
            if (zapis != null)
                zapis.close();
            okno.rysuj();
            okno.wyczyscKomentarze();
        }
    }
}