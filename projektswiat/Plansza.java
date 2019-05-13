package projektswiat;


public class Plansza {
    final private int x, y;
    private Organizm[][] pole_gry;
    
    public Plansza(int x, int y)
    {
        this.x = x;
        this.y = y;
        pole_gry = new Organizm[x][y];
        inicjuj();
    }
    
    private void inicjuj()
    {
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
            {
                pole_gry[i][j] = null;
            }
    }
    
    public void umiesc(Organizm org, int x, int y)
    {
        pole_gry[x][y] = org;
    }
    
    public void usun(int x, int y)
    {
        pole_gry[x][y] = null;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public boolean czyWolne(int x, int y)
    {
        if (x >= this.x || y >= this.y || x < 0 || y < 0 || pole_gry[x][y] != null)
            return false;
        return true;
    }
    
    public boolean czyWolneWokol(int x, int y)
    {
        if (czyWolne(x-1,y) || czyWolne(x+1,y) || czyWolne(x, y-1) || czyWolne(x, y+1))
            return true;
        return false;
    }
    
    public Organizm getOrganizm(int x, int y)
    {
        if (x >= this.x || y >= this.y || x < 0 || y < 0)
		return null;
	return pole_gry[x][y];
    }
    
    public Organizm[] getSasiedzi(int x, int y)
    {
        Organizm[] sasiedzi = new Organizm[4];
	sasiedzi[0] = getOrganizm(x + 1, y);
	sasiedzi[1] = getOrganizm(x - 1, y);
	sasiedzi[2] = getOrganizm(x, y + 1);
	sasiedzi[3] = getOrganizm(x, y - 1);

	return sasiedzi;
    }
    
    public Organizm przesun(int x, int y, Kierunki kierunek)
    {
        int zmianax = 0, zmianay = 0;
	if (kierunek == Kierunki.LEWO)
		zmianax = -1;
	else if (kierunek == Kierunki.PRAWO)
		zmianax = 1;
	else if (kierunek == Kierunki.GORA)
		zmianay = 1;
	else
		zmianay = -1;
        
        if ((czyWolne(x+zmianax,y+zmianay))) //jesli pole jest wolne zwraca null
	{ 
		pole_gry[x + zmianax][y + zmianay] = pole_gry[x][y];
		pole_gry[x][y] = null;
		return null;
	}
	else // jesli nie to zwraca organizm
	{ 
		return pole_gry[x + zmianax][y + zmianay];
	}
    }
    
}

