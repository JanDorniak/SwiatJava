package projektswiat;


public enum Kierunki {
    LEWO(1),
    PRAWO(2),
    GORA(3),
    DOL(4);
    
    private final int wartosc;
    
    private Kierunki(int x)
    {
        this.wartosc = x;
    }
    
    int getWartosc()
    {
        return wartosc;
    }
    
}
