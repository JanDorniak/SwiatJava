package projektswiat;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import java.util.Iterator;
import java.util.ListIterator;

import projektswiat.zwierzeta.Owca;
import projektswiat.zwierzeta.Wilk;

public class Swiat {
    private int tura;
    List<Organizm> lista = new LinkedList<>();
    List<Organizm> listaNowych = new LinkedList<>();
    
    public Swiat(int x, int y)
    {
        this.tura = 0;
        plansza = new Plansza(x,y);
        /*(dodajOrganizm(Organizmy.OWCA, 2, 2);
        dodajOrganizm(Organizmy.OWCA, 3, 3);
        dodajOrganizm(Organizmy.OWCA, 2, 4);
        dodajOrganizm(Organizmy.OWCA, 1, 2);*/
        dodajOrganizm(Organizmy.OWCA, 1, 1);
        dodajOrganizm(Organizmy.WILK, 0, 0);
        dodajOrganizm(Organizmy.WILK, 1, 0);
        //dodajOrganizm(Organizmy.WILK, 2, 1);
        //inicjuj
        //wyswietl
    }
    
    private Organizm stworzPoNazwie(Organizmy nazwa, int x, int y)
    {
        switch (nazwa)
        {
            case OWCA:
                return new Owca(x,y,this);
            case WILK:
                return new Wilk(x,y,this);
            default: 
                return null;
        }
    }
    
    private void rysuj()
    {
        System.out.println(lista.size());
        for (int i = 0; i < plansza.getX(); i++)
        {
            for (int j = 0; j < plansza.getY(); j++)
            {
                Organizm aktualny = plansza.getOrganizm(i, j);
                if (aktualny != null)
                    System.out.print(aktualny.getSymbol());
                else
                    System.out.print('.');
            }
            System.out.println();
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
            lista.add(lista.size()-1, organizm);
    }
    
    private void losujMiejsce(int x, int y)
    {
        Random generator = new Random();
        do 
	{
		x = generator.nextInt(plansza.getX());
		y = generator.nextInt(plansza.getY());
	} while (!(plansza.czyWolne(x, y)));
    }
    /**/
    
    
    public Plansza plansza;
    //komentator
    
    public void dodajOrganizm(Organizmy nazwa, int x, int y)
    {
        if (x < 0 || y < 0)
            losujMiejsce(x, y);
        
        Organizm nowy = stworzPoNazwie(nazwa, x, y);
        
        dodajDoListy(nowy);
        plansza.umiesc(nowy, x, y);
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
    }
    
    public void przygotujDoDodania(Organizmy nazwa, int x, int y)
    {
        Organizm nowy = stworzPoNazwie(nazwa, x, y);
        plansza.umiesc(nowy, x, y);
        listaNowych.add(nowy);
    }
    
    public void wykonajTure()
    {
        //rysowanie
        for (Organizm i : lista)
        {
            if (i == null || tura == 0)
                continue;
            else
                i.akcja();
        }
        
        lista.removeAll(Collections.singleton(null));
        
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